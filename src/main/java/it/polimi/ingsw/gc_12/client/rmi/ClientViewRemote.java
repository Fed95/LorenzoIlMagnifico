package it.polimi.ingsw.gc_12.client.rmi;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.PlayerColor;
import it.polimi.ingsw.gc_12.event.Event;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientViewRemote extends Remote{
	// Interface to receive information from the server
	void updateClient(Event event) throws RemoteException;
	String getName() throws RemoteException;
	void setColor(PlayerColor playerColor) throws RemoteException;
}
