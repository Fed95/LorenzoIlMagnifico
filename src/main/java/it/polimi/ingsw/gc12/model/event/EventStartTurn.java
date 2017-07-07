package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.action.*;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.view.client.ClientHandler;
import it.polimi.ingsw.gc12.view.client.gui.MainBoard;
import javafx.application.Platform;

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
	public void setActions(Match match) {
		hasPlaced = match.getActionHandler().hasPlaced();
		actions = new ArrayList<>();
		if(!hasPlaced) {
			for (FamilyMember familyMember : player.getAvailableFamilyMembers()) {
				actions.add(new ActionChooseFamilyMember(player, familyMember));
			}
		}
		if(player.getPersonalBoard().getLeaderCardsSpace().getCards().size() > 0) {
			if (player.getPlayableLeaderCards().size() > 0)
				actions.add(new ActionViewPlayableLeaderCards(player));
			if (player.getAvailableLeaderCards().size() > 0)
				actions.add(new ActionViewAvailableLeaderCards(player));
			actions.add(new ActionViewDiscardableLeaderCards(player));
		}
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
		Platform.runLater(() -> view.getControllerMainBoard().disablePassTurn(!hasPlaced));
	}
}
