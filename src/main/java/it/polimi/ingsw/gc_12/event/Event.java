package it.polimi.ingsw.gc_12.event;


import it.polimi.ingsw.gc_12.Player;

public abstract class Event implements EventInterface {
	
	protected Player player;
	private final ListEvent eventType;
	
	public Event(Player player,ListEvent eventType) {
		this.player = player;
		this.eventType=eventType;
	}
	public Event(ListEvent eventType){
		this.eventType=eventType;
	}
	public Player getPlayer() {
		return player;
	}

	public boolean equals(Object obj) {
		if(this.getClass() != obj.getClass())
			return false;
		
		Event event = (Event) obj;
		return this.player == event.getPlayer() && this.getAttributes().equals(event.getAttributes());
	}
}
