package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;

import java.io.IOException;
import java.rmi.RemoteException;

public class ActionPassTurn extends Action{

	public ActionPassTurn(Player player) {
		super(player);
	}

	@Override
	public void start(Match match){
		match.newTurn();
	}

	@Override
	public boolean isValid(Match match){
		return false;
	}

	@Override
	public String toString() {
		return "Pass turn";
	}

}
