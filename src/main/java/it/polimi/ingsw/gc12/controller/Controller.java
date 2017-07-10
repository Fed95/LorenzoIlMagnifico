package it.polimi.ingsw.gc12.controller;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.action.Action;
import it.polimi.ingsw.gc12.misc.observer.Observer;

/**
 * The Controller executes the action that the Match has retrieved from the ActionController after receiving an input from the user.
 */
public class Controller implements Observer<Action>{
	
	private final Match match;
	
	public Controller(Match match){
		this.match = match;
	}
	
	public void update(Action action) {

		System.out.println("Server Controller: Notified of new " + action.getClass().getSimpleName() + " from ServerRMIView.");
		Observer.super.update(action);
		System.out.println("Server Controller: Starting the " + action.getClass().getSimpleName() + "...");
		action.start(match);
	}

	@Override
	public void update() {
	}

}
