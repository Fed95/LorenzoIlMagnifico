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
	public void start(Match match) throws IOException {
		match.newTurn();
	}

	@Override
	public boolean isValid(Match match) throws IOException {
		return false;
	}

}
