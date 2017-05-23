package it.polimi.ingsw.gc_12.track;

import it.polimi.ingsw.gc_12.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrackTurnOrder {

	private List<Player> turnOrder = new ArrayList<>();
	private Player currentPlayer;
	private int turn;

	public TrackTurnOrder(List<Player> players) {
		this.turnOrder = players;
		chooseRandomOrder();
	}

	public void chooseRandomOrder() {
		Collections.shuffle(turnOrder);
		resetRound();
	}

	public void resetRound() {
		this.turn = 0;
	}

	public Player getCurrentPlayer() {
		return turnOrder.get(turn);
	}

	public int newTurn() {
		turn++;
		if (turn == turnOrder.size())
			turn = 0;
		return turn;
	}


}
