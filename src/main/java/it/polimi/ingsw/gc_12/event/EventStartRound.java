package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.dice.SpaceDie;
import it.polimi.ingsw.gc_12.occupiables.TowerSet;

import java.util.List;

public class EventStartRound extends Event {

	private int round;
	private TowerSet towers;
	private SpaceDie spaceDie;

	public EventStartRound(int round, TowerSet towers, SpaceDie spaceDie) {
		this.round = round;
		this.towers = towers;
		this.spaceDie = spaceDie;
	}

	public int getRound() {
		return round;
	}

	@Override
	public void executeClientSide(ClientHandler client) {
	    client.getMatch().getBoard().setTowerSet(towers);
	    client.getMatch().setCards(towers);
	    client.getMatch().setDice(spaceDie);
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
	@Override
    public String toStringClientGUI(){
	    return "ROUND "+round;
    }
}
