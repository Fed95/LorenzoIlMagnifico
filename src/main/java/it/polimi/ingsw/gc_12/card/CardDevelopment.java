package it.polimi.ingsw.gc_12.card;

import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.resource.Resource;

public abstract class CardDevelopment extends Card{
	private final CardDevelopmentType cardDevelopmentType;
	private final int period;
	
	public CardDevelopment(int id, CardDevelopmentType cardDevelopmentType, String name, int period, List<Resource> requirements, List<Effect> effects) {
		super(id, name,CardType.DEVELOPMENT, requirements, effects);
		this.cardDevelopmentType = cardDevelopmentType;
		this.period = period;
	}

	public int getPeriod(){
		return period;
	}

	public CardDevelopmentType getType(){
		return cardDevelopmentType;
	}
}
