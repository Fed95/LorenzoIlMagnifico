package it.polimi.ingsw.gc_12.EventTests;

import it.polimi.ingsw.gc12.model.card.LeaderCard;
import it.polimi.ingsw.gc12.model.event.EventActivateLeaderCard;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActivateLeaderCardTest {

    Player player = InstanceCreator.createMockPlayer();
    LeaderCard card = mock(LeaderCard.class);
    int cardId = 0;
    int cardId1 = 1;
    EventActivateLeaderCard event;
    EventActivateLeaderCard event1;
    EventActivateLeaderCard event2;

    @Test
    public void testEquals(){
        when(card.getId()).thenReturn(cardId);
        event = new EventActivateLeaderCard(player, card);
        event1 = new EventActivateLeaderCard(cardId);
        event2 = new EventActivateLeaderCard(cardId1);

        assertTrue(event.equals(event));
        assertTrue(event.equals(event1));
        assertFalse(event.equals(event2));
    }
}
