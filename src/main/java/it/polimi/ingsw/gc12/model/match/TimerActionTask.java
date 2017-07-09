package it.polimi.ingsw.gc12.model.match;

import it.polimi.ingsw.gc12.model.match.Match;

import java.util.TimerTask;

public class TimerActionTask extends TimerTask {

	private Match match;

	public TimerActionTask(Match match) {
		this.match = match;
	}

	@Override
	public void run() {
		match.excludeCurrentPlayer();
		match.newTurn(false);
	}
}