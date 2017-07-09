package it.polimi.ingsw.gc_12.EffectsTests;

import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;
import it.polimi.ingsw.gc12.model.card.Card;
import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.effect.EffectNoRequisites;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventPickCard;
import it.polimi.ingsw.gc12.model.match.Match;
import org.junit.Test;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

public class NoRequisitesTest {

    Match match = mock(Match.class);
    Event illegalEvent = mock(Event.class);
    EventPickCard eventPickCard = mock(EventPickCard.class);
    CardDevelopment card = mock(CardDevelopment.class);
    EffectNoRequisites effect;

    @Test
    public void testConstructor(){
        try{
            effect = new EffectNoRequisites(eventPickCard);
            assertEquals(eventPickCard, effect.getEvent());
            effect.toString();
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testIlligalEvent() throws ActionDeniedException {
        effect = new EffectNoRequisites(illegalEvent);
        effect.execute(match, illegalEvent, false);
    }

    @Test
    public void testExecute(){
        when(eventPickCard.getCard()).thenReturn(card);
        effect = new EffectNoRequisites(eventPickCard);
        try {
            effect.execute(match, eventPickCard, false);
        } catch (ActionDeniedException e) {
            fail(e.getMessage());
        }
        verify(card, times(1)).setNoRequisites(true);

        effect.discard(match, eventPickCard);
        verify(card, times(1)).setNoRequisites(false);
    }
}
