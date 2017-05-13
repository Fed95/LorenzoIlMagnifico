package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;

public abstract class EffectProvider {
	
	protected List<Effect> effects = new ArrayList<>();

	public EffectProvider(List<Effect> effects) {
		this.effects = effects;
	}
	
	public List<Effect> getEffects() {
		return effects;
	}

}
