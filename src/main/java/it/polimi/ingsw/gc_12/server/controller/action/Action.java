package it.polimi.ingsw.gc_12.server.controller.action;

import it.polimi.ingsw.gc_12.Match;

import java.io.Serializable;

public abstract class Action implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4334191184999875154L;

	public abstract void execute(Match match);
}
