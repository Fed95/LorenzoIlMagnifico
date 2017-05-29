package it.polimi.ingsw.gc_12.track;

import java.util.Collections;
import java.util.List;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.comparator.VictoryComparator;

public class TrackVictoryPoints {

	public List<Player> getPlayerOrdered(){

		List<Player> players = Match.instance().getPlayers();

		Collections.sort(Match.instance().getPlayers(), new VictoryComparator().reversed());
		return players;
	}
}
