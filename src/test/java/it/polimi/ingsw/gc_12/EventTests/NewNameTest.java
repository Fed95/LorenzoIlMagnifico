package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.event.EventNewName;
import it.polimi.ingsw.gc12.view.client.ClientHandler;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class NewNameTest {

    ClientHandler clientHandler = mock(ClientHandler.class);
    int unauthorisedId = 0;
    String name = "name";
    EventNewName event;

    @Test
    public void testConstructor(){
        try{
            event = new EventNewName(unauthorisedId, name);
            assertEquals(unauthorisedId, event.getUnauthorizedId());
            assertEquals(name, event.getName());

            event = new EventNewName(unauthorisedId);
            event.setName(name);
            assertEquals(unauthorisedId, event.getUnauthorizedId());
            assertEquals(name, event.getName());
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testExecuteClientSide(){
        ClientHandler clientHandler = mock(ClientHandler.class);
        event = new EventNewName(unauthorisedId);
        event.executeClientSide(clientHandler);
        verify(clientHandler, times(1)).setUnauthorizedId(unauthorisedId);
    }
}
