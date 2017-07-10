package it.polimi.ingsw.gc12.model.card;

import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.player.resource.Resource;

import java.util.List;

/**
 * This class is responsible for creates empty cards used for json file generation
 */
public final class CardBuilder {

	private CardBuilder() {}

	public static Card create(CardType cardType, int id, String name, int period, List<Resource> requirements, List<Effect> effects) throws IllegalArgumentException {
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
	public static Card create(CardType cardType) throws IllegalArgumentException {
		return create(cardType, 0, "generic", 0, null, null);
	}
}
