package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.view.client.ClientHandler;
import it.polimi.ingsw.gc12.model.board.dice.SpaceDie;
import it.polimi.ingsw.gc12.model.board.occupiable.TowerSet;

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
		return "";
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
