package it.polimi.ingsw.gc12.view.client.rmi;

import it.polimi.ingsw.gc12.model.event.*;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.view.client.ClientHandler;
import it.polimi.ingsw.gc12.view.client.ClientView;

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

		if(event instanceof EventMessage || event instanceof EventPlayerReconnected || event instanceof EventExcluded || event instanceof EventMatchSuspended)
			showMessage(event);

		if(event instanceof EventExcluded || event instanceof EventMatchSuspended)
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
	public void checkConnection() throws RemoteException {}

	private static final long serialVersionUID = 6111979881550001331L;
}
