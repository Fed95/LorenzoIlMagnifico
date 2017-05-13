package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.event.Event;

public abstract class Effect implements EffectInterface {
	
	protected Event event;
	
	public Effect(Event action) {
		this.event = action;
	}
	
	public Event getEvent() {
		return this.event;
	}
}
