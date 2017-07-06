package it.polimi.ingsw.gc12.misc.comparator;

import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;

import java.util.Comparator;

public class VictoryComparator implements Comparator<Player> {
	@Override
	public int compare(Player player1, Player player2){
		return player1.getResourceValue(ResourceType.VICTORY_POINT).compareTo(player2.getResourceValue(ResourceType.VICTORY_POINT));
	}
}
