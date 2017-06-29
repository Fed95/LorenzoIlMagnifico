package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.*;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.ClientSender;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventStartTurn extends Event{

	private int turn;

	public EventStartTurn(Player player, List<Action> actions, int turn) {
		super(player);
		this.actions = actions;
		this.turn = turn;
	}

	public EventStartTurn(Player player) {
		this(player, new ArrayList<>(), 0);
	}

	public EventStartTurn() {
	}

	@Override
	public List<Object> getAttributes() {
		return new ArrayList<>();
	}

	@Override
	public void executeClientSide(ClientHandler client) {
		client.getMatch().setTurn(turn);
		boolean myTurn = client.getColor().equals(player.getColor());
		client.setMyTurn(myTurn);
		if(myTurn) {
			if(client.isExcluded()) {
				for (int i = 0; i < actions.size(); i++) {
					if(actions.get(i) instanceof ActionPassTurn) {
						new Thread(new PassTurnSender(i, client.getView().getClientSender())).start();
					}
				}
			}
			else {
				super.executeClientSide(client);
			}
		}
	}

	@Override
	public void setActions(ActionHandler actionHandler, Match match) {
		actions = new ArrayList<>();
		actionHandler.setHasPlaced(false);
		for (FamilyMember familyMember : player.getAvailableFamilyMembers()) {
			actions.add(new ActionChooseFamilyMember(player, familyMember));
		}
		actions.add(new ActionPassTurn(player));
		actions.add(new ActionRequestStatistics(player));
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(System.getProperty("line.separator"));
		sb.append("It's " + player.getName() + "'s turn.");
		return sb.toString();
	}

	@Override
	public String toStringClient() {
		return "It's " + player.getName() + "'s turn.";
	}

	class PassTurnSender implements Runnable {
		ClientSender clientSender;
		int input;

		PassTurnSender(int input, ClientSender clientSender) {
			this.input = input;
			this.clientSender = clientSender;
		}

		public void run() {
			try {
				clientSender.sendAction(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
