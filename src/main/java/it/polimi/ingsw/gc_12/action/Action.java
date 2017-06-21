package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;

import java.io.Serializable;

public abstract class Action implements Serializable {

	protected Player player;

	public Action(Player player) {
		this.player = player;
	}

    public abstract boolean isValid(Match match);
	public abstract void start(Match match);

}
