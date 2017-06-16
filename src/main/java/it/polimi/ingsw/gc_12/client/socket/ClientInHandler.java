package it.polimi.ingsw.gc_12.client.socket;

import it.polimi.ingsw.gc_12.MatchInstance;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.event.*;

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
				if(object instanceof EventStartMatch) {
					System.out.println("ClientRMI: EventStartMatch recognised. Creating view with local match.");
					EventStartMatch event = (EventStartMatch) object;
					match = event.getMatchInstance();

					createView(match);
				}
				else {
					handleEvent(object);
				}

				/*if(object instanceof Set<?>){
					System.out.println(object);
					Set<Prigioniero> prigionieri=(Set<Prigioniero>)object;
					prigionieri.iterator().next().setName("pippo");
				}*/
				

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void createView(MatchInstance match) {
		//view = new ViewCLI(match, clientOut);
	}

	@Override
	protected boolean isMyTurn(Player currentPlayer){
		if(currentPlayer == null) {
			System.out.println("MyTurn: current player is null");
		}
		else {
			if(clientOut.getName().equals(currentPlayer.getName()))
				System.out.println("It's your turn");
			else
				System.out.println("It's " + currentPlayer.getName() + "'s turn.");
		}
		return clientOut.getName().equals(currentPlayer.getName());
	}
}
