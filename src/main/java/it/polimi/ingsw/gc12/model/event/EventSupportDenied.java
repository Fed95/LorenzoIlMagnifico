package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.board.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.view.client.ClientHandler;

public class EventSupportDenied extends Event{

	private ExcommunicationTile tile;

	public EventSupportDenied(Player player, ExcommunicationTile tile) {
		super(player);
		this.tile = tile;
	}

	@Override
	public void executeClientSide(ClientHandler client) {
		client.getMatch().playerExcommunication(player, tile);
	}

	public ExcommunicationTile getTile() {
		return tile;
	}

	@Override
	public String toString() {
		return "";
	}
}
