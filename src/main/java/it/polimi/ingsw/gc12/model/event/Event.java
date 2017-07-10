package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.action.Action;
import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.view.client.ClientHandler;
import it.polimi.ingsw.gc12.model.effect.EffectProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * When an event is initialised during the game, all the possible EffectProviders are associated to it.
 * Every Effect of every Effect provider, which stored event equals this event, will be activated.
 * (reminder: every Effect contains an event which allows the effect do be executed only when he is notified with an equal event).
 *
 * Furthermore, the server communicates with the client sending events, which contain the actions that the client can perform.
 * These action are saved in the event just before it is sent to the client
 * All events containing actions also contain a DiscardAction for CLI navigational purposes
 */
public abstract class Event implements Serializable{

	protected Player player;
	protected List<EffectProvider> effectProviders = new ArrayList<>();
	protected List<Action> actions = new ArrayList<>();
	protected ActionHandler actionHandler;

	public Event(Player player) {
		if(player != null) {
			this.player = player;
			effectProviders.add(player.getPersonalBoard().getBonusTile());
			effectProviders.addAll(player.getPersonalBoard().getLeaderCardsSpace().getUsefulLeaderCards());
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

	public void setActions(Match match) {
		match.getActionHandler().removeLastEvent();
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

	public abstract String toString();

}
