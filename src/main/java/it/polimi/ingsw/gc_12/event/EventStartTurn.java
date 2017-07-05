package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.*;
import it.polimi.ingsw.gc_12.client.*;
import it.polimi.ingsw.gc_12.java_fx.MainBoard;
import javafx.application.Platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventStartTurn extends Event implements EventView {

	private int turn;
	private boolean hasPlaced;

	public EventStartTurn(Player player, List<Action> actions, int turn) {
		super(player);
		if(actions != null)
			this.actions = actions;
		this.turn = turn;
	}

	public EventStartTurn(Player player, int turn){
		this(player, null, turn);
	}

	public EventStartTurn(Player player) {
		this(player, null, 0);
	}

	public EventStartTurn() {
	}

	@Override
	public void executeClientSide(ClientHandler client) {
		client.getMatch().setTurn(turn);
		boolean myTurn = client.getColor().equals(player.getColor());
		client.setMyTurn(myTurn);
		if(myTurn) {
			super.executeClientSide(client);
		}
	}

	@Override
	public void setActions(ActionHandler actionHandler, Match match) {
		hasPlaced = actionHandler.hasPlaced();
		actions = new ArrayList<>();
		if(!hasPlaced) {
			for (FamilyMember familyMember : player.getAvailableFamilyMembers()) {
				actions.add(new ActionChooseFamilyMember(player, familyMember));
			}
		}

		if(player.getPlayableLeaderCards().size() > 0)
			actions.add(new ActionViewPlayableLeaderCards(player));
		if(player.getAvailableLeaderCards().size() > 0)
			actions.add(new ActionViewAvailableLeaderCards(player));
		if(player.getPersonalBoard().getLeaderCards().size() > 0)
			actions.add(new ActionViewDiscardableLeaderCards(player));
		if(hasPlaced)
			actions.add(new ActionPassTurn(player));
		actions.add(new ActionRequestStatistics(player));
	}

	@Override
	public String toString() {
		if(player == null)
			return this.getClass().getSimpleName();

		StringBuilder sb = new StringBuilder();
		sb.append(System.getProperty("line.separator"));
		sb.append("It's " + player.getName() + "'s turn.");
		return sb.toString();
	}

	@Override
	public String toStringClient() {
		return "It's " + player.getName() + "'s turn.";
	}
	@Override
    public String toStringClientGUI(){
        return "It's " + player.getName() + "'s turn.";
    }

	@Override
	public void executeViewSide(MainBoard view) {
		Platform.runLater(() -> view.getControllerMainBoard().disablePassTurn(!hasPlaced, player));
	}
}
