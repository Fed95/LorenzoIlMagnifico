package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.card.CardBuilding;
import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.card.CardType;
import it.polimi.ingsw.gc12.model.event.EventPickCard;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PickCardTest {

    Player player = InstanceCreator.createMockPlayer();
    CardDevelopment card = mock(CardBuilding.class);
    CardType type = CardType.BUILDING;
    int cardId = 0;
    EventPickCard event;

    @Test
    public void testConstructors(){
        try{
            event = new EventPickCard(player, card);
            event = new EventPickCard(type);
            event = new EventPickCard(cardId);
            event = new EventPickCard(card);
            event = new EventPickCard();
            assertTrue(event.isAnyCard());
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testEquals(){
        event = new EventPickCard();
        EventPickCard event1 = new EventPickCard(type);
        EventPickCard event2 = new EventPickCard(cardId);
        EventPickCard event3 = new EventPickCard(card);
        EventPickCard event4 = new EventPickCard(1);

        when(card.getType()).thenReturn(CardType.BUILDING);
        when(card.getId()).thenReturn(0);

        assertTrue(event1.equals(event));
        assertTrue(event2.equals(event));
        assertTrue(event3.equals(event));

        assertTrue(event1.equals(event3));
        assertTrue(event2.equals(event3));

        assertFalse(event2.equals(event4));
    }

    @Test
    public void testToString(){
        try{
            event = new EventPickCard(player, card);
            event.toString();
        }catch (Exception e){
            fail(e.getMessage());
        }
    }
}
