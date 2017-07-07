package it.polimi.ingsw.gc_12.ActionTests;

import it.polimi.ingsw.gc12.model.action.DiscardAction;
import it.polimi.ingsw.gc12.model.card.LeaderCard;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class DiscardActionTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    LeaderCard card = (LeaderCard) InstanceCreator.getCard("Santa Rita");
    LeaderCard card1 = (LeaderCard) InstanceCreator.getCard("Cesare Borgia");
    DiscardAction action = new DiscardAction(player, card);
    DiscardAction action1 = new DiscardAction(player, card1);
    DiscardAction action2 = new DiscardAction(player, null);

    @Test
    public void testStart(){
        action.start(match);
        assertFalse(card.isPlayed());
    }

    @Test
    public void testEquals(){
        assertFalse(action.equals(action1));
        assertTrue(action.equals(action));
        assertFalse(action.equals(action2));
        assertTrue(action2.equals(action2));
    }
}
