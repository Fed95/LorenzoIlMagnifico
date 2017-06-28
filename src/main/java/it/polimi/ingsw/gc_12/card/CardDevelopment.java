package it.polimi.ingsw.gc_12.card;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.resource.Resource;

import java.util.List;

public abstract class CardDevelopment extends Card{
	private final CardType cardType;
	private final int period;
	
	public CardDevelopment(int id, CardType cardType, String name, int period, List<Resource> requirements, List<Effect> effects) {
		super(id, name, requirements, effects);
		this.cardType = cardType;
		this.period = period;
	}

	public int getPeriod(){
		return period;
	}

	public CardType getType(){
		return cardType;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("		" + name).append(System.getProperty("line.separator"));
		if(this instanceof CardVenture && ((CardVenture) this).hasChoice()) {
			sb.append("		(This card has a cost choice)").append(System.getProperty("line.separator"));
			sb.append("		Cost: [" + ((CardVenture) this).getMilitaryCost() + "] (Required: " + ((CardVenture) this).getMilitaryRequirement() + ")").append(System.getProperty("line.separator"));
		}
		sb.append("		Cost: " + requirements).append(System.getProperty("line.separator"));
		sb.append("		Effects: ").append(System.getProperty("line.separator"));
		for(Effect effect : effects)
			sb.append("         - " + effect).append(System.getProperty("line.separator"));
		sb.append(System.getProperty("line.separator"));
		return sb.toString();
	}
}


