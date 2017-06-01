package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Player;

public abstract class Action {
	
	protected Player player;
	
	public Action(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}

	public abstract void start() throws RuntimeException;
}
