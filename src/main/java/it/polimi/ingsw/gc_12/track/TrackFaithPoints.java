package it.polimi.ingsw.gc_12.track;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.resource.ResourceType;

public class TrackFaithPoints {

	private List<FaithSlot> faithSlots = new ArrayList<>();

	public TrackFaithPoints(List<Integer> values){
		for(int value : values)
			faithSlots.add(new FaithSlot(value));
	}

	public List<Player> getSafePlayers(){

		List<Player> safePlayers = Match.instance().getPlayers();

		for(Player player : safePlayers){

			if(player.getResourceValue(ResourceType.FAITH_POINT) < Match.instance().getPeriodNum() + 2){
				safePlayers.remove(player);
			}
		}
		return safePlayers;
	}

	public boolean isPlayerSafe(Player player) {
		return player.getResourceValue(ResourceType.FAITH_POINT) >= Match.instance().getPeriodNum() + 2;
	}

	public FaithSlot getFaithSlot(int index){
		return faithSlots.get(index);
	}
}
