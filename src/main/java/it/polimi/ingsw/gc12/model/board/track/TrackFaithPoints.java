package it.polimi.ingsw.gc12.model.board.track;

import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all the faith point slots and is responsible of creating it
 */
public class TrackFaithPoints {

	private List<FaithSlot> faithSlots = new ArrayList<>();

    /**
     * Constructor create the faith points slot
     * @param values value of the victory points that the slot gave to you
     */
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

	public List<FaithSlot> getFaithSlots() {
		return faithSlots;
	}
}
