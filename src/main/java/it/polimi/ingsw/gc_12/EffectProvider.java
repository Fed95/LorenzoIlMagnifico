package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;

public abstract class EffectProvider {
	
	protected List<Effect> effects = new ArrayList<>();
	private final ListEffectProvider listEffectProvider;
	
	public EffectProvider(List<Effect> effects, ListEffectProvider listEffectProvider) {
		this.effects = effects;
		this.listEffectProvider=listEffectProvider;
	}
	
	public List<Effect> getEffects() {
		return effects;
	}

}
