package it.polimi.ingsw.gc_12.ActionTests;

import it.polimi.ingsw.gc12.model.action.ActionPlaceOnMarket;
import it.polimi.ingsw.gc12.model.board.occupiable.SpaceMarket;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class PlaceOnMarketTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    FamilyMember familyMember = player.getFamilyMember(FamilyMemberColor.BLACK);
    SpaceMarket spaceMarket = match.getBoard().getMarket().getSpaceMarkets().get(0);
    SpaceMarket spaceMarket1 = match.getBoard().getMarket().getSpaceMarkets().get(1);

    @Test
    public void testA(){
        familyMember.setValue(7);
        spaceMarket.placeFamilyMember(familyMember);

        ActionPlaceOnMarket action = new ActionPlaceOnMarket(player, familyMember, spaceMarket);
        assertFalse(action.isValid(match));
    }

    @Test
    public void testB(){
        familyMember.setValue(0);
        player.setResourceValue(ResourceType.SERVANT, 0);
        ActionPlaceOnMarket action = new ActionPlaceOnMarket(player, familyMember, spaceMarket);
        assertFalse(action.isValid(match));
    }

    @Test
    public void testC(){
        ActionPlaceOnMarket action = new ActionPlaceOnMarket(player, familyMember, spaceMarket);
        ActionPlaceOnMarket action1 = new ActionPlaceOnMarket(player, familyMember, spaceMarket1);
        assertTrue(action.equals(action));
        assertFalse(action.equals(action1));
    }
}
