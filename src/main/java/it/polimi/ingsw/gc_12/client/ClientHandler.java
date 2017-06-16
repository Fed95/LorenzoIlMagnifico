package it.polimi.ingsw.gc_12.client;


import it.polimi.ingsw.gc_12.MatchInstance;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.mvc.View;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public abstract class ClientHandler extends UnicastRemoteObject {
	protected MatchInstance match;
	protected View view;
	protected List<Action> actions = new ArrayList<>();

	protected ClientHandler() throws RemoteException {
		super();
	}

	public void handleEvent(Event event) {
		if(event.getPlayer() != null && isMyTurn(event.getPlayer())) {
			actions = event.getActions();
			System.out.println("What would you like to do?");
			System.out.println();
			for (int i = 0; i < actions.size(); i++) {
				System.out.println(i + " - " + actions.get(i));
			}
		}
	}

	public List<Action> getActions() {
		return actions;
	}

	protected abstract boolean isMyTurn(Player player);
}