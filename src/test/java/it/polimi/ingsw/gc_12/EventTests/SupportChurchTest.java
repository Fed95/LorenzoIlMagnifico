package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.event.EventSupportChurch;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SupportChurchTest {

    Player player = InstanceCreator.createMockPlayer();
    EventSupportChurch event;

    @Test
    public void testToString(){
        event = new EventSupportChurch(player);

        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("line.separator"));
        sb.append(player.getName() + " has chosen to support the church.");

        assertEquals(sb.toString(), event.toString());
    }
}
