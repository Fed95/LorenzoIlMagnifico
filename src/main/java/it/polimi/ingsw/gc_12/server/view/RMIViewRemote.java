package it.polimi.ingsw.gc_12.server.view;

import it.polimi.ingsw.gc_12.PlayerColor;
import it.polimi.ingsw.gc_12.client.rmi.ClientViewRemote;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIViewRemote extends Remote {

	void registerClient(ClientViewRemote clientStub) throws IOException, AlreadyBoundException, CloneNotSupportedException;
	void receiveAction(int input) throws RemoteException;
	void receiveName(String name, int unauthorizedId) throws IOException;
	void receiveColor(PlayerColor color) throws RemoteException;

}
