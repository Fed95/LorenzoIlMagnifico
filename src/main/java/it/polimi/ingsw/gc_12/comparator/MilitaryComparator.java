package it.polimi.ingsw.gc_12.comparator;

import java.util.Comparator;

import it.polimi.ingsw.gc_12.Player;

public class MilitaryComparator implements Comparator<Player> {
	@Override
	public int compare(Player player1, Player player2){
		return player1.getMilitaryPoints().compareTo(player2.getMilitaryPoints());
	}
}
