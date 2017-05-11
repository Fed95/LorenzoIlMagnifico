package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.Player;

// TODO: make it an interface/abstract class and make multiple class of events
public class Event {
	Player player;
	Occupiable occupiable;
	
	public Event(Player player, Occupiable occupiable) {
		this.player = player;
		this.occupiable = occupiable;
	}

	public Player getPlayer() {
		return player;
	}

	public Occupiable getOccupiable() {
		return occupiable;
	}

	public boolean equals(Event event) {
		return this.player == event.getPlayer() && this.occupiable == event.getOccupiable();
	}
	
	
	
	
}
