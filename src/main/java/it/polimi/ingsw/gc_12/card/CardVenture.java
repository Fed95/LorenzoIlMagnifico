package it.polimi.ingsw.gc_12.card;

import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.resource.Resource;

public class CardVenture extends CardDevelopment{

	public CardVenture(int id, String name, int period, List<Resource> requirements, List<Effect> effects) {
		super(id, CardDevelopmentType.VENTURE, name, period, requirements, effects);
	}

}
