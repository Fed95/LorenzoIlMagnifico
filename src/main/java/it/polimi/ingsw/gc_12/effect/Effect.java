package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.exceptions.ActionDeniedException;

import java.io.Serializable;

public abstract class Effect implements Serializable {
	
	protected Event event;
	
	public Effect(Event event) {
		this.event = event;
	}
	
	public Event getEvent() {
		return this.event;
	}

	public abstract void execute(Match match, Event event, boolean validation) throws ActionDeniedException;

	public void execute(Match match, Event event) throws ActionDeniedException {
		execute(match, event, false);
	}
	
	public abstract void discard(Event event) ;
}
