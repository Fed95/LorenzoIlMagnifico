package it.polimi.ingsw.gc_12.EffectsTests;

import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;
import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.effect.EffectChangeResource;
import it.polimi.ingsw.gc12.model.effect.EffectDenyExchange;
import it.polimi.ingsw.gc12.model.effect.EffectMultiplyCardResourceBonus;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventPickCard;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.resource.*;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MunliplyCardRescourceBonusTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    String description = "test";
    Event event = mock(Event.class);
    EventPickCard eventPickCard = mock(EventPickCard.class);
    List<ResourceType> types = new ArrayList<>();
    int multiplier = 2;
    ResourceExchange exchange = new ResourceExchange(new Money(3), new Stone(1));
    CardDevelopment card = InstanceCreator.createEmptyDevelopmentCard();
    EffectMultiplyCardResourceBonus effect;

    @Test
    public void testConstructor(){
        try {
            effect = new EffectMultiplyCardResourceBonus(event, types, multiplier);
            assertEquals(event, effect.getEvent());
            assertEquals(types, effect.getTypes());
            assertEquals(multiplier, effect.getMultiplier());

            effect = new EffectMultiplyCardResourceBonus(event, null, multiplier);
            assertEquals(event, effect.getEvent());
            assertEquals(multiplier, effect.getMultiplier());
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testIllegalState() throws ActionDeniedException {
        effect = new EffectMultiplyCardResourceBonus(event, types, multiplier);
        effect.execute(match, event, false);
    }

    @Test
    public void testExecute(){
        for(Resource resource : player.getResources().values()) {
            resource.setValue(0);
            types.add(resource.getType());
        }
        when(eventPickCard.getCard()).thenReturn(card);
        when(eventPickCard.getCardID()).thenReturn(card.getId());
        when(eventPickCard.getPlayer()).thenReturn(player);
        card.getEffects().add(new EffectChangeResource(eventPickCard, exchange, false));
        effect = new EffectMultiplyCardResourceBonus(event, types, multiplier);
        try {
            effect.execute(match, eventPickCard, false);
            effect.discard(match, eventPickCard);
            assertEquals(java.util.Optional.of(1), java.util.Optional.ofNullable(player.getResourceValue(ResourceType.STONE)));
        } catch (ActionDeniedException e) {
            fail(e.getMessage());
        }
    }
}
