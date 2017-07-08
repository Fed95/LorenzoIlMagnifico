package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.event.EventSetFamilyMemberValue;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class SetFamilyMemberValueTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    EventSetFamilyMemberValue event;

    @Test
    public void testConstructor(){
        try{
            event = new EventSetFamilyMemberValue(player, 0);
            assertEquals(player, event.getPlayer());
            assertEquals(0, event.getValue());

            event = new EventSetFamilyMemberValue(player, 1);
            assertEquals(player, event.getPlayer());
            assertEquals(1, event.getValue());
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testInvalidConstructor(){
        event = new EventSetFamilyMemberValue(player, -1);
    }

    @Test
    public void testToString(){
        event = new EventSetFamilyMemberValue(player, 1);
        try{
            event.toString();
        }catch(Exception e){
            Assert.fail(e.getMessage());
        }
    }
}
