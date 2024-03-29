package it.polimi.ingsw.gc_12.ActionTests;

import it.polimi.ingsw.gc12.model.action.Action;
import it.polimi.ingsw.gc12.model.action.ActionPlaceOnTower;
import it.polimi.ingsw.gc12.model.card.CardType;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.board.occupiable.TowerFloor;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc12.model.player.resource.Money;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;
import it.polimi.ingsw.gc12.Server;
import it.polimi.ingsw.gc12.view.server.ServerRMIView;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class PlaceOnTowerTest {

    private Match match = InstanceCreator.createMatch(2);

    Server server = mock(Server.class);
    ServerRMIView view = mock(ServerRMIView.class);
    Event event = mock(Event.class);

    private ActionPlaceOnTower createAction( TowerFloor floor){
        Player player = match.getPlayer("p0");

        ActionPlaceOnTower action = new ActionPlaceOnTower(player, player.getFamilyMember(FamilyMemberColor.BLACK), floor);
        List<Resource> discounts = new ArrayList<>();
        discounts.add(new Money(1));
        action.setDiscounts(discounts);
        return action;
    }

    @Test
    public void test(){

        Mockito.doNothing().when(view).update();
        match.registerObserver(view);
        match.start();
        match.getActionHandler().flushEvents();//remove "EventStartTurn"

        System.out.println("starting test");
        TowerFloor floor =  match.getBoard().getTowerSet().getTower(CardType.BUILDING).getFloor(0);

        Player player = match.getPlayer("p0");
        for(ResourceType type : ResourceType.values())
            player.setResourceValue(type, 100);

        ActionPlaceOnTower action = new ActionPlaceOnTower(player, player.getFamilyMember(FamilyMemberColor.BLACK), floor);
        List<Resource> discounts = new ArrayList<>();
        discounts.add(new Money(1));
        action.setDiscounts(discounts);

        System.out.println("created " + action);


        assertTrue(action.isValid(match));

        System.out.println("starting action");
        action.start(match);

        assertTrue((floor.getRequiredValue() - action.getFamilyMember().getValue()) <= match.getActionHandler().getOffset());
        assertEquals(discounts, action.getDiscounts());

        for(ResourceType resourceType : ResourceType.values()){
            assertEquals(Optional.of(100), Optional.ofNullable(player.getResourceValue(resourceType)));
        }

        Action action1 = match.getActionHandler().getAvailableAction(0);
        action1.start(match);

        System.out.println("placement complete");
        assertTrue(match.getBoard().getTowerSet().getTower(CardType.BUILDING).getFloor(0).getOccupiers().size() > 0);
        assertTrue(match.getBoard().getTowerSet().getTower(CardType.BUILDING).getFloor(0).getOccupiers().get(0).getColor().equals(FamilyMemberColor.BLACK));
    }
}
