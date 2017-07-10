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


public class ClientInHandler extends ClientHandler implements Runnable {

	private ObjectInputStream socketIn;
	private ClientOutHandler clientOut;
	
	public ClientInHandler(ObjectInputStream socketIn, ClientOutHandler clientOut, ClientView view) throws RemoteException {
		super(view);
		this.socketIn = socketIn;
		this.clientOut = clientOut; // TODO: remove it?
	}
	
	@Override
	public void run() {
		while(true){
			// handles input messages coming from the server, just showing them to the user
			try {
				Object object = socketIn.readObject();
				if(object instanceof Event) {
					Event event = (Event) object;
					if(event instanceof EventMessage || event instanceof EventPlayerReconnected || event instanceof EventExcluded || event instanceof EventMatchSuspended)
						showMessage(event);
					if((event instanceof EventExcluded && color.equals(event.getPlayer().getColor())) || event instanceof EventMatchSuspended)
						events = new LinkedList<>();
					events.addLast(event);
					if(events.size() <= 1) {
						handleEvent();
					}
				}
				else if(object instanceof PlayerColor) {
					color = (PlayerColor) object;
					System.out.println("Your color is "+color);
				}
			}
			catch (SocketException e) {
				System.out.println("The server closed!");
				System.exit(1);
			}
			catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
