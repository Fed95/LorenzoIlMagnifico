package it.polimi.ingsw.gc_12.ActionTests;
import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.action.ActionChooseTower;
import it.polimi.ingsw.gc12.model.board.occupiable.Tower;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventTowerChosen;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class ChooseTowerTest {

    Match match = mock(Match.class);
    ActionHandler actionHandler = mock(ActionHandler.class);
    Player player = InstanceCreator.createMockPlayer();
    FamilyMember familyMember = mock(FamilyMember.class);
    Tower tower = mock(Tower.class);
    ActionChooseTower action = new ActionChooseTower(player, familyMember, tower);
    ActionChooseTower action1 = new ActionChooseTower(player, familyMember, null);


    @Test
    public void testEquals(){
        assertTrue(action.equals(action));
        assertFalse(action.equals(action1));
        assertTrue(action1.equals(action1));
    }

    @Test
    public void testStart(){
        Event event = new EventTowerChosen(player, familyMember, tower);
        when(match.getActionHandler()).thenReturn(actionHandler);
        action.start(match);
        verify(actionHandler, times(1)).update(event, match);
    }
}
