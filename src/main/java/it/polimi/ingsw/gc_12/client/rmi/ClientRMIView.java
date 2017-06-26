package it.polimi.ingsw.gc_12.client.rmi;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.PlayerColor;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventExcluded;
import it.polimi.ingsw.gc_12.event.EventStartTurn;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.LinkedList;

public class ClientRMIView extends ClientHandler implements ClientViewRemote, Serializable{

	private String name;

	protected ClientRMIView(String name) throws RemoteException {
		super();
		this.name = name;
	}

	@Override
	public synchronized void updateClient(Event event) {
		System.out.println("RECEIVED " + event.getClass().getSimpleName());
		if(event instanceof EventExcluded)
			events = new LinkedList<>();
		events.addLast(event);

		if(events.size() <= 1) {
			handleEvent();
		}
	}

	@Override
	public String getName() throws RemoteException {
		return name;
	}

	@Override
	public PlayerColor getPlayerColor() throws RemoteException {
		return color;
	}

	@Override
	public void setColor(PlayerColor playerColor) throws RemoteException {
		color = playerColor;
		System.out.println("Your color is "+color);
	}


	private static final long serialVersionUID = 6111979881550001331L;
}
