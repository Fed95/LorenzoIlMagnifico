package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.occupiables.Market;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;

public abstract class Effect implements Serializable {
	
	protected Event event;
	
	public Effect(Event event) {
		this.event = event;
	}
	
	public Event getEvent() {
		return this.event;
	}
	
	public abstract void execute(Match match, Event event) throws RuntimeException, IOException;
	
	public abstract void discard(Event event) throws RuntimeException, IOException;
}
