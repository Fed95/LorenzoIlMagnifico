package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;

public class ActionPassTurn extends Action{

	public ActionPassTurn(Player player) {
		super(player);
	}

	@Override
	public void start(Match match) {
		match.newTurn(false);
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
