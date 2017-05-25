package it.polimi.ingsw.gc_12.card;

import java.util.List;

import it.polimi.ingsw.gc_12.EffectProvider;
import it.polimi.ingsw.gc_12.ListEffectProvider;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.resource.Resource;

public abstract class Card extends EffectProvider {
	private final String name;
	private final int id;
	private List<Resource>  requirements;
	private final CardType type;
	
	public Card(int id, String name,CardType type, List<Resource> requirements, List<Effect> effects){
		super(effects,ListEffectProvider.CARD);
		this.id = id;
		this.name = name;
		this.requirements = requirements;
		this.effects = effects;
		this.type=type;
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
	
	@Override
	public String toString() {
		return name;
	}
}
