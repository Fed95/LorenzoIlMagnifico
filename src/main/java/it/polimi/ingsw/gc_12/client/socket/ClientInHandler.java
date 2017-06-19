package it.polimi.ingsw.gc_12.client.socket;

import it.polimi.ingsw.gc_12.MatchInstance;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.event.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.util.List;


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
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	protected boolean isMyTurn(Player currentPlayer){
		if(currentPlayer == null) {
			System.out.println("MyTurn: current player is null");
		}
		else {

		}
		return clientOut.getName().equals(currentPlayer.getName());
	}
}
