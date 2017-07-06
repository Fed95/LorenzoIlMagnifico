package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;

import java.io.Serializable;

public abstract class Action implements Serializable {

	protected Player player;

	public Action(Player player) {
		this.player = player;
	}

    public abstract boolean isValid(Match match);
	public abstract void start(Match match);

}
