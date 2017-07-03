package it.polimi.ingsw.gc_12;

import java.util.TimerTask;

public class TimerActionTask extends TimerTask {

	private Match match;

	public TimerActionTask(Match match) {
		this.match = match;
	}

	@Override
	public void run() {
		match.excludeCurrentPlayer();
		match.newTurn();
	}
}