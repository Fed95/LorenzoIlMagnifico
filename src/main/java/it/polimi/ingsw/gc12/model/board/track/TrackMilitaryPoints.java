package it.polimi.ingsw.gc12.model.board.track;

import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.misc.comparator.MilitaryComparator;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Represent the military points track, has a methods for ordering player considering the military points
 */
public class TrackMilitaryPoints implements Serializable{
    /**
     * Return a list of player ordered by military points
     * @param players list of player to order
     * @return list player ordered
     */
	public List<Player> getPlayersOrder(List<Player> players){
		Collections.sort(players, new MilitaryComparator().reversed());
		return players;
	}
}
