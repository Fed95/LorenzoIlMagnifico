package it.polimi.ingsw.gc_12.card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.resource.Resource;

public class Card implements EffectProvider, Serializable {
	private List<Effect> effects = new ArrayList<>();
	private final String name;
	private final int id;
	private List<Resource> requirements;

	public Card(int id, String name, List<Resource> requirements, List<Effect> effects){
		this.id = id;
		this.name = name;
		this.requirements = requirements;
		this.effects = effects;
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
	
	/*@Override
	public String toString() {
		return name;
	}*/

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("		" + name).append(System.getProperty("line.separator"));
		sb.append("		Requirements: " + requirements).append(System.getProperty("line.separator"));
		sb.append("		Effects: ").append(System.getProperty("line.separator"));
		for(Effect effect : effects)
			sb.append("           " + effect).append(System.getProperty("line.separator"));
		sb.append(System.getProperty("line.separator"));
		return sb.toString();
	}
}
