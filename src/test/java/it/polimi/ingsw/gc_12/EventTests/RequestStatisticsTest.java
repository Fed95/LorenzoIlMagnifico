package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.event.EventRequestStatistics;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;

public class RequestStatisticsTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    EventRequestStatistics event;

    @Test
    public void testConstructor(){
        try{
            event = new EventRequestStatistics(player);
            assertEquals(player, event.getPlayer());
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testSetActions(){
        event = new EventRequestStatistics(player);
        event.setActions(match);

        assertEquals(3, event.getActions().size());
    }

    @Test
    public void testToString(){
        event = new EventRequestStatistics(player);
        try{
            event.toString();
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
}
