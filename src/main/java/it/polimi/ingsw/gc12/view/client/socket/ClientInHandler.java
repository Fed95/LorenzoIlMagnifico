package it.polimi.ingsw.gc12.view.client.socket;

import it.polimi.ingsw.gc12.model.event.*;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.view.client.ClientHandler;
import it.polimi.ingsw.gc12.view.client.ClientView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.util.LinkedList;

/**
 * Receives objects from the ServerSocketView, mostly events.
 */

public class ClientInHandler extends ClientHandler implements Runnable {

	private ObjectInputStream socketIn;
	
	public ClientInHandler(ObjectInputStream socketIn, ClientOutHandler clientOut, ClientView view) throws RemoteException {
		super(view);
		this.socketIn = socketIn;
	}
	
	@Override
	public void run() {
		while(true){

			try {
				Object object = socketIn.readObject();
				if(object instanceof Event) {
					Event event = (Event) object;
					// If the event is of that king of type. Show it directly without wait for the event to be popped
					// by the queue and executed by handleEvent.
					if(event instanceof EventMessage || event instanceof EventPlayerReconnected || event instanceof EventExcluded || event instanceof EventMatchSuspended)
						showMessage(event);

					// Flush the queue of events for this kind of events
					if((event instanceof EventExcluded && color.equals(event.getPlayer().getColor())) || event instanceof EventMatchSuspended)
						events = new LinkedList<>();

					events.addLast(event);
					if(events.size() <= 1) {
						handleEvent();
					}
				}
				else if(object instanceof PlayerColor) {
					// Color chosen by the server when a player is connected has been assigned to the player
					// and sent to him/her
					color = (PlayerColor) object;
					System.out.println("Your color is "+color);
				}
			}
			catch (SocketException e) {
				// The server crashed or closed
				System.out.println("The server closed!");
				System.exit(1);
			}
			catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
