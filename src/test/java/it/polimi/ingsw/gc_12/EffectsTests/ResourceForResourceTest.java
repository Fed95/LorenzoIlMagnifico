package it.polimi.ingsw.gc_12.EffectsTests;

import it.polimi.ingsw.gc12.model.effect.EffectResourceForResource;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.resource.Money;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;
import it.polimi.ingsw.gc_12.InstanceCreator;
import org.junit.Test;


import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResourceForResourceTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    Event event = mock(Event.class);
    Resource ownedResource = new Money(4);
    Resource resource = new Money(1);
    EffectResourceForResource effect;

    @Test
    public void testConstructor(){
        try {
            effect = new EffectResourceForResource(event, ownedResource, resource);
            assertEquals(event, effect.getEvent());
            effect.toString();
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testExecute(){
        player.setResourceValue(ResourceType.MONEY, 15);
        when(event.getPlayer()).thenReturn(player);

        effect = new EffectResourceForResource(event, ownedResource, resource);
        effect.execute(match, event, false);
        effect.discard(match, event);
        assertEquals(java.util.Optional.of(18), java.util.Optional.ofNullable(player.getResourceValue(ResourceType.MONEY)));

        resource.setValue(-5);
        effect = new EffectResourceForResource(event, ownedResource, resource);
        effect.execute(match, event, false);
        assertEquals(java.util.Optional.of(0), java.util.Optional.ofNullable(player.getResourceValue(ResourceType.MONEY)));
    }
}
