package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.board.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc12.model.event.EventSupportDenied;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.mock;

public class SupportDeniedTest {

    Match match = mock(Match.class);
    Player player = InstanceCreator.createMockPlayer();
    ExcommunicationTile tile = mock(ExcommunicationTile.class);
    EventSupportDenied event;

    @Test
    public void testConstructor(){
        try {
            event = new EventSupportDenied(player, tile);
            assertEquals(player, event.getPlayer());
            assertEquals(tile, event.getTile());
            event.toString();
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
}
