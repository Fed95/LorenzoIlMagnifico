package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.action.ActionHandler;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.java_fx.MainBoard;
import it.polimi.ingsw.gc_12.server.controller.Change;
import javafx.application.Platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Event extends Change {

	protected Player player;
	protected List<EffectProvider> effectProviders = new ArrayList<>();
	protected List<Action> actions = new ArrayList<>();
	protected ActionHandler actionHandler;

	public Event(Player player) {
		if(player != null) {
			this.player = player;
			effectProviders.add(player.getPersonalBoard().getBonusTile());
			effectProviders.addAll(player.getPersonalBoard().getUsefulLeaderCards());
			effectProviders.addAll(player.getCards());
			effectProviders.addAll(player.getExcommunications());
		}else{
			System.out.println("The player of " + this + " is null!");
		}
	}

	public Event() {}

	public void setActionHandler(ActionHandler actionHandler){
		this.actionHandler = actionHandler;
	}

	public void executeClientSide(ClientHandler client) {
		if(client.isMyTurn()) {
			if(actions.size() > 0) {
				System.out.println("What would you like to do?");
				System.out.println();
			}
			for (int i = 0; i < actions.size(); i++)
				System.out.println(i + " - " + actions.get(i));
		}
	}

	public void changeMainBoard(ClientHandler client) {
		Platform.runLater(() -> {
			MainBoard mainBoard = (MainBoard) client.getView();
			try {
				mainBoard.changeScene("FXMLMainBoard", 1980, 1080, true, "Lorenzo il magnifico");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public Player getPlayer() {
		return player;
	}

	public boolean equals(Object obj) {
		if (this.getClass() != obj.getClass()) {
			//System.out.println("Recieved: " + obj + ". I am: " + this + ". CLASS MISMATCH ");
			return false;
		}
		return true;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(ActionHandler actionHandler, Match match) {
		actionHandler.removeLastEvent();
	}

	public List<EffectProvider> getEffectProviders() {
		return effectProviders;
	}

	public String toStringClient() {
		return null;
	}
    public String toStringClientGUI() {
        return null;
    }

	public abstract List<Object> getAttributes();
	public abstract String toString();

}
