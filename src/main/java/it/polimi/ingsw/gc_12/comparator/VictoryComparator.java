package it.polimi.ingsw.gc_12.comparator;

import java.util.Comparator;

import it.polimi.ingsw.gc_12.Player;

public class VictoryComparator implements Comparator<Player> {
	@Override
	public int compare(Player player1, Player player2){
		return player1.getVictoryPoints().compareTo(player2.getVictoryPoints());
	}
}
