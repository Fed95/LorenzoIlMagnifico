package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.effect.Effect;

import java.util.List;

public class BonusTile implements EffectProvider {

	private List<Effect> effects;

	public BonusTile(List<Effect> effects) {
		this.effects = effects;
	}

	@Override
	public List<Effect> getEffects() {
		return effects;
	}

	@Override
	public void setEffects(List<Effect> effects) {
		this.effects = effects;
	}
}
