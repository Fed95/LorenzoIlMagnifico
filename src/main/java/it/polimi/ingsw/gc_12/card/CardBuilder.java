package it.polimi.ingsw.gc_12.card;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.resource.Resource;

import java.util.List;

/**
 * Created by marco on 26/05/2017.
 */
public final class CardBuilder {

	private CardBuilder() {}

	public static CardDevelopment create(CardType cardType, int id, String name, int period, List<Resource> requirements, List<Effect> effects){
		CardDevelopment card;

		switch (cardType) {
			case BUILDING:
				card = new CardBuilding(id, name, period, requirements, effects);
				break;
			case CHARACTER:
				card = new CardCharacter(id, name, period, requirements, effects);
				break;
			case TERRITORY:
				card = new CardTerritory(id, name, period, requirements, effects);
				break;
			case VENTURE:
				card = new CardTerritory(id, name, period, requirements, effects);
				break;
			default:
				throw new IllegalArgumentException("The CardType does not exist.");
		}
		return card;
	}
}
