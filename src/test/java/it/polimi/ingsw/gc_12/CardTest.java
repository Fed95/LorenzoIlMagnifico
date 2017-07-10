package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc12.model.card.*;
import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.player.resource.MilitaryPoint;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceExchange;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

public class CardTest {

    List<Resource> resources = new ArrayList<>();
    List<Effect> effects = new ArrayList<>();
    ResourceExchange militaryExchange = new ResourceExchange(new MilitaryPoint(5), new MilitaryPoint(3));
    String name = "test";
    int value = 1;
    CardDevelopment card;

    @Test
    public void testConstructors(){

        card = new CardVenture(value, name, value, resources, militaryExchange, effects);
        assertEquals(CardType.VENTURE, card.getType());
        assertEquals(militaryExchange, ((CardVenture) card).getMilitaryExchange());
        assertEquals(new MilitaryPoint(2), ((CardVenture) card).getMilitaryCost());
        assertEquals(new MilitaryPoint(5), ((CardVenture) card).getMilitaryRequirement());

        card = new CardVenture(value, name, value, resources, effects);
        assertEquals(CardType.VENTURE, card.getType());

        card = new CardTerritory(value, name, value, resources, effects);
        assertEquals(CardType.TERRITORY, card.getType());

        card = new CardCharacter(value, name, value, resources, effects);
        assertEquals(CardType.CHARACTER, card.getType());
    }
}
