package it.polimi.ingsw.gc_12.server.controller;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.server.observer.Observer;

import java.io.IOException;
import java.rmi.RemoteException;

public class Controller implements Observer<Action>{
	
	private final Match match;
	
	public Controller(Match match){
		this.match = match;
	}
	
	public void update(Action action) throws IOException, RemoteException {

		System.out.println("Server Controller: Notified of new " + action.getClass().getSimpleName() + " from RMIView.");
		Observer.super.update(action);
		try {
			System.out.println("Server Controller: Starting the " + action.getClass().getSimpleName() + "...");
			action.start(match);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
