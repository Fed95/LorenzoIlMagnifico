package it.polimi.ingsw.gc_12.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
	void connect(ClientInterface client) throws RemoteException;
	void disconnect(ClientInterface client) throws RemoteException;
	void send(Message m) throws RemoteException;

}
