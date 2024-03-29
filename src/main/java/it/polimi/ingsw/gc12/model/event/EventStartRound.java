package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.board.track.TrackTurnOrder;
import it.polimi.ingsw.gc12.view.client.MatchInstance;
import it.polimi.ingsw.gc12.view.client.ClientHandler;
import it.polimi.ingsw.gc12.model.board.dice.SpaceDie;
import it.polimi.ingsw.gc12.model.board.occupiable.TowerSet;
import it.polimi.ingsw.gc12.view.client.gui.MainBoard;
import javafx.application.Platform;

public class EventStartRound extends Event implements EventView{

	private int round;
	private TowerSet towers;
	private SpaceDie spaceDie;
	private TrackTurnOrder turnOrder;

	public EventStartRound(int round, TowerSet towers, SpaceDie spaceDie, TrackTurnOrder turnOrder) {
		this.round = round;
		this.towers = towers;
		this.spaceDie = spaceDie;
		this.turnOrder = turnOrder;
	}

	public int getRound() {
		return round;
	}

	@Override
	public void executeClientSide(ClientHandler client) {
		MatchInstance matchInstance = client.getMatch();
	    matchInstance.getBoard().setTowerSet(towers);
	    matchInstance.setCards(towers);
	    matchInstance.setDice(spaceDie);

		if(round != 1) {
			matchInstance.resetFamilyMembers();
			matchInstance.resetBoard();
			matchInstance.setTurnOrder(turnOrder);
		}
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

	@Override
	public void executeViewSide(MainBoard view) {
		Platform.runLater(() -> {
			view.getControllerMainBoard().disablePassTurn(true);
			view.getControllerMainBoard().resetOccupiableImages();
			view.getControllerMainBoard().resetFamilyMembers();
		});
	}
}
