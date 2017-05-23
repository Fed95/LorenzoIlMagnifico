package it.polimi.ingsw.gc_12.card;

import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.resource.Resource;

public abstract class CardDevelopment extends Card{
	private final CardType cardType;
	private final int period;
	
	public CardDevelopment(int id, CardType cardType, String name, int period, List<Resource> requirements, List<Effect> effects) {
		super(id, name, requirements, effects);
		this.cardType = cardType;
		this.period = period;
	}
}
