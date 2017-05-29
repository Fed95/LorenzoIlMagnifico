package it.polimi.ingsw.gc_12.mvc;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerMatch {

	private List<Player> players;
	private ControllerPlayer controllerPlayer;
	private Match match;

	public ControllerMatch(List<Player> players) {
		this.players = players;
		match = Match.instance();
		match.setPlayers(players);
		controllerPlayer = new ControllerPlayer(match);
	}

	public void start() {
		for (int i = 0; i < 3; i++) { // TODO: use variable from match
			for (int j = 0; j < 2; j++) { // TODO: use variable from match
				match.newRound();

				for (int k = 0; k < match.getBoard().getSpaceDie().getDiceNum(); k++) {
					controllerPlayer.start();
					match.newTurn();
				}

			}
			vaticanReport();
		}
	}

	private void vaticanReport() {
		for(Player player : players) {
			if(!match.getBoard().getTrackFaithPoints().isPlayerSafe(player))
				controllerPlayer.vaticanReport(player);
		}
	}
}
