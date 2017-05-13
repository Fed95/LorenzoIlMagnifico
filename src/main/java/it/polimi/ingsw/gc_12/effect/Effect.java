package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.action.Action;

public abstract class Effect implements EffectInterface {
	
	protected Action action;
	
	public Effect(Action action) {
		this.action = action;
	}
	
	public Action getAction() {
		return this.action;
	}
}
