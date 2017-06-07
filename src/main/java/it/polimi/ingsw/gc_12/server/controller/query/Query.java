package it.polimi.ingsw.gc_12.server.controller.query;

import it.polimi.ingsw.gc_12.Match;

import java.io.Serializable;

public abstract class Query<O> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6852387886693073989L;
	
	public abstract O perform(Match m);
}
