package it.polimi.ingsw.gc_12.track;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.comparator.MilitaryComparator;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class TrackMilitaryPoints implements Serializable{

	public List<Player> getPlayersOrder(List<Player> players){
		Collections.sort(players, new MilitaryComparator().reversed());
		return players;
	}
}
