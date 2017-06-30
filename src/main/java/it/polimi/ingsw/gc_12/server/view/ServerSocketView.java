package it.polimi.ingsw.gc_12.server.view;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.PlayerColor;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.client.NewName;
import it.polimi.ingsw.gc_12.client.rmi.ClientViewRemote;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventStartRound;
import it.polimi.ingsw.gc_12.event.EventStartTurn;
import it.polimi.ingsw.gc_12.server.Server;
import it.polimi.ingsw.gc_12.server.controller.Controller;
import it.polimi.ingsw.gc_12.server.observer.Observer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ServerSocketView extends View implements Runnable {

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

		if(event instanceof EventStartTurn && i == 0) {
			setTimeoutAction();
			i++;
		}


		// sending the info to the client
		try {
			this.socketOut.writeObject(event);
			this.socketOut.flush();
			this.socketOut.reset();

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
				} else if (object instanceof NewName) {
					NewName newName = (NewName) object;
					name = newName.getName();
					if (server.isNameTaken(name)) {
						unauthorizedClients.put(newName.getUnauthorizedId(), name);
						socketOut.writeObject(new NewName(newName.getUnauthorizedId(), name));
					} else
						acceptName();
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
		socketOut.writeObject(new NewName(incrementalId, name));
	}

	private void acceptName() throws IOException, AlreadyBoundException, CloneNotSupportedException {
		PlayerColor playerColor = playerColors.poll();
		socketOut.writeObject(playerColor);
		server.addPlayer(this, new Player(name, playerColor));
	}

}
