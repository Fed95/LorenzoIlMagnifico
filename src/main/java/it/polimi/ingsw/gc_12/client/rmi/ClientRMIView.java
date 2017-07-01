package it.polimi.ingsw.gc_12.client.rmi;

import it.polimi.ingsw.gc_12.PlayerColor;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventExcluded;
import it.polimi.ingsw.gc_12.java_fx.MainBoard;
import it.polimi.ingsw.gc_12.mvc.ClientView;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.LinkedList;

public class ClientRMIView extends ClientHandler implements ClientViewRemote, Serializable{

	private String name;

	protected ClientRMIView(String name, ClientView view) throws RemoteException {
		super(view);
		this.name = name;
	}

	@Override
	public void updateClient(Event event) {
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
	public void setColor(PlayerColor playerColor) {
		color = playerColor;
		System.out.println("Your color is "+color);
		unauthorizedId = 0;
	}

	@Override
	public void askNewName(int unauthorizedId) throws RemoteException {
		System.out.println("This name is already taken from another active player.");
		System.out.println("Choose another one.");
		this.unauthorizedId = unauthorizedId;
		if(view instanceof MainBoard)
			((MainBoard) view).errorNameTaken();
	}

	@Override
	public void checkConnection() throws RemoteException {}

	private static final long serialVersionUID = 6111979881550001331L;
}
