package it.polimi.ingsw.gc_12.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
	Player getPlayer() throws RemoteException;
}
