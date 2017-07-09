package it.polimi.ingsw.gc_12.EffectsTests;

import it.polimi.ingsw.gc12.model.effect.EffectCopyLeaderCard;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.match.Match;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.mock;

public class CopyLeaderCardTest {

    Match match = mock(Match.class);
    Event event = mock(Event.class);
    int cardId = 0;
    EffectCopyLeaderCard effect;

    @Test
    public void testMisc(){
        try{
            effect = new EffectCopyLeaderCard(event, cardId);
            assertEquals(event, effect.getEvent());
            assertEquals(cardId, effect.getCardId());

            effect.discard(match, event);
            effect.toString();
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
}
