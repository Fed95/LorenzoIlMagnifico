package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.event.EventChooseExchange;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.resource.ResourceExchange;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ChooseExchangeTest {

    Player player = InstanceCreator.createMockPlayer();
    List<ResourceExchange> exchanges = new ArrayList<>();
    EventChooseExchange event;

    @Test
    public void testMisc(){
        event = new EventChooseExchange(player, exchanges);
        assertEquals(exchanges, event.getExchanges());
        assertEquals("Choose the exchange you would like to activate", event.toString());
    }
}
