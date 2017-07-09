package it.polimi.ingsw.gc_12.EffectsTests;

import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;
import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.effect.EffectCardDiscount;
import it.polimi.ingsw.gc12.model.event.EventPickCard;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.resource.*;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CardDiscountTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    String description = "test";
    EventPickCard event = mock(EventPickCard.class);
    Resource resource = new Money(3);
    CardDevelopment card = InstanceCreator.createEmptyDevelopmentCard();
    EffectCardDiscount effect;

    @Test
    public void testConstructor(){
        try {
            effect = new EffectCardDiscount(event, resource, description);
            assertEquals(event, effect.getEvent());
            assertEquals(resource, effect.getResource());
            assertEquals(description, effect.toString());

        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testExecute(){
        card.getRequirements().add(resource);
        when(event.getCard()).thenReturn(card);
        when(event.getPlayer()).thenReturn(player);
        effect = new EffectCardDiscount(event, resource, description);

        for(Resource resource : player.getResources().values())
            resource.setValue(0);

        try {
            effect.execute(match, event, false);
            effect.discard(match, event);
        } catch (ActionDeniedException e) {
            fail(e.getMessage());
        }

        assertEquals(java.util.Optional.of(3), java.util.Optional.ofNullable(player.getResourceValue(ResourceType.MONEY)));
    }
}
