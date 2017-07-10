package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.card.LeaderCard;
import it.polimi.ingsw.gc12.model.event.EventPlacementEnded;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.personalboard.LeaderCardsSpace;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlacementEndedTest {

    Match match = mock(Match.class);
    ActionHandler actionHandler = mock(ActionHandler.class);
    Player player = InstanceCreator.createMockPlayer();
    LeaderCardsSpace leaderCardsSpace = mock(LeaderCardsSpace.class);
    List<LeaderCard> leaderCards = new ArrayList<>();
    LeaderCard leaderCard = mock(LeaderCard.class);
    EventPlacementEnded event;

    @Test
    public void testSetActions(){
        leaderCards.add(leaderCard);
        when(player.getPersonalBoard().getLeaderCardsSpace()).thenReturn(leaderCardsSpace);
        when(leaderCardsSpace.getCards()).thenReturn(leaderCards);
        when(match.getActionHandler()).thenReturn(actionHandler);
        when(player.getPlayableLeaderCards()).thenReturn(leaderCards);
        when(player.getAvailableLeaderCards()).thenReturn(leaderCards);
        event = new EventPlacementEnded(player);

        try{
            event.setActions(match);
            event.toString();
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
}
