package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;

/**
 * Created by marco on 20/06/2017.
 */
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
