package it.polimi.ingsw.gc_12.server.controller;

import it.polimi.ingsw.gc_12.server.model.State;

public class StateChange extends Change {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5551223529797237865L;
	
	private final State newState;
	
	public StateChange(State newState){
		this.newState=newState;
	}

	public State getNewState() {
		return newState;
	}

	/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
	@Override
	public String toString() {
		return "StateChange [newState=" + newState + "]";
	}
}
