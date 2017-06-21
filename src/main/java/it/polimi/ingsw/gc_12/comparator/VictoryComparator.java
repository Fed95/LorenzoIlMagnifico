package it.polimi.ingsw.gc_12.comparator;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.resource.ResourceType;

import java.util.Comparator;

public class VictoryComparator implements Comparator<Player> {
	@Override
	public int compare(Player player1, Player player2){
		return player1.getResourceValue(ResourceType.VICTORY_POINT).compareTo(player2.getResourceValue(ResourceType.VICTORY_POINT));
	}
}
