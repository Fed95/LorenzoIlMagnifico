package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.event.EventViewStatistics;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewStatisticsTest extends ViewTest {

    Player player0 = match.getPlayer("p0");
    Player player1 = match.getPlayer("p1");
    EventViewStatistics event;

    @Test
    public void testConstructor(){
        try{
            event = new EventViewStatistics(player0, player1);
            assertEquals(player1, event.getChosenPlayer());
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testOther(){
        event = new EventViewStatistics(player0, player1);
        try {
            event.toString();
            testExecuteClientSide(event);
        }catch (Exception e){
            fail(e.getMessage());
        }
    }
}
