package it.polimi.ingsw.gc12.model.board.track;

import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.misc.comparator.VictoryComparator;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Represent the victory points track, has a methods for ordering player considering the victory points
 */
public class TrackVictoryPoints implements Serializable{
    /**
     * Method that return a list of players ordered by vistory points
     * @param players player to order
     * @return players ordered
     */
	public List<Player> getPlayerOrdered(List<Player> players){
		Collections.sort(players, new VictoryComparator().reversed());
		return players;
	}
}
