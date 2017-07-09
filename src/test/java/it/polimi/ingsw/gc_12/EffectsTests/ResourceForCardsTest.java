package it.polimi.ingsw.gc_12.EffectsTests;

import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.card.CardType;
import it.polimi.ingsw.gc12.model.effect.EffectResourceForCards;
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

public class ResourceForCardsTest {

    Match match = InstanceCreator.createMatch(2);
    Player player = match.getPlayer("p0");
    Event event = mock(Event.class);
    CardType cardType = CardType.BUILDING;
    CardDevelopment card = InstanceCreator.createEmptyDevelopmentCard();//Building type
    Resource resource = new Money(1);
    EffectResourceForCards effect;

    @Test
    public void testConstructor(){
        try {
            effect = new EffectResourceForCards(event, cardType, resource);
            assertEquals(event, effect.getEvent());
            effect.toString();
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testExecute(){
        for(Resource resource : player.getResources().values())
            resource.setValue(0);
        player.getPersonalBoard().placeCard(card);
        player.getPersonalBoard().placeCard(card);
        when(event.getPlayer()).thenReturn(player);

        effect = new EffectResourceForCards(event, cardType, resource);
        effect.execute(match, event, false);
        effect.discard(match, event);
        assertEquals(java.util.Optional.of(2), java.util.Optional.ofNullable(player.getResourceValue(ResourceType.MONEY)));
    }
}
