package it.polimi.ingsw.gc_12.ActionTests;

import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.action.ActionViewAvailableLeaderCards;
import it.polimi.ingsw.gc12.model.action.ActionViewDiscardableLeaderCards;
import it.polimi.ingsw.gc12.model.action.ActionViewPlayableLeaderCards;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ViewLeaderCardsTest {

    Match match = mock(Match.class);
    ActionHandler actionHandler = mock(ActionHandler.class);
    Player player = InstanceCreator.createMockPlayer();
    ActionViewAvailableLeaderCards availableLeaderCards;
    ActionViewPlayableLeaderCards playableLeaderCards;
    ActionViewDiscardableLeaderCards discardableLeaderCards;

    @Test
    public void testMisc(){
        when(match.getActionHandler()).thenReturn(actionHandler);

        try {
            availableLeaderCards = new ActionViewAvailableLeaderCards(player);
            playableLeaderCards = new ActionViewPlayableLeaderCards(player);
            discardableLeaderCards = new ActionViewDiscardableLeaderCards(player);

            availableLeaderCards.start(match);
            playableLeaderCards.start(match);
            discardableLeaderCards.start(match);

            availableLeaderCards.toString();
            playableLeaderCards.toString();
            discardableLeaderCards.toString();
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
}
