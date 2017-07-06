package it.polimi.ingsw.gc_12.ActionTests;
import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.action.Action;
import it.polimi.ingsw.gc12.model.action.ActionReady;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ActionHandlerTest {

    Match match = mock(Match.class);
    Player player = mock(Player.class);
    ActionHandler actionHandler;
    Event event = mock(Event.class);
    Action action = mock(Action.class);

    @Before
    public void init(){
        actionHandler = new ActionHandler(match);
        when(event.getActions()).thenReturn(Collections.singletonList(action));
    }

    @Test
    public void testUpdate(){
        actionHandler.update(event, match);
        assertEquals(event, actionHandler.getEvents().getFirst());
    }

    @Test
    public void testAvailableAction(){
        actionHandler.getEvents().add(event);
        actionHandler.getEvents().add(event);
        assertTrue(actionHandler.getAvailableAction(0) instanceof ActionReady);
        assertEquals(action, actionHandler.getAvailableAction(0));

        actionHandler.setCouncilPrivileges(2);
        assertEquals(action, actionHandler.getAvailableAction(0));
    }

}
