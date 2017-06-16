package it.polimi.ingsw.gc_12.client.rmi;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.MatchInstance;
import it.polimi.ingsw.gc_12.MatchRemote;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.mvc.CLIAdapter;
import it.polimi.ingsw.gc_12.mvc.View;
import it.polimi.ingsw.gc_12.mvc.ViewCLI;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.resource.ResourceType;
import it.polimi.ingsw.gc_12.server.controller.Change;
import it.polimi.ingsw.gc_12.server.controller.StateChange;
import it.polimi.ingsw.gc_12.action.Action;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ClientRMIView extends ClientHandler implements ClientViewRemote, Serializable{

	private String name;

	private MatchRemote matchRemote;
	private Player player;
	private volatile List<Action> actions = new ArrayList<>();

	protected ClientRMIView(String name) throws RemoteException {
		super();
		this.name = name;
	}

	@Override
	public void updateClient(Event event) {
		System.out.println(event);
		if(event instanceof EventStartMatch) {
			EventStartMatch eventStartMatch = (EventStartMatch) event;
			matchRemote = eventStartMatch.getMatch();
			try {
				player = matchRemote.getPlayer(name);
				match = matchRemote.getInstance();

			} catch (CloneNotSupportedException | RemoteException e) {
				e.printStackTrace();
			}
		}
		else if(event.getPlayer() != null && isMyTurn(event.getPlayer())) {
			actions = event.getActions();
			System.out.println("Choose the action to perform:");
			for (int i = 0; i < actions.size(); i++) {
				System.out.println(i + " - " + actions.get(i));

			}
		}

		/*if(change instanceof EventStartMatch) {
			System.out.println("ClientRMI: EventStartMatch recognised. Creating view with local match.");
			EventStartMatch event = (EventStartMatch) change;
			matchRemote = event.getMatch();
			player = matchRemote.getPlayer(name);
			try {
				match = matchRemote.getInstance();

			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			createView(match);
		}
		else {
			handleEvent(change);
		}*/
	}

	@Override
	protected boolean isMyTurn(Player currentPlayer){
		if(currentPlayer == null) {
			System.out.println("MyTurn: current player is null");
		}
		return name.equals(currentPlayer.getName());
	}

	@Override
	public String getName() throws RemoteException {
		return name;
	}

	public List<Action> getActions() {
		return actions;
	}

	private static final long serialVersionUID = 6111979881550001331L;
}
