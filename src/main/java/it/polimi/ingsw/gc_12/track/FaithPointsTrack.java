package it.polimi.ingsw.gc_12.track;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;

public class FaithPointsTrack {
	List<Player> players;
	public FaithPointsTrack(List<Player> players){
		this.players = players;
	}
	public List<Player> getSafePlayers(){
		List<Player> safePlayers= new ArrayList<>();
		for(Player pl : players){
			if(pl.getFaithPoints() >= Match.instance().getPeriodNum()+2){
				safePlayers.add(pl);
			}
		}
		return safePlayers;
	}
}
