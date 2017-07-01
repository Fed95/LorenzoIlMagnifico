package it.polimi.ingsw.gc_12.card;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.event.EventPickCard;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CardDevelopment extends Card{
	private final CardType cardType;
	private final int period;
	
	public CardDevelopment(int id, CardType cardType, String name, int period, List<Resource> requirements, List<Effect> effects) {
		super(id, name, requirements, effects);
		this.cardType = cardType;
		this.period = period;
	}

	public CardDevelopment(CardType type){
		this(0, type, "generic", 0, null, null);
	}

	public int getPeriod(){
		return period;
	}

	public CardType getType(){
		return cardType;
	}

	public List<Effect> getImmediateEffects() {
		List<Effect> immediateeffects = new ArrayList<>();
		for(Effect effect : effects){
			if (effect.getEvent() instanceof EventPickCard)
				effects.add(effect);
		}
		return immediateeffects;
	}



	public List<Resource> getDiscountedRequirements(List<Resource> discounts){
		if(discounts.size() > 0) {

			Map<ResourceType, Resource> cardRequirements = new HashMap<>();
			List<Resource> requirements = new ArrayList<>(this.requirements);

			for (Resource requirement : requirements)
				cardRequirements.put(requirement.getType(), requirement);

			for (Resource resource : discounts) {

				ResourceType type = resource.getType();

				if (cardRequirements.containsKey(type)) {
					int currentValue = cardRequirements.get(type).getValue();
					int newValue = (currentValue - resource.getValue() < 0 ? 0 : currentValue - resource.getValue());
					cardRequirements.get(type).setValue(newValue);
				}
			}
			return requirements;
		}
		else{
			return requirements;
		}
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


