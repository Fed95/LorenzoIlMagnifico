package it.polimi.ingsw.gc_12.card;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.resource.Resource;

import java.util.List;

public class CardVenture extends CardDevelopment{

	public CardVenture(int id, String name, int period, List<Resource> requirements, List<Effect> effects) {
		super(id, CardType.VENTURE, name, period, requirements, effects);
	}
}
