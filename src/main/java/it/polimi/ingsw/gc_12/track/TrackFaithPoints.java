package it.polimi.ingsw.gc_12.track;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.resource.ResourceType;

public class TrackFaithPoints {

	public List<Player> getSafePlayers(){

		List<Player> safePlayers = Match.instance().getPlayers();

		for(Player player : safePlayers){

			if(player.getResourceValue(ResourceType.FAITH_POINT) >= Match.instance().getPeriodNum() + 2){
				safePlayers.add(player);
			}
		}
		return safePlayers;
	}
}
