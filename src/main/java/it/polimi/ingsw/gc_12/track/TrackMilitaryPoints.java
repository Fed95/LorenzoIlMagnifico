package it.polimi.ingsw.gc_12.track;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.comparator.MilitaryComparator;

import java.util.Collections;
import java.util.List;

public class TrackMilitaryPoints {

	public List<Player> getPlayersOrder(List<Player> players){
		Collections.sort(players, new MilitaryComparator().reversed());
		return players;
	}
}
