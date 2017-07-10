package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc12.misc.json.loader.LoaderCard;
import it.polimi.ingsw.gc12.model.card.Card;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventExcluded;
import it.polimi.ingsw.gc12.model.event.EventVaticanReport;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.match.MatchState;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.view.client.rmi.ClientViewRemote;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MatchTest {

    @Test
    public void testNewTurn(){
        Match match = InstanceCreator.createMatch(2);
        match.start();
        match.setTurnCounter(8);
        match.setRoundNum(2);
        match.newTurn(false);
        Event event = new EventVaticanReport(match.getBoard().getTrackTurnOrder().
                getOrderedPlayers().get(0), match.getBoard().getExcommunicationSpace().
                getTiles().get(match.getPeriodNum()), match.getBoard().getTrackTurnOrder().getOrderedPlayers());
        assertEquals(event, match.getActionHandler().getEvents().getLast());

        match = InstanceCreator.createMatch(2);
        match.start();
        match.setTurnCounter(1);
        Player player = match.getBoard().getTrackTurnOrder().newTurn();
        player.setDisconnected(true);
        match.newTurn(false);
        assertEquals(2, match.getTurnCounter());
    }

    @Test
    public void testExcludeAndIncludeCurrentPlayer(){
        Match match = InstanceCreator.createMatch(2);
        match.start();
        Player player = match.getBoard().getTrackTurnOrder().getCurrentPlayer();
        match.excludeCurrentPlayer();
        assertTrue(player.isExcluded());
        match.includePlayer(player);
        assertFalse(player.isExcluded());
    }

    @Test
    public void testEndMatch(){
        Match match = InstanceCreator.createMatch(2);
        match.start();
        match.setRoundNum(6);
        match.setTurnCounter(8);
        match.newTurn(true);
        assertEquals(MatchState.ENDED, match.getGameState());

        match.setGameState(MatchState.RUNNING);
        assertEquals(MatchState.RUNNING, match.getGameState());
    }

    @Test
    public void testSetPlayers(){
        Match match = InstanceCreator.createMatch(2);
        Map<PlayerColor, Player> players = new HashMap<>();
        match.setPlayers(players);
        assertEquals(players, match.getPlayers());
    }

    @Test
    public void testGetCards(){
        Match match = InstanceCreator.createMatch(2);
        List<Card> cards = new LoaderCard().get(match);
        List<Card> gameCards = match.getCards();
        for(Card card : cards)
            assertTrue(gameCards.contains(card));
    }

    @Test
    public void testToString(){
        Match match = InstanceCreator.createMatch(2);
        assertEquals(match.getBoard().toString(), match.toString());
    }

    @Test
    public void testSetDisconnectedReconnectedPlayer(){
        Match match = InstanceCreator.createMatch(2);
        match.start();
        match.setTurnCounter(1);
        Player player = match.getBoard().getTrackTurnOrder().getCurrentPlayer();
        match.setDisconnectedPlayer(player);
        assertTrue(player.isDisconnected());
        assertEquals(2, match.getTurnCounter());

        ClientViewRemote client = mock(ClientViewRemote.class);
        match.setReconnectedPlayer(player, client);
        assertFalse(player.isDisconnected());

        player.setDisconnected(true);
        match.setReconnectedPlayer(player);
        assertFalse(player.isDisconnected());
    }

    @Test
    public void testCheckEnoughPlayers(){
        Match match = InstanceCreator.createMatch(2);
        Player player = match.getPlayer("p0");
        match.start();
        match.setDisconnectedPlayer(player);
        assertEquals(MatchState.PAUSED, match.getGameState());

        match.setReconnectedPlayer(player);
        assertEquals(MatchState.RUNNING, match.getGameState());

    }
}
