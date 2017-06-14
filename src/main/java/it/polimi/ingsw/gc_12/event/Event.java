package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.server.controller.Change;
import it.polimi.ingsw.gc_12.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Event extends Change implements EventInterface {

	protected Player player;
	protected List<EffectProvider> effectProviders = new ArrayList<>();

	public Event(Player player) {
		this.player = player;
		effectProviders.addAll(player.getCards());
		effectProviders.addAll(player.getExcommunications());
	}

	public Event() {}

	public Player getPlayer() {
		return player;
	}

	public boolean equals(Object obj) {
		if (this.getClass() != obj.getClass()) {

			System.out.println("The events are not of the same class");

			return false;
		}
		return true;
	}
}
