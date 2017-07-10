package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.event.EventFamilyMemberChosen;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class FamilyMemberChosenTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    FamilyMember familyMember = player.getFamilyMember(FamilyMemberColor.BLACK);
    EventFamilyMemberChosen event;

    @Test
    public void testSetActions(){
        try {
            match.start();
            familyMember.setValue(7);
            event = new EventFamilyMemberChosen(player, familyMember);
            assertEquals(familyMember, event.getFamilyMember());

            event.setActions(match);
            assertEquals(8, event.getActions().size());

            event.toString();
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
}
