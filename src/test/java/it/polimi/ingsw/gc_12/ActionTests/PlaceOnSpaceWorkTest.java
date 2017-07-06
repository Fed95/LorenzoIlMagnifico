package it.polimi.ingsw.gc_12.ActionTests;

import it.polimi.ingsw.gc12.model.action.ActionPlaceOnSpaceWork;
import it.polimi.ingsw.gc12.model.board.occupiable.SpaceWork;
import it.polimi.ingsw.gc12.model.board.occupiable.SpaceWorkZone;
import it.polimi.ingsw.gc12.model.board.occupiable.WorkType;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;
import it.polimi.ingsw.gc_12.*;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;

public class PlaceOnSpaceWorkTest {

    Match match = InstanceCreator.createMatch(4);
    Player player = match.getPlayer("p0");
    FamilyMember familyMember = player.getFamilyMember(FamilyMemberColor.BLACK);
    SpaceWorkZone zone = match.getBoard().getSpaceWorkZones().get(WorkType.HARVEST);
    SpaceWork spaceWork = zone.getSpaceWorks().get(0);//(SpaceWorkSingle)

    @Test
    public void testA(){
        familyMember.setValue(7);
        spaceWork.placeFamilyMember(familyMember);

        ActionPlaceOnSpaceWork action = new ActionPlaceOnSpaceWork(player, familyMember, spaceWork);
        assertFalse(action.isValid(match));
    }

    @Test
    public void testB(){
        familyMember.setValue(0);
        player.setResourceValue(ResourceType.SERVANT, 0);
        ActionPlaceOnSpaceWork action = new ActionPlaceOnSpaceWork(player, familyMember, spaceWork);
        assertFalse(action.isValid(match));
    }

    @Test
    public void testC(){
        SpaceWork spaceWork = zone.getSpaceWorks().get(1);
        spaceWork.placeFamilyMember(familyMember);

        ActionPlaceOnSpaceWork action = new ActionPlaceOnSpaceWork(player, familyMember, this.spaceWork);
        assertFalse(action.isValid(match));
    }
}
