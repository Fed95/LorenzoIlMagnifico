package it.polimi.ingsw.gc_12.ActionTests;

import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.action.ActionReceiveExcommunication;
import it.polimi.ingsw.gc12.model.board.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc_12.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ReceiveExcommunicationTest {

    Match match = mock(Match.class);
    ActionHandler actionHandler = mock(ActionHandler.class);
    Player player = InstanceCreator.createMockPlayer();
    ExcommunicationTile tile = mock(ExcommunicationTile.class);
    List<ExcommunicationTile> tiles = new ArrayList<>();
    List<Player> players = new ArrayList<>();
    ActionReceiveExcommunication action;

    @Test
    public void testStart(){
        when(player.getExcommunications()).thenReturn(tiles);
        when(match.getActionHandler()).thenReturn(actionHandler);
        when(actionHandler.getPlayers()).thenReturn(players);

        action = new ActionReceiveExcommunication(player, tile);
        action.start(match);
        assertTrue(tiles.contains(tile));
        verify(match, times(1)).newTurn(true);

        players.add(player);
        action.start(match);
        verify(match, times(1)).vaticanReport(players);

        action.toString();
    }
}
