package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.event.Event;

public abstract class Effect {
	
	protected Event event;
	
	public Effect(Event event) {
		this.event = event;
	}
	
	public Event getEvent() {
		return this.event;
	}
	
	public abstract void execute(Event event) throws RuntimeException;
	
	public abstract void discard(Event event) throws RuntimeException;
}
