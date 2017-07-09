package it.polimi.ingsw.gc_12.EffectsTests;

import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;
import it.polimi.ingsw.gc12.model.effect.EffectPlacementDenied;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.match.Match;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.*;

public class PlacementDeniedTest {

    Match match = mock(Match.class);
    Event event = mock(Event.class);
    EffectPlacementDenied effect;

    @Test
    public void testConstructor(){
        try{
            effect = new EffectPlacementDenied(event);
            assertEquals(event, effect.getEvent());
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test(expected = ActionDeniedException.class)
    public void testExecute() throws ActionDeniedException {
        effect = new EffectPlacementDenied(event);
        effect.execute(match, event, false);
        effect.discard(match, event);
    }

}
