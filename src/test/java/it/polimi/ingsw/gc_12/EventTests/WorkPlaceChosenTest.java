package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.event.EventWorkplaceChosen;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class WorkPlaceChosenTest {

    Match match = InstanceCreator.createMatch(4);
    Player player = match.getPlayer("p0");
    FamilyMember familyMember = player.getFamilyMember(FamilyMemberColor.BLACK);
    EventWorkplaceChosen event;

    @Test
    public void testConstructor(){
        try {
            event = new EventWorkplaceChosen(player, familyMember);
            assertEquals(player, event.getPlayer());
            assertEquals(familyMember, event.getFamilyMember());
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testSetActions(){
        familyMember.setValue(7);
        event = new EventWorkplaceChosen(player, familyMember);
        event.setActions(match);
        assertEquals(5, event.getActions().size());
    }
}
