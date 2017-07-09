package it.polimi.ingsw.gc_12.EffectsTests;

import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;
import it.polimi.ingsw.gc12.model.effect.EffectSetFamilyMemberValue;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventActivateLeaderCard;
import it.polimi.ingsw.gc12.model.event.EventSetFamilyMemberValue;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.*;

public class SetFamilyMemberValueTest {

    Match match = mock(Match.class);
    ActionHandler actionHandler = mock(ActionHandler.class);
    Player player = InstanceCreator.createMockPlayer();
    EventActivateLeaderCard event = mock(EventActivateLeaderCard.class);
    Event illegalEvent = mock(Event.class);
    int value = 6;
    EffectSetFamilyMemberValue effect;


    @Test
    public void testConstructor(){
        try {
            effect = new EffectSetFamilyMemberValue(event, value);
            assertEquals(event, effect.getEvent());
            effect.toString();
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testIllegalExecute() throws ActionDeniedException {
        effect = new EffectSetFamilyMemberValue(illegalEvent, value);
        effect.execute(match, illegalEvent, false);
    }

    @Test
    public void testExecute(){
        try {
            when(match.getActionHandler()).thenReturn(actionHandler);
            when(event.getPlayer()).thenReturn(player);
            effect = new EffectSetFamilyMemberValue(event, value);
            effect.execute(match, event, false);
            effect.discard(match, event);
            EventSetFamilyMemberValue event = new EventSetFamilyMemberValue(player, value);
            verify(actionHandler, times(1)).update(event, match);
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
}
