package it.polimi.ingsw.gc_12.track;

import java.util.Collections;

import java.util.List;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.comparator.MilitaryComparator;

public class MilitaryPointsTrack{
	List<Player> players;
	public MilitaryPointsTrack(List<Player> players){
		this.players = players;
	}
	public List<Player> getPlayerOrdered(){
		Collections.sort(players, new MilitaryComparator().reversed());
		return players;
	}
}
