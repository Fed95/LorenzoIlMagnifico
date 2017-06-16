package it.polimi.ingsw.gc_12.server.view;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.server.Server;
import it.polimi.ingsw.gc_12.server.controller.Change;
import it.polimi.ingsw.gc_12.server.controller.query.Query;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.util.Set;

public class ServerSocketView extends View implements Runnable {

	private Socket socket;
	private Server server;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private Match match;

	public ServerSocketView(Socket socket, Match match, Server server) throws IOException {
		// creates the streams to communicate with the client-side, and the reference to the model
		this.socket = socket;
		this.match = match;
		this.server = server;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Event event) {
		System.out.println("Sending to the client " + event.getClass().getSimpleName());

		// sending the info to the client
		try {
			this.socketOut.writeObject(event);
			this.socketOut.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				try {
					Object object = socketIn.readObject();
					System.out.println(object);
					if (object instanceof String) {
						String name = (String) object;
						System.out.println("Player " + name + " received");
						server.addClientSocket(name);
					}
					else if (object instanceof Integer) {
						int input = (Integer) object;
						Action action = match.getActionHandler().getAvailableActions().get(input);
						System.out.println("RMIView: " + action.getClass().getSimpleName() + " received from ClientRMI. Notifying observers (Server Controller).");
						this.notifyObserver(action);
					}


				} catch (Exception e ) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
