package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.event.EventEndMatch;
import it.polimi.ingsw.gc12.model.event.EventExcluded;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.view.client.ClientHandler;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.*;

public class ExcludedTest {

    Player player = InstanceCreator.createMockPlayer();
    EventExcluded event;

    @Test
    public void testConstructor(){
        try {
            event = new EventExcluded(player);
            assertEquals(player, event.getPlayer());
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testExecuteClientSide(){
        ClientHandler clientHandler = mock(ClientHandler.class);
        when(clientHandler.getColor()).thenReturn(PlayerColor.BLUE);
        when(player.getColor()).thenReturn(PlayerColor.BLUE);
        event = new EventExcluded(player);
        event.executeClientSide(clientHandler);
        verify(clientHandler, times(1)).setExcluded(true);
    }
}
