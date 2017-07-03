package it.polimi.ingsw.gc_12.server.view;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.PlayerColor;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
		// TODO Auto-generated method stub
	}

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

		} catch (IOException e) {
			Player player = match.getPlayer(name);
			if(!player.isDisconnected()) {
				match.unregisterObserver(this);
				match.setDisconnectedPlayer(player);
			}
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
				System.out.println(object);
				if (object instanceof String) {
					name = (String) object;
					System.out.println("Player " + name + " received");
					if (server.isNameTaken(name)) {
						if(!server.tryReconnection(new Player(name, null), this))
							askNewName();
					} else
						acceptName();

				} else if (object instanceof Integer) {
					int input = (Integer) object;
					Action action = match.getActionHandler().getAvailableAction(input);
					System.out.println("ServerRMIView: " + action.getClass().getSimpleName() + " received from ClientRMI. Notifying observers (Server Controller).");
					this.notifyObserver(action);
				} else if (object instanceof EventNewName) {
					EventNewName eventNewName = (EventNewName) object;
					name = eventNewName.getName();
					if (server.isNameTaken(name)) {
						unauthorizedClients.put(eventNewName.getUnauthorizedId(), name);
						socketOut.writeObject(new EventNewName(eventNewName.getUnauthorizedId(), name));
					} else
						acceptName();
				} else if(object instanceof PlayerColor) {
					Player player = match.getPlayers().get(object);
					if(player.isExcluded()) {
						match.includePlayer(player);
					}
				}
			}
		}
		catch (IOException e) {
			Player player = match.getPlayer(name);
			if(!player.isDisconnected()) {

				match.unregisterObserver(this);
				match.setDisconnectedPlayer(player);
			}
		}
		catch (ClassNotFoundException | CloneNotSupportedException | AlreadyBoundException e) {
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
