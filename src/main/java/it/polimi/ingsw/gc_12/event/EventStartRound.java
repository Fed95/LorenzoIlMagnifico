package it.polimi.ingsw.gc_12.event;

import java.util.List;

public class EventStartRound extends Event {

	int round;

	public EventStartRound(int round) {
		this.round = round;
	}

	public int getRound() {
		return round;
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
