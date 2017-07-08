package it.polimi.ingsw.gc_12.EffectsTests;

import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;
import it.polimi.ingsw.gc12.model.effect.EffectChangeResource;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceExchange;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChangeResourceTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    List<Resource> resources = InstanceCreator.getResourceList();
    ResourceExchange exchange = new ResourceExchange(resources, null);
    ResourceExchange exchange1 = new ResourceExchange(resources, resources);
    ResourceExchange exchange2 = new ResourceExchange(null, resources);
    List<ResourceExchange> exchanges = new ArrayList<>();
    Event event = mock(Event.class);
    EffectChangeResource effect;

    @Test
    public void testConstructor(){
        try {
            effect = new EffectChangeResource(event, exchange, false);
            assertEquals(event, effect.getEvent());
            assertTrue(effect.getExchanges().contains(exchange));
            assertFalse(effect.hasChoice());

            effect = new EffectChangeResource(event, exchanges, true);
            assertEquals(event, effect.getEvent());
            assertEquals(exchanges, effect.getExchanges());
            assertTrue(effect.hasChoice());

        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testExecute(){
        for(ResourceType type : ResourceType.values())
            player.setResourceValue(type, 3);
        when(event.getPlayer()).thenReturn(player);

        try {
            test(false, false);
            test(false, true);
            test(true, true);

            effect = new EffectChangeResource(event, exchange, true);
            effect.execute(match, event, false);
            match.getActionHandler().getAvailableAction(0);
            for(ResourceType type : ResourceType.values())
                assertEquals(java.util.Optional.of(2), java.util.Optional.ofNullable(player.getResourceValue(type)));

        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    private void test(boolean choice, boolean validation) throws ActionDeniedException {
        effect = new EffectChangeResource(event, exchange, choice);
        effect.execute(match, event, validation);
        for(ResourceType type : ResourceType.values())
            assertEquals(java.util.Optional.of(2), java.util.Optional.ofNullable(player.getResourceValue(type)));
    }

    @Test
    public void testDiscard(){
        for(ResourceType type : ResourceType.values())
            player.setResourceValue(type, 3);
        when(event.getPlayer()).thenReturn(player);

        effect = new EffectChangeResource(event, exchange, true);
        effect.discard(match, event);
        for(ResourceType type : ResourceType.values())
            assertEquals(java.util.Optional.of(3), java.util.Optional.ofNullable(player.getResourceValue(type)));

        effect = new EffectChangeResource(event, exchange, false);
        effect.discard(match, event);
        for(ResourceType type : ResourceType.values())
            assertEquals(java.util.Optional.of(4), java.util.Optional.ofNullable(player.getResourceValue(type)));
    }
}
