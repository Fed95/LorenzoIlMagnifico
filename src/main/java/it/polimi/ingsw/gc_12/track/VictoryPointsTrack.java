package it.polimi.ingsw.gc_12.track;

import java.util.Collections;
import java.util.List;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.comparator.VictoryComparator;

public class VictoryPointsTrack {
	List<Player> players;
	public VictoryPointsTrack(List<Player> players){
		this.players = players;
	}
	public List<Player> getPlayerOrdered(){
		Collections.sort(players, new VictoryComparator().reversed());
		return players;
	}
}
