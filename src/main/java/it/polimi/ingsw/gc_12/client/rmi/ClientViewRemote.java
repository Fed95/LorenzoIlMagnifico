package it.polimi.ingsw.gc_12.client.rmi;

import it.polimi.ingsw.gc_12.server.controller.Change;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientViewRemote extends Remote{
	// Interface to receive information from the server
	void updateClient(Change c) throws IOException, NotBoundException;
	String getName() throws RemoteException;
}
