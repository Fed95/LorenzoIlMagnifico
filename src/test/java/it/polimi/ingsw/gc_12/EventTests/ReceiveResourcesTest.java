package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventReceiveResource;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.resource.Money;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.Stone;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.mock;

public class ReceiveResourcesTest {

    Match match = mock(Match.class);
    Player player = InstanceCreator.createMockPlayer();
    Resource money = new Money(1);
    Resource stone = new Stone(1);
    EventReceiveResource event;
    EventReceiveResource event1;
    EventReceiveResource event2;

    @Test
    public void testConstructor(){
        try{
            event = new EventReceiveResource(player, money);
            assertEquals(player, event.getPlayer());
            assertEquals(money, event.getResource());

            event = new EventReceiveResource(money);
            assertEquals(money, event.getResource());

            event.toString();
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testEquals(){
        event = new EventReceiveResource(player, money);
        event1 = new EventReceiveResource(player, stone);
        event2 = new EventReceiveResource(money);
        Event e = mock(Event.class);

        assertTrue(event.equals(event));
        assertTrue(event.equals(event2));
        assertFalse(event.equals(event1));
        assertFalse(event.equals(e));
    }
}
