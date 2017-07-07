package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.event.Event;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.view.client.ClientHandler;
import it.polimi.ingsw.gc_12.InstanceCreator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ViewTest {

    protected Match match = InstanceCreator.createMatch(2);

    public void testExecuteClientSide(Event event){
        ClientHandler clientHandler = mock(ClientHandler.class);
        when(clientHandler.isMyTurn()).thenReturn(true);
        event.executeClientSide(clientHandler);
    }
}
