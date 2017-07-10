package it.polimi.ingsw.gc12.view.server;

import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventEndMatch;
import it.polimi.ingsw.gc12.model.event.EventNewName;
import it.polimi.ingsw.gc12.model.event.EventPlayerReconnected;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.action.Action;
import it.polimi.ingsw.gc12.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.AlreadyBoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Socket part of the server view. There is one instance of ServerSocketView for each player using sockets.
 * It receives events from the match (it observes it through the superclass ServerView) and it forwards them to the client.
 * It receives objects from the client and it's responsible to catch if the player disconnected
 * and to handle the first exchange of data to be sure that the name chosen by the player is not already taken.
 */

public class ServerSocketView extends ServerView implements Runnable {

	private Socket socket;
	private Server server;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private String name;

	private LinkedList<PlayerColor> playerColors;
	private Map<Integer, String> unauthorizedClients = new HashMap<>();

	public ServerSocketView(Socket socket, Match match, Server server, LinkedList<PlayerColor> playerColors) throws IOException {
		super(match);
		// creates the streams to communicate with the client-side, and the reference to the model
		this.socket = socket;
		this.server = server;
		this.playerColors = playerColors;
	}

	@Override
	public void update() {
	}

	/**
	 * Receives events from the match and sends them to the client connected to this view through socket
	 * @param event
	 */
	@Override
	public void update(Event event) {
		System.out.println("Sending to the client " + event.getClass().getSimpleName());

		// If sending an EventPlayerReconnected of a RMI client to a socket client, don't send the ServerRMI
		if(event instanceof EventPlayerReconnected) {
			EventPlayerReconnected eventRec = (EventPlayerReconnected) event;
			if(eventRec.getClientViewRemote() != null)
				event = new EventPlayerReconnected(eventRec.getPlayer(), eventRec.getMatch());
		}

		// sending the info to the client
		try {
			sendObject(event);

		} catch (SocketException e) {
			// Catch if the player disconnected and notify to the match which player disconnected.
			Player player = match.getPlayer(name);
			if(!player.isDisconnected()) {
				match.unregisterObserver(this);
				match.setDisconnectedPlayer(player);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		if(event instanceof EventEndMatch) {
			server.endMatch(match);
		}
	}

	@Override
	public void run() {
		try {
			socketIn = new ObjectInputStream(socket.getInputStream());
			socketOut = new ObjectOutputStream(socket.getOutputStream());

			//server.increaseClientsNum();
			while (true) {
				// await for incoming data from the client
				//try {
				Object object = socketIn.readObject();

				// Get the first name chosen by the player before it connects completely
				if (object instanceof String) {
					name = (String) object;
					System.out.println("Player " + name + " received");

					// Check if there is already a player with that name
					if (server.isNameTaken(name)) {
						// If the player with that name is disconnected, maybe he is trying to reconnect
						if(!server.tryReconnection(new Player(name, null), this))
							askNewName();
					} else
						acceptName();
				} else if (object instanceof Integer) {
					// Receives the index among the possible actions previously sent to the client and saved in the ActionHandler
					int input = (Integer) object;

					// Get the action corresponding to the index "input"
					Action action = match.getActionHandler().getAvailableAction(input);
					System.out.println("ServerRMIView: " + action.getClass().getSimpleName() + " received from ClientRMI. Notifying observers (Server Controller).");
					this.notifyObserver(action);
				} else if (object instanceof EventNewName) {
					// New name received from the client because the one previously chosen was already taken
					EventNewName eventNewName = (EventNewName) object;
					name = eventNewName.getName();

					// Check again if this name is taken
					if (server.isNameTaken(name)) {
						// The unauthorizedId is used to identify which one of the client is trying to choose a name
						// because it's not possible to identify it by the name because it has not been chosen
						// and not by the color because the match didn't started yet
						unauthorizedClients.put(eventNewName.getUnauthorizedId(), name);
						socketOut.writeObject(new EventNewName(eventNewName.getUnauthorizedId(), name));
					} else
						acceptName();
				} else if(object instanceof PlayerColor) {
					// The player identified with this color is rejoining the match after being excluded
					// for the action timeout's expiration
					Player player = match.getPlayers().get(object);
					if(player.isExcluded()) {
						match.includePlayer(player);
					}
				}
			}
		}
		catch (SocketException e) {
			// Catches if the player disconnected
			Player player = match.getPlayer(name);
			if(!player.isDisconnected()) {

				match.unregisterObserver(this);
				match.setDisconnectedPlayer(player);
			}
		}
		catch (IOException | ClassNotFoundException | CloneNotSupportedException | AlreadyBoundException e) {
			e.printStackTrace();
		}
	}

	private void askNewName() throws IOException {
		while (server.isNameTaken(String.valueOf(incrementalId))) {
			incrementalId++;
		}
		unauthorizedClients.put(incrementalId, name);
		sendObject(new EventNewName(incrementalId, name));
	}

	private void acceptName() throws IOException, AlreadyBoundException, CloneNotSupportedException {
		PlayerColor playerColor = playerColors.poll();
		sendObject(playerColor);
		server.addPlayer(new Player(name, playerColor));
	}

	private synchronized void sendObject(Object object) throws IOException {
		this.socketOut.writeObject(object);
		this.socketOut.flush();
		this.socketOut.reset();
	}

}
