package it.polimi.ingsw.gc_12;

import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.resource.Resource;

public class Card extends EffectProvider {
	private final String name;
	private Resource[] requirements;

	public Card(String name, Resource[] requirements, List<Effect> effects){
		super(effects);
		this.name = name;
		this.requirements = requirements;
		this.effects = effects;
	}

	public String getName() {
		return name;
	}

	public Resource[] getRequirements() {
		return requirements;
	}
	
	public List<Effect> getEffects() {
		return effects;
	}
}
