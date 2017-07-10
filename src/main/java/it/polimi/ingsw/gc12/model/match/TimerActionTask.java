package it.polimi.ingsw.gc12.model.match;

import it.polimi.ingsw.gc12.model.match.Match;
/**
 * This class, if the match is not paused, esclude a player and try to start a new turn
 */
import java.util.TimerTask;

public class TimerActionTask extends TimerTask {

	private Match match;

	public TimerActionTask(Match match) {
		this.match = match;
	}

	@Override
	public void run() {
		if(match.getGameState() != MatchState.PAUSED) {
			match.excludeCurrentPlayer();
			match.newTurn(false);
		}
	}
}