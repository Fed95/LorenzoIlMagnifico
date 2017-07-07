package it.polimi.ingsw.gc_12.ActionTests;

import it.polimi.ingsw.gc12.model.action.ActionPlaceOnCouncil;
import it.polimi.ingsw.gc12.model.board.occupiable.CouncilPalace;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class PlaceOnCouncilTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    FamilyMember familyMember = player.getFamilyMember(FamilyMemberColor.BLACK);
    FamilyMember familyMember1 = player.getFamilyMember(FamilyMemberColor.WHITE);
    CouncilPalace councilPalace = match.getBoard().getCouncilPalace();

    @Test
    public void testA(){
        familyMember.setValue(0);
        player.setResourceValue(ResourceType.SERVANT, 0);
        ActionPlaceOnCouncil action = new ActionPlaceOnCouncil(player, familyMember, councilPalace);
        assertFalse(action.isValid(match));
    }

    @Test
    public void testC(){
        ActionPlaceOnCouncil action = new ActionPlaceOnCouncil(player, familyMember, councilPalace);
        ActionPlaceOnCouncil action1 = new ActionPlaceOnCouncil(player, familyMember1, councilPalace);
        assertTrue(action.equals(action));
        assertTrue(action.equals(action1));
    }
}
