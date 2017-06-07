package it.polimi.ingsw.gc_12.client.rmi;

import it.polimi.ingsw.gc_12.MatchRemote;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.server.controller.Change;
import it.polimi.ingsw.gc_12.server.controller.StateChange;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ClientRMIView extends UnicastRemoteObject implements ClientViewRemote, Serializable{

	private String name;
	private ClientRMI client;
	private MatchRemote match;
	private Player currentPlayer;
	private static final String MODEL_VIEW = "match";

	protected ClientRMIView(ClientRMI client, String name) throws RemoteException {
		super();
		this.client = client;
		this.name = name;
	}

	@Override
	public void updateClient(Change c) throws RemoteException, NotBoundException {
		// Just prints what was received from the server
		System.out.println(c);
		if(c instanceof StateChange) {
			StateChange stateChange = (StateChange) c;
			switch (stateChange.getNewState()) {
				case RUNNING:
					getMatch();
					client.createView(match);
					currentPlayer = match.getCurrentPlayer();
					if(isMyTurn()) {
						client.askAction(match.isFMPlaced());
					}
					break;
			}
		}
	}

	private void getMatch() throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(client.HOST, client.PORT);
		//get the stub (local object) of the remote view
		match = (MatchRemote) registry.lookup(MODEL_VIEW);
	}

	private boolean isMyTurn() throws RemoteException {
		return name.equals(currentPlayer.getName());
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	@Override
	public String getName() throws RemoteException {
		return name;
	}

	private static final long serialVersionUID = 6111979881550001331L;
}
