package it.polimi.ingsw.gc_12.track;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.resource.ResourceType;

import java.util.ArrayList;
import java.util.List;

public class TrackFaithPoints {

	private List<FaithSlot> faithSlots = new ArrayList<>();

	public TrackFaithPoints(List<Integer> values){
		for(int value : values)
			faithSlots.add(new FaithSlot(value));
	}

	public boolean isPlayerSafe(Player player, int period) {
		return player.getResourceValue(ResourceType.FAITH_POINT) >= period + 2;
	}

	public FaithSlot getFaithSlot(int index){
		return faithSlots.get(index);
	}
}
