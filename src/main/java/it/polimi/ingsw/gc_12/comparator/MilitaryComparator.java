package it.polimi.ingsw.gc_12.comparator;

import java.util.Comparator;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.resource.ResourceType;

public class MilitaryComparator implements Comparator<Player> {
	@Override
	public int compare(Player player1, Player player2){
		return player1.getResourceValue(ResourceType.MILITARY_POINT).compareTo(player2.getResourceValue(ResourceType.MILITARY_POINT));
	}
}
