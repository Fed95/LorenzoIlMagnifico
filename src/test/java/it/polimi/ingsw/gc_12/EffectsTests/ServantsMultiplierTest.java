package it.polimi.ingsw.gc_12.EffectsTests;

import it.polimi.ingsw.gc12.model.effect.EffectServantsMultiplier;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc12.model.event.EventServantsRequested;
import it.polimi.ingsw.gc12.model.match.Match;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ServantsMultiplierTest {

    Match match = mock(Match.class);
    Event illegalEvent = mock(Event.class);
    EventServantsRequested eventServantsRequested = mock(EventServantsRequested.class);
    EventPlaceFamilyMember eventPlaceFamilyMember = mock(EventPlaceFamilyMember.class);
    int value = 3;
    EffectServantsMultiplier effect;


    @Test
    public void testConstructor(){
        try{
            effect = new EffectServantsMultiplier(eventPlaceFamilyMember, value);
            assertEquals(eventPlaceFamilyMember, effect.getEvent());
            assertEquals(value, effect.getValue());

            effect = new EffectServantsMultiplier(eventServantsRequested, value);
            assertEquals(eventServantsRequested, effect.getEvent());
            assertEquals(value, effect.getValue());

            effect.toString();
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testIllegalEvent(){
        effect = new EffectServantsMultiplier(illegalEvent, value);
        effect.execute(match, illegalEvent, false);
    }

    @Test
    public void testExecute(){
        effect = new EffectServantsMultiplier(eventServantsRequested, value);
        effect.execute(match, eventServantsRequested, false);
        verify(eventServantsRequested, times(1)).setMultiplier(value);

        effect = new EffectServantsMultiplier(eventPlaceFamilyMember, value);
        effect.execute(match, eventPlaceFamilyMember, false);
        verify(eventPlaceFamilyMember, times(1)).setMultiplier(value);
    }
}
