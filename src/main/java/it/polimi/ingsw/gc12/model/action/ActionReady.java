package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;

public class ActionReady extends Action{

	public ActionReady(Player player) {
		super(player);
	}

	@Override
	public boolean isValid(Match match) {
		return false;
	}

	@Override
	public void start(Match match) {
		match.addReady();
	}
}
