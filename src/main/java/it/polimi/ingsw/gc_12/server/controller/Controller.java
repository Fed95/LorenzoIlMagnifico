package it.polimi.ingsw.gc_12.server.controller;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.server.observer.Observer;

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
		// TODO Auto-generated method stub
		
	}

}
