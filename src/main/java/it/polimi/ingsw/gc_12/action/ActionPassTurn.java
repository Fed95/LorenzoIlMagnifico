package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;

public class ActionPassTurn extends Action{

	public ActionPassTurn(Player player) {
		super(player);
	}

	@Override
	public void start(Match match) {
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

	@Override
	public boolean equals(Object o) {
		return this == o || o instanceof ActionPassTurn;
	}

	@Override
	public int hashCode() {
		return player.hashCode();
	}
}
