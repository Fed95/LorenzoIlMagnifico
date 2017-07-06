package it.polimi.ingsw.gc_12.ActionTests;

import it.polimi.ingsw.gc12.model.action.ActionDiscardLeaderCard;
import it.polimi.ingsw.gc12.model.card.LeaderCard;
import it.polimi.ingsw.gc12.model.event.EventCouncilPrivilegeReceived;
import it.polimi.ingsw.gc12.model.event.EventStartTurn;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.view.server.ServerRMIView;
import it.polimi.ingsw.gc12.view.server.ServerSocketView;
import it.polimi.ingsw.gc_12.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DiscardLeaderCardTest {

    Match match = InstanceCreator.createMatch(2);
    ServerRMIView rmiView = mock(ServerRMIView.class);
    ServerSocketView socketView = mock(ServerSocketView.class);
    Player player = match.getPlayer("p0");
    LeaderCard card = (LeaderCard) InstanceCreator.getCard("Santa Rita");

    @Test
    public void testStart(){
        match.registerObserver(rmiView);
        match.registerObserver(socketView);
        ActionDiscardLeaderCard action = new ActionDiscardLeaderCard(player, card);
        action.start(match);

        assertTrue(match.getActionHandler().getEvents().getFirst() instanceof EventCouncilPrivilegeReceived);
        verify(rmiView, times(1)).update(match.getActionHandler().getEvents().getFirst());
        verify(socketView, times(1)).update(match.getActionHandler().getEvents().getFirst());
        verify(rmiView, times(1)).update(match.getActionHandler().getEvents().getFirst());
        verify(socketView, times(1)).update(match.getActionHandler().getEvents().getFirst());


        match.getActionHandler().getAvailableAction(0);

        assertTrue(match.getActionHandler().getEvents().getFirst() instanceof EventStartTurn);
        assertTrue(action.equals(action));
    }
}
