package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.event.EventViewAvailableLeaderCards;
import it.polimi.ingsw.gc12.model.event.EventViewDiscardableLeaderCards;
import it.polimi.ingsw.gc12.model.event.EventViewPlayableLeaderCards;
import it.polimi.ingsw.gc12.model.player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ViewLeaderCardsTest extends ViewTest {

    Player player = match.getPlayer("p0");
    EventViewAvailableLeaderCards viewAvailableLeaderCards;
    EventViewDiscardableLeaderCards viewDiscardableLeaderCards;
    EventViewPlayableLeaderCards eventViewPlayableLeaderCards;

    @Test
    public void testConstructor(){
        try{
            viewAvailableLeaderCards = new EventViewAvailableLeaderCards(player);
            viewDiscardableLeaderCards = new EventViewDiscardableLeaderCards(player);
            eventViewPlayableLeaderCards = new EventViewPlayableLeaderCards(player);

            assertEquals(player, viewAvailableLeaderCards.getPlayer());
            assertEquals(player, viewDiscardableLeaderCards.getPlayer());
            assertEquals(player, eventViewPlayableLeaderCards.getPlayer());

        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testOther(){
        viewAvailableLeaderCards = new EventViewAvailableLeaderCards(player);
        viewDiscardableLeaderCards = new EventViewDiscardableLeaderCards(player);
        eventViewPlayableLeaderCards = new EventViewPlayableLeaderCards(player);

        try {
            viewAvailableLeaderCards.toString();
            viewDiscardableLeaderCards.toString();
            eventViewPlayableLeaderCards.toString();

            testExecuteClientSide(viewAvailableLeaderCards);
            testExecuteClientSide(viewDiscardableLeaderCards);
            testExecuteClientSide(eventViewPlayableLeaderCards);

        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testSetActions(){
        viewAvailableLeaderCards = new EventViewAvailableLeaderCards(player);
        viewAvailableLeaderCards.setActions(match);
        int expectedSize = player.getAvailableLeaderCards().size() + 1;
        assertEquals(expectedSize, viewAvailableLeaderCards.getActions().size());

        viewDiscardableLeaderCards = new EventViewDiscardableLeaderCards(player);
        viewDiscardableLeaderCards.setActions(match);
        expectedSize = player.getPersonalBoard().getLeaderCardsSpace().getCards().size() + 1;
        assertEquals(expectedSize, viewDiscardableLeaderCards.getActions().size());

        eventViewPlayableLeaderCards = new EventViewPlayableLeaderCards(player);
        eventViewPlayableLeaderCards.setActions(match);
        expectedSize = player.getPlayableLeaderCards().size() + 1;
        assertEquals(expectedSize, eventViewPlayableLeaderCards.getActions().size());
    }
}
