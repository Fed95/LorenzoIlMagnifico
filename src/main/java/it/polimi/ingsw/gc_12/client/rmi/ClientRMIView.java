package it.polimi.ingsw.gc_12.client.rmi;

import it.polimi.ingsw.gc_12.MatchRemote;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.EventChooseFamilyMember;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.event.EventStartMatch;
import it.polimi.ingsw.gc_12.event.EventStartTurn;
import it.polimi.ingsw.gc_12.mvc.CLIAdapter;
import it.polimi.ingsw.gc_12.mvc.View;
import it.polimi.ingsw.gc_12.mvc.ViewCLI;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
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
	private View view;
	private static final String MODEL_VIEW = "match";

	protected ClientRMIView(ClientRMI client, String name) throws RemoteException {
		super();
		this.client = client;
		this.name = name;
	}

	@Override
	public void updateClient(Change change) throws RemoteException, NotBoundException {
		if(change instanceof EventStartMatch) {
			EventStartMatch event = (EventStartMatch) change;
			match = event.getMatch();
			createView(match);
		}
		else if(change instanceof EventStartTurn) {
			currentPlayer = match.getCurrentPlayer();
			if(isMyTurn()) {
				view.askAction(match.isFMPlaced());
			}
		}
		else if(change instanceof EventChooseFamilyMember) {
			currentPlayer = match.getCurrentPlayer(); // TODO: fix synchronization to be sure that EventStartTurn is always already executed
			EventChooseFamilyMember event = (EventChooseFamilyMember) change;
			if(isMyTurn()) {
				view.askOccupiable(event.getFamilyMember());
			}
		}
		else if(change instanceof EventPlaceFamilyMember) {
			currentPlayer = match.getCurrentPlayer();
			if(isMyTurn()) {
				view.askAction(true);
			}
		}
	}
	
	public void createView(MatchRemote match) {
		view = new ViewCLI(match, client);
	}

	private void getMatch() throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(client.HOST, client.PORT);
		//get the stub (local object) of the remote view
		match = (MatchRemote) registry.lookup(MODEL_VIEW);
	}

	private boolean isMyTurn(){
		if(currentPlayer == null) {
			System.out.println("MyTurn: current player is null");
		}
		else {
			System.out.println("MyTurn: current player is NOT null");
			if(name.equals(currentPlayer.getName()))
				System.out.println("It's your turn");
		}
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
