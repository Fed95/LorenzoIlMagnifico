package it.polimi.ingsw.gc_12.server.view;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.PlayerColor;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.action.ActionPassTurn;
import it.polimi.ingsw.gc_12.client.rmi.ClientViewRemote;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventStartTurn;
import it.polimi.ingsw.gc_12.event.EventTowerChosen;
import it.polimi.ingsw.gc_12.server.Server;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ServerRMIView extends View implements RMIViewRemote {

	private Set<ClientViewRemote> clients;
	private Server server;
	private LinkedList<PlayerColor> playerColors;
	private boolean recievedAnsewr = false;

	public ServerRMIView(Server server, Match match, LinkedList<PlayerColor> playerColors) {
		super(match);
		this.clients = new HashSet<>();
		this.server = server;
		this.playerColors = playerColors;
	}

	@Override
	public void registerClient(ClientViewRemote clientStub) throws IOException, AlreadyBoundException, CloneNotSupportedException {
		String name = clientStub.getName();

		System.out.println("CLIENT REGISTERED");

		this.clients.add(clientStub);
		PlayerColor playerColor = playerColors.poll();
		clientStub.setColor(playerColor);
		Player player = new Player(name, playerColor);
		server.addPlayer(player);
	}

	@Override
	public void update(Event event) {
		if(event instanceof EventTowerChosen)
			recievedAnsewr = false;

		if(event instanceof EventStartTurn && i == 0) {
			setTimeoutAction();
			i++;
		}
		System.out.println("RMIVIEW: SENDING " + event.getClass() + " CHANGE TO THE CLIENT");
		for (ClientViewRemote clientStub : this.clients) {
			try {
				clientStub.updateClient(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void receiveAction(int input){
		recievedAnsewr = true;
		Action action = match.getActionHandler().getAvailableAction(input);
		System.out.println("ServerRMIView: " + action.getClass().getSimpleName() + " received from ClientRMI. Notifying observers (Server Controller).");
		this.notifyObserver(action);
	}

	public Match getMatch() {
		return match;
	}
}
