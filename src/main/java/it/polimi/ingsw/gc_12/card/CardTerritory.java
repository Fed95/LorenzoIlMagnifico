package it.polimi.ingsw.gc_12.card;

import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.resource.Resource;

public class CardTerritory extends CardDevelopment {

	public CardTerritory(int id, String name, int period, List<Resource> requirements, List<Effect> effects) {
		super(id, CardType.TERRITORY, name, period, requirements, effects);
	}

}
