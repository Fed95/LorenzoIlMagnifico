package it.polimi.ingsw.gc_12.EffectsTests;

import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;
import it.polimi.ingsw.gc12.model.board.occupiable.Occupiable;
import it.polimi.ingsw.gc12.model.board.occupiable.TowerFloor;
import it.polimi.ingsw.gc12.model.card.Card;
import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.effect.EffectNoRequisites;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventPickCard;
import it.polimi.ingsw.gc12.model.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import org.junit.Test;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

public class NoRequisitesTest {

    Match match = mock(Match.class);
    Event illegalEvent = mock(Event.class);
    EventPlaceFamilyMember event = mock(EventPlaceFamilyMember.class);
    TowerFloor occupiable = mock(TowerFloor.class);
    CardDevelopment card = mock(CardDevelopment.class);
    String description = "test";
    EffectNoRequisites effect;

    @Test
    public void testConstructor(){
        try{
            effect = new EffectNoRequisites(event, description);
            assertEquals(event, effect.getEvent());
            effect.toString();
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testIlligalEvent() throws ActionDeniedException {
        effect = new EffectNoRequisites(illegalEvent, description);
        effect.execute(match, illegalEvent, false);
    }

    @Test
    public void testExecute(){
        when(event.getOccupiable()).thenReturn(occupiable);
        when(occupiable.getCard()).thenReturn(card);
        effect = new EffectNoRequisites(event, description);
        try {
            effect.execute(match, event, false);
        } catch (ActionDeniedException e) {
            fail(e.getMessage());
        }
        verify(card, times(1)).setNoRequisites(true);

        effect.discard(match, event);
        verify(card, times(1)).setNoRequisites(false);
    }
}
