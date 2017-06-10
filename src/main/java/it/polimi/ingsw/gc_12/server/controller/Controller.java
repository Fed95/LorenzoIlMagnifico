package it.polimi.ingsw.gc_12.server.controller;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.server.observer.Observer;

public class Controller implements Observer<Action>{
	
	private final Match match;
	
	public Controller(Match match){
		this.match = match;
	}
	
	public void update(Action action){
		System.out.println("I AM THE CONTROLLER UPDATING THE MODEL WITH A NEW ACTION");
		Observer.super.update(action);
		try {
			action.start(match);
		} catch (RuntimeException | RequiredValueNotSatisfiedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
