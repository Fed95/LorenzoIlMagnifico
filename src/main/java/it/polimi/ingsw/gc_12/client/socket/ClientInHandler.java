package it.polimi.ingsw.gc_12.client.socket;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.PlayerColor;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.event.Event;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;


public class ClientInHandler extends ClientHandler implements Runnable {

	private ObjectInputStream socketIn;
	private ClientOutHandler clientOut;
	
	public ClientInHandler(ObjectInputStream socketIn, ClientOutHandler clientOut) throws RemoteException {
		super();
		this.socketIn = socketIn;
		this.clientOut = clientOut;
	}
	
	@Override
	public void run() {
		while(true){
			// handles input messages coming from the server, just showing them to the user
			try {
				Object object = socketIn.readObject();
				if(object instanceof Event) {
					Event event = (Event) object;
					System.out.println("RECEIVED "+event.getClass().getSimpleName());
					events.addLast(event);
					if(events.size() <= 1) {
						handleEvent();
					}
				}
				else if(object instanceof PlayerColor) {
					color = (PlayerColor) object;
					System.out.println("Your color is "+color);
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
