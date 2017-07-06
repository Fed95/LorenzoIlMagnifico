package it.polimi.ingsw.gc12.view.client.rmi;

import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.event.Event;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientViewRemote extends Remote{
	// Interface to receive information from the server
	void updateClient(Event event) throws RemoteException;
	String getName() throws RemoteException;
	void setColor(PlayerColor playerColor) throws IOException;
	void checkConnection() throws RemoteException;
}
