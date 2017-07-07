package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.view.client.ClientHandler;

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
	public void setActions(Match match) {
		match.getActionHandler().flushEvents();
	}

	@Override
	public String toString() {
		return "";
	}
}
