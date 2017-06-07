package it.polimi.ingsw.gc_12.card;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.resource.Resource;

import java.util.List;

/**
 * Created by marco on 26/05/2017.
 */
public final class CardBuilder {

	private CardBuilder() {}

	public static CardDevelopment create(CardType cardType, int id, String name, int period, List<Resource> requirements, List<Effect> effects) throws IllegalArgumentException {
		switch (cardType) {
			case BUILDING:
				return new CardBuilding(id, name, period, requirements, effects);
			case CHARACTER:
				return new CardCharacter(id, name, period, requirements, effects);
			case TERRITORY:
				return new CardTerritory(id, name, period, requirements, effects);
			case VENTURE:
				return new CardVenture(id, name, period, requirements, effects);
			default:
				throw new IllegalArgumentException("The CardType does not exist.");
		}
	}
}
