package it.polimi.ingsw.gc_12.card;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Card implements EffectProvider, Serializable {

	private final int id;
	protected final String name;
	protected List<Resource> requirements;
	protected List<Effect> effects = new ArrayList<>();

	public Card(int id, String name, List<Resource> requirements, List<Effect> effects){
		this.id = id;
		this.name = name;
		this.requirements = requirements;
		this.effects = effects;
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

	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public List<Resource>  getRequirements() {
		return requirements;
	}

	public List<Effect> getEffects() {
		return effects;
	}
}
