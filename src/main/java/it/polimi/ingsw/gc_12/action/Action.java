package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Player;

public abstract class Action implements ActionInterface {
	
	protected Player player;
	
	public Action(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
