package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;

public class ActionPassTurn extends Action{

	@Override
	public void start(Match match) throws RuntimeException, RequiredValueNotSatisfiedException {
		match.newTurn();
		
	}

}
