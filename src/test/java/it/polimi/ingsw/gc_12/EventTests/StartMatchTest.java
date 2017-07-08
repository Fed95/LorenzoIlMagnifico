package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.event.EventEndMatch;
import it.polimi.ingsw.gc12.model.event.EventStartMatch;
import it.polimi.ingsw.gc12.model.event.EventStartTurn;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.view.client.ClientHandler;
import it.polimi.ingsw.gc12.view.client.MatchInstance;
import it.polimi.ingsw.gc12.view.client.gui.MainBoardController;
import it.polimi.ingsw.gc_12.InstanceCreator;
import javafx.scene.control.TextArea;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StartMatchTest {


    Match match = mock(Match.class);
    EventStartMatch event;

    @Test
    public void testConstructor(){
        try {
            event = new EventStartMatch(match);
            assertEquals(match, event.getMatch());
        }catch(Exception e){
            fail(e.getMessage());
        }
    }


    @Test
    public void testExecuteClientSide(){
        ClientHandler clientHandler = mock(ClientHandler.class);
        MatchInstance matchInstance = mock(MatchInstance.class);

        when(clientHandler.createMatchInstance()).thenReturn(matchInstance);

        event = new EventStartMatch(match);

        try {
            event.executeClientSide(clientHandler);
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testToString(){
        try{
            event = new EventStartMatch(match);
            event.toString();
            event.toStringClient();
            event.toStringClientGUI();

            event = new EventStartMatch(match);
            event.toString();
            event.toStringClient();
            event.toStringClientGUI();

        }catch(Exception e){
            e.getMessage();
        }
    }
}
