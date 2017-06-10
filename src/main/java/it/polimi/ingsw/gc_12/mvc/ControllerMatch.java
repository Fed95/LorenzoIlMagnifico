package it.polimi.ingsw.gc_12.mvc;

import it.polimi.ingsw.gc_12.FamilyMemberColor;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;

import java.util.List;

public class ControllerMatch {

	private List<Player> players;
	private ControllerPlayer controllerPlayer;
	private Match match;

	public ControllerMatch(List<Player> players) {
		this.players = players;
		match = new Match();
		//match.setPlayers(players);
		match.init(players);
		controllerPlayer = new ControllerPlayer(match);
		match.setControllerPlayer(controllerPlayer);
	}

	public void start() {
		for (int i = 0; i < 3; i++) { // 3 periods TODO: use variable from match
			for (int j = 0; j < 2; j++) { // 2 rounds per period TODO: use variable from match
				//Refreshes the board
				if(i == 0)
					match.getBoard().getTrackTurnOrder().chooseRandomOrder();
				else
					match.getBoard().refresh(match.getRoundNum(), match.getPeriodNum());

				for (int k = 0; k < FamilyMemberColor.values().length; k++) { // 4 turns per round TODO: use variable from match
					//controllerPlayer.start();
					match.newTurn();
				}

			}
			vaticanReport();
		}
	}

	private void vaticanReport() {
		for(Player player : players) {
			/*if(match.getBoard().getTrackFaithPoints().isPlayerSafe(player, match.getPeriodNum()))
				controllerPlayer.vaticanReport(player);
			else
				controllerPlayer.receiveExcommunication(player);*/
		}
	}

	public Match getMatch() {
		return match;
	}

}
