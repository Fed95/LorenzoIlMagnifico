package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.EffectProvider;
import it.polimi.ingsw.gc_12.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Event implements EventInterface {

	protected Player player;
	protected List<EffectProvider> effectProviders = new ArrayList<>();

	public Event(Player player) {
		this.player = player;
	}

	public Event() {}

	public Player getPlayer() {
		return player;
	}

	public boolean equals(Object obj) {
		if (this.getClass() != obj.getClass()) {

			System.out.println("the events are not of the same class");

			return false;
		}
		return true;
	}
}
