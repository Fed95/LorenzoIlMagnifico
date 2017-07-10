package it.polimi.ingsw.gc12.view.server;

import it.polimi.ingsw.gc12.model.event.*;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.action.Action;
import it.polimi.ingsw.gc12.view.client.rmi.ClientViewRemote;
import it.polimi.ingsw.gc12.Server;

import java.io.IOException;
import java.rmi.*;
import java.util.*;

/**
 * RMI part of the server view. There is only one instance of ServerRMIView for each match,
 * because it saves all the clients using RMI in the list clients.
 * When it receives an event from the match of which it is observer, the ServerRMIVIew sends it to the clients.
 * It also receives messages from the clients for multiple purposes.
 */

public class ServerRMIView extends ServerView implements RMIViewRemote {

	private Set<ClientViewRemote> clients;
	private Map<ClientViewRemote, Player> clientPlayers = new HashMap<>();
	private Server server;
	private LinkedList<PlayerColor> playerColors;
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

		// If a player reconnected, to be able to communicate with the server it already has exported a ServerRMIView
		// from the registry. For that reason when a player reconnects it has to receive the right ServerRMIView
		// of the match he/she was previously playing.
		if(event instanceof EventPlayerReconnected) {
			EventPlayerReconnected eventReconnected = (EventPlayerReconnected) event;
			if(eventReconnected.getClientViewRemote() != null) {
				clients.add(eventReconnected.getClientViewRemote());
				clientPlayers.put(eventReconnected.getClientViewRemote(), event.getPlayer());
				eventReconnected.setServerRMI(this);
			}
		}
		System.out.println("RMIView: sending " + event.getClass().getSimpleName() + " to the client");
		Iterator<ClientViewRemote> itr = clients.iterator();
		while(itr.hasNext()) {
			ClientViewRemote clientStub = itr.next();
			try {
				clientStub.updateClient(event);
			}
			// The player disconnected, but the ServerRMIView doesn't handle the disconnection because
			// the update() method stated later is responsible for that.
			catch (ConnectException | UnmarshalException | ConnectIOException ignored) {}
			catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		if(event instanceof EventEndMatch) {
			server.endMatch(match);
		}

	}

	// Every short period of time (about 1 sec) the ServerRMI sends an update to the client to check if it connected.
	// If the player is disconnected, the exception is catched and the notification is sent to the match.
	@Override
	public void update() {
		Iterator<ClientViewRemote> itr = clients.iterator();
		while(itr.hasNext()) {
			ClientViewRemote clientStub = itr.next();
			Player player = clientPlayers.get(clientStub);
			try {
				clientStub.checkConnection();
			} catch (ConnectException | UnmarshalException | ConnectIOException e) {
				if(!player.isDisconnected())
					match.setDisconnectedPlayer(player);
				itr.remove();
			}
			catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Receives the index among the possible actions previously sent to the client and saved in the ActionHandler.
	 * It sends the extracted action to the controller that executes it.
	 * @param input The index of the action chosen by the player
	 */
	@Override
	public void receiveAction(int input){
		Action action = match.getActionHandler().getAvailableAction(input);
		System.out.println("ServerRMIView: " + action.getClass().getSimpleName() + " received from ClientRMI. Notifying observers (Server Controller).");
		this.notifyObserver(action);
	}

	/**
	 * New name received from the client because the one previously chosen was already taken.
	 * The unauthorizedId is used to identify which one of the client is trying to choose a name
	 * because it's not possible to identify it by the name because it has not been chosen
	 * and not by the color because the match didn't started yet
	 * @param name
	 * @param unauthorizedId
	 * @throws IOException
	 */

	@Override
	public void receiveName(String name, int unauthorizedId) throws IOException {
		try {
			checkNewName(name, unauthorizedClients.get(unauthorizedId));
		} catch (AlreadyBoundException | CloneNotSupportedException e) {
			askNewName(unauthorizedClients.get(unauthorizedId));
		}
	}

	/**
	 * The player identified with this color is rejoining the match after being excluded
	 * for the action timeout's expiration
	 * @param color
	 * @throws RemoteException
	 */

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
