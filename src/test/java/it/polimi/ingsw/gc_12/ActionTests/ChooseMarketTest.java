package it.polimi.ingsw.gc_12.ActionTests;

import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.action.ActionChooseMarket;
import it.polimi.ingsw.gc12.model.board.occupiable.Market;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventMarketChosen;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ChooseMarketTest {

    Match match = mock(Match.class);
    ActionHandler actionHandler = mock(ActionHandler.class);
    Player player = InstanceCreator.createMockPlayer();
    FamilyMember familyMember = mock(FamilyMember.class);
    Market market = mock(Market.class);
    ActionChooseMarket action = new ActionChooseMarket(player, familyMember);
    ActionChooseMarket action1 = new ActionChooseMarket(player, null);

    @Test
    public void testEquals(){
        assertTrue(action.equals(action));
        assertTrue(action.equals(action1));
        assertTrue(action1.equals(action1));
    }

    @Test
    public void testStart(){
        Event event = new EventMarketChosen(player, familyMember);
        when(match.getActionHandler()).thenReturn(actionHandler);
        action.start(match);
        verify(actionHandler, times(1)).update(event, match);
    }
}
