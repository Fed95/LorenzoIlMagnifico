package it.polimi.ingsw.gc12.view.server;

import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventNewName;
import it.polimi.ingsw.gc12.model.event.EventPlayerReconnected;
import it.polimi.ingsw.gc12.model.event.EventTowerChosen;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.action.Action;
import it.polimi.ingsw.gc12.view.client.rmi.ClientViewRemote;
import it.polimi.ingsw.gc12.Server;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.ConnectException;
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
			}
			catch (ConnectException ignored) {}
			catch (RemoteException e) {
				e.printStackTrace();
			}
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
			} catch (ConnectException e) {
				if(!player.isDisconnected())
					match.setDisconnectedPlayer(player);
				itr.remove();
			}
			catch (RemoteException e) {
				e.printStackTrace();
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

	@Override
	public void receiveColor(PlayerColor color) throws RemoteException {
		Player player = match.getPlayers().get(color);
		if(player.isExcluded()) {
			match.includePlayer(player);
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
		EventNewName event = new EventNewName(incrementalId);
		client.updateClient(event);
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