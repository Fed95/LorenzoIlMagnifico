package it.polimi.ingsw.gc_12.EffectsTests;

import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.effect.EffectChangeFamilyMemberValue;
import it.polimi.ingsw.gc12.model.effect.EffectChangeResource;
import it.polimi.ingsw.gc12.model.effect.EffectDenyExchange;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc12.model.player.resource.*;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DenyExchangeTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    String description = "test";
    Event event = mock(Event.class);
    ResourceExchange exchange = new ResourceExchange(new Money(3), new Stone(1));
    CardDevelopment card = InstanceCreator.createEmptyDevelopmentCard();
    EffectDenyExchange effect;


    @Test
    public void testConstructor(){
        try {
            effect = new EffectDenyExchange(event, card, description, false);
            assertEquals(event, effect.getEvent());
            assertEquals(card, effect.getEffectProvider());
            assertEquals(description, effect.getDescription());

            effect = new EffectDenyExchange(event, description, true);
            assertEquals(event, effect.getEvent());
            assertEquals(description, effect.getDescription());
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testExecuteAndDiscard(){
        when(event.getPlayer()).thenReturn(player);
        when(event.getEffectProviders()).thenReturn(Collections.singletonList(card));
        EffectChangeResource e = new EffectChangeResource(event, exchange, false);
        card.getEffects().add(e);

        for(Resource resource : player.getResources().values())
            resource.setValue(3);

        effect = new EffectDenyExchange(event, card, description, false);
        effect.execute(match, event, false);
        assertEquals(java.util.Optional.of(6), java.util.Optional.ofNullable(player.getResourceValue(ResourceType.MONEY)));
        assertEquals(java.util.Optional.of(3), java.util.Optional.ofNullable(player.getResourceValue(ResourceType.STONE)));

        effect.discard(match, event);
        assertEquals(java.util.Optional.of(3), java.util.Optional.ofNullable(player.getResourceValue(ResourceType.MONEY)));
        assertEquals(java.util.Optional.of(3), java.util.Optional.ofNullable(player.getResourceValue(ResourceType.STONE)));

        for(Resource resource : player.getResources().values())
            resource.setValue(3);

        effect = new EffectDenyExchange(event, card, description, true);
        effect.execute(match, event, false);
        assertEquals(java.util.Optional.of(3), java.util.Optional.ofNullable(player.getResourceValue(ResourceType.MONEY)));
        assertEquals(java.util.Optional.of(2), java.util.Optional.ofNullable(player.getResourceValue(ResourceType.STONE)));

        effect.discard(match, event);
        assertEquals(java.util.Optional.of(3), java.util.Optional.ofNullable(player.getResourceValue(ResourceType.MONEY)));
        assertEquals(java.util.Optional.of(3), java.util.Optional.ofNullable(player.getResourceValue(ResourceType.STONE)));
    }
}
