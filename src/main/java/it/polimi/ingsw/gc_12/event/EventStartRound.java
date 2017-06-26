package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.occupiables.TowerSet;

import java.util.List;

public class EventStartRound extends Event {

	int round;
	TowerSet towers;

	public EventStartRound(int round, TowerSet towers) {
		this.round = round;
		this.towers = towers;
	}

	public int getRound() {
		return round;
	}

	@Override
	public void executeClientSide(ClientHandler client) {
		client.getMatch().getBoard().setTowerSet(towers);
	}

	@Override
	public List<Object> getAttributes() {
		return null;
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public String toStringClient() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("-------------------------------\n");
		stringBuilder.append("----------  ROUND ").append(round).append("  ----------\n");
		stringBuilder.append("-------------------------------");
		return stringBuilder.toString();
	}
}
