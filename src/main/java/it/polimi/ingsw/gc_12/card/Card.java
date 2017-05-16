package it.polimi.ingsw.gc_12.card;

import java.util.List;

import it.polimi.ingsw.gc_12.EffectProvider;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.resource.Resource;

public class Card extends EffectProvider {
	private final String name;
	private final int id;
	private Resource[] requirements;

	public Card(int id, String name, Resource[] requirements, List<Effect> effects){
		super(effects);
		this.id = id;
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
	
	@Override
	public String toString() {
		return name;
	}
}
