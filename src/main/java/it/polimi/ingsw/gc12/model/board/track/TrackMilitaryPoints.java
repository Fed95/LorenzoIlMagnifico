package it.polimi.ingsw.gc12.model.board.track;

import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.misc.comparator.MilitaryComparator;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class TrackMilitaryPoints implements Serializable{

	public List<Player> getPlayersOrder(List<Player> players){
		Collections.sort(players, new MilitaryComparator().reversed());
		return players;
	}
}
