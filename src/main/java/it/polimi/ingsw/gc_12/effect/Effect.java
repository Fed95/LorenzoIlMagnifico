package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.event.Event;

public abstract class Effect {
	
	protected Event event;
	
	public Effect(Event action) {
		this.event = action;
	}
	
	public Event getEvent() {
		return this.event;
	}
	
	public abstract void execute(Event event);
	
	public abstract void discard(Event event);
}
