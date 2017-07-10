package it.polimi.ingsw.gc_12.ActionTests;

import it.polimi.ingsw.gc12.model.action.Action;
import it.polimi.ingsw.gc12.model.action.ActionPassTurn;
import it.polimi.ingsw.gc12.model.action.ActionPlace;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PassTurnTest {

    Match match = mock(Match.class);
    Player player = mock(Player.class);
    Player player1 = mock(Player.class);
    ActionPassTurn action;
    ActionPassTurn action1;
    Action action2 = mock(Action.class);

    @Test
    public void testMisc(){
        action = new ActionPassTurn(player);
        action1 = new ActionPassTurn(player1);
        try{
            action.start(match);
            verify(match, times(1)).newTurn(false);
            assertFalse(action.isValid(match));
            action.toString();
            assertTrue(action.equals(action));
            assertTrue(action.equals(action1));
            assertFalse(action.equals(action2));
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
}
