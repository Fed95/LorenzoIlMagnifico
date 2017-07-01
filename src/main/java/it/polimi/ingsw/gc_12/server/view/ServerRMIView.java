package it.polimi.ingsw.gc_12.server.view;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.PlayerColor;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.client.rmi.ClientViewRemote;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.server.Server;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.*;

public class ServerRMIView extends ServerView implements RMIViewRemote {

	private Set<ClientViewRemote> clients;
	private Map<ClientViewRemote, Player> clientPlayers = new HashMap<>();
	private Server server;
	private LinkedList<PlayerColor> playerColors;
	private boolean recievedAnsewr = false; // TODO: remove it?
	private Map<Integer, ClientViewRemote> unauthorizedClients = new HashMap<>();

	public ServerRMIView(Server server, Match match, LinkedList<PlayerColor> playerColors) {
		super(match);
		this.clients = new HashSet<>();
		this.server = server;
		this.playerColors = playerColors;
	}

	@Override
	public void registerClient(ClientViewRemote clientStub) throws IOException, AlreadyBoundException, CloneNotSupportedException {
		String name = clientStub.getName();
		checkNewName(name, clientStub);
	}

	@Override
	public void update(Event event) {
		if(event instanceof EventTowerChosen)
			recievedAnsewr = false;

		if(event instanceof EventStartTurn && i == 0) {
			setTimeoutAction();
			i++;
		}

		if(event instanceof EventPlayerReconnected) {
			EventPlayerReconnected eventReconnected = (EventPlayerReconnected) event;
			if(eventReconnected.getClientViewRemote() != null) {
				clients.add(eventReconnected.getClientViewRemote());
				clientPlayers.put(eventReconnected.getClientViewRemote(), event.getPlayer());
				eventReconnected.setServerRMI(this);
			}
		}
		System.out.println("RMIVIEW: SENDING " + event.getClass() + " CHANGE TO THE CLIENT");
		Iterator<ClientViewRemote> itr = clients.iterator();
		while(itr.hasNext()) {
			ClientViewRemote clientStub = itr.next();
			try {
				clientStub.updateClient(event);
			} catch (RemoteException ignored) {}
		}

	}

	@Override
	public void update() {
		Iterator<ClientViewRemote> itr = clients.iterator();
		while(itr.hasNext()) {
			ClientViewRemote clientStub = itr.next();
			Player player = clientPlayers.get(clientStub);
			try {
				clientStub.checkConnection();
			} catch (RemoteException e) {
				if(!player.isDisconnected())
					match.setDisconnectedPlayer(player);
				itr.remove();
			}
		}
	}

	@Override
	public void receiveAction(int input){
		recievedAnsewr = true;
		Action action = match.getActionHandler().getAvailableAction(input);
		System.out.println("ServerRMIView: " + action.getClass().getSimpleName() + " received from ClientRMI. Notifying observers (Server Controller).");
		this.notifyObserver(action);
	}

	@Override
	public void receiveName(String name, int unauthorizedId) throws IOException {
		try {
			checkNewName(name, unauthorizedClients.get(unauthorizedId));
		} catch (AlreadyBoundException | CloneNotSupportedException e) {
			askNewName(unauthorizedClients.get(unauthorizedId));
		}
	}

	private void checkNewName(String name, ClientViewRemote client) throws IOException, AlreadyBoundException, CloneNotSupportedException {
		if(server.isNameTaken(name)) {
			Player player = new Player(name, null);
			if(!server.tryReconnection(player, client))
				askNewName(client);
		}
		else {
			acceptName(name, client);
		}
	}

	private void askNewName(ClientViewRemote client) throws RemoteException {
		while(server.isNameTaken(String.valueOf(incrementalId))){
			incrementalId++;
		}
		unauthorizedClients.put(incrementalId, client);
		client.askNewName(incrementalId);
	}

	private void acceptName(String name, ClientViewRemote client) throws IOException, AlreadyBoundException, CloneNotSupportedException {
		this.clients.add(client);
		PlayerColor playerColor = playerColors.poll();
		client.setColor(playerColor);
		Player player = new Player(name, playerColor);
		clientPlayers.put(client, player);
		server.addPlayer(player);
	}

	public Match getMatch() {
		return match;
	}
}
