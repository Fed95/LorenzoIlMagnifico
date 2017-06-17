package it.polimi.ingsw.gc_12.client.rmi;


import it.polimi.ingsw.gc_12.MatchRemote;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.action.Action;
import java.io.Serializable;
import java.rmi.RemoteException;

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
		else {
			handleEvent(event);
		}
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
	private static final long serialVersionUID = 6111979881550001331L;
}
