package it.polimi.ingsw.gc_12.ActionTests;

import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.action.ActionChooseWorkplace;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventWorkplaceChosen;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ChooseWorkPlaceTest {

    Match match = mock(Match.class);
    ActionHandler actionHandler = mock(ActionHandler.class);
    Player player = InstanceCreator.createMockPlayer();
    FamilyMember familyMember = mock(FamilyMember.class);
    ActionChooseWorkplace action = new ActionChooseWorkplace(player, familyMember);
    ActionChooseWorkplace action1 = new ActionChooseWorkplace(player, null);

    @Test
    public void testEquals(){
        assertTrue(action.equals(action));
        assertTrue(action.equals(action1));
        assertTrue(action1.equals(action1));
    }

    @Test
    public void testStart(){
        Event event = new EventWorkplaceChosen(player, familyMember);
        when(match.getActionHandler()).thenReturn(actionHandler);
        action.start(match);
        verify(actionHandler, times(1)).update(event, match);
    }
}
