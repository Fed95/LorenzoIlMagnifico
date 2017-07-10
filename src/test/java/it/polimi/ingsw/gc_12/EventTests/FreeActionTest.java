package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.action.*;
import it.polimi.ingsw.gc12.model.board.occupiable.CouncilPalace;
import it.polimi.ingsw.gc12.model.board.occupiable.Occupiable;
import it.polimi.ingsw.gc12.model.board.occupiable.TowerFloor;
import it.polimi.ingsw.gc12.model.card.CardType;
import it.polimi.ingsw.gc12.model.event.EventFreeAction;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.Servant;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class FreeActionTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    FamilyMember familyMember = player.getFamilyMember(FamilyMemberColor.BLACK);
    TowerFloor towerFloor = match.getBoard().getTowerSet().getTower(CardType.TERRITORY).getFloor(0);
    List<Occupiable> occupiables = Collections.singletonList(towerFloor);
    List<Action> actions = new ArrayList<>();
    List<Resource> discounts = InstanceCreator.getResourceList();
    EventFreeAction event;

    @Test
    public void testConstructor(){
        try{
            event = new EventFreeAction(player, familyMember, occupiables, actions, discounts);
            event = new EventFreeAction(player, familyMember, occupiables, actions);
            event = new EventFreeAction(player, familyMember, occupiables);

            assertEquals(familyMember, event.getFamilyMember());
            assertEquals(occupiables, event.getOccupiables());

            event.toString();
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testSetActions(){
        familyMember.setValue(7);
        towerFloor.removeCard();
        towerFloor.getEffects().clear();
        towerFloor.setCard(InstanceCreator.createEmptyDevelopmentCard());
        event = new EventFreeAction(player, familyMember, occupiables, actions, discounts);
        event.setActions(match);
        assertEquals(2, event.getActions().size());
        ActionPlace action = ActionFactory.createActionPlace(player, familyMember, towerFloor, discounts);
        assertEquals(action, event.getActions().get(0));
    }

}
