package it.polimi.ingsw.gc_12.EffectsTests;

import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.effect.EffectReduceReceivedResource;
import it.polimi.ingsw.gc12.model.effect.EffectResourceForCards;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventReceiveResource;
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

public class ReduceReceivedResourceTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    int value = 1;
    Event invalidEvent = mock(Event.class);
    EventReceiveResource event = mock(EventReceiveResource.class);
    CardDevelopment card = InstanceCreator.createEmptyDevelopmentCard();//Building type
    Resource resource = new Money(10);
    EffectReduceReceivedResource effect;

    @Test(expected = IllegalStateException.class)
    public void testIllegalConstructor(){
        effect = new EffectReduceReceivedResource(invalidEvent, value);
    }

    @Test
    public void testConstructor(){
        try {
            when(event.getResource()).thenReturn(resource);
            effect = new EffectReduceReceivedResource(event, value);
            assertEquals(event, effect.getEvent());
            assertEquals(ResourceType.MONEY, effect.getResourceType());
            effect.toString();
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testExecute(){
        when(event.getResource()).thenReturn(resource);
        effect = new EffectReduceReceivedResource(event, value);
        effect.execute(match, event, false);
        effect.discard(match, event);

        assertEquals(11, resource.getValue());
    }
}
