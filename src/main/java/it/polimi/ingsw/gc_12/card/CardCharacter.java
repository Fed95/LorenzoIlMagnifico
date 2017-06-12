package it.polimi.ingsw.gc_12.card;

import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;

public class CardCharacter extends CardDevelopment {

	private ResourceExchange militaryExchange;

	public CardCharacter(int id, String name, int period, List<Resource> requirements, List<Effect> effects) {
		super(id, CardType.CHARACTER, name, period, requirements, effects);
	}

	public ResourceExchange getMilitaryExchange() {
		return militaryExchange;
	}
}
