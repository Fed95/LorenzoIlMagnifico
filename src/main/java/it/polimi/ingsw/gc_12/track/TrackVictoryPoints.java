package it.polimi.ingsw.gc_12.track;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.comparator.VictoryComparator;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class TrackVictoryPoints implements Serializable{

	public List<Player> getPlayerOrdered(List<Player> players){
		Collections.sort(players, new VictoryComparator().reversed());
		return players;
	}
}
