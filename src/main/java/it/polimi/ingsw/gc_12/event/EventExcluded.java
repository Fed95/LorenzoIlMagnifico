package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.ActionHandler;
import it.polimi.ingsw.gc_12.client.ClientHandler;

import java.util.List;

public class EventExcluded extends Event {

	public EventExcluded(Player player) {
		super(player);
	}

	@Override
	public void executeClientSide(ClientHandler client) {
		boolean myTurn = client.getColor().equals(player.getColor());
		if(myTurn)
			client.setExcluded(true);
	}

	@Override
	public void setActions(ActionHandler actionHandler, Match match) {
		actionHandler.flushEvents();
	}

	@Override
	public String toString() {
		return null;
	}
}
