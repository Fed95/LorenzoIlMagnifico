package it.polimi.ingsw.gc_12;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by marco on 07/06/2017.
 */
public interface MatchRemote extends Remote {

	Player getCurrentPlayer() throws RemoteException;
	boolean isFMPlaced() throws RemoteException; 
}
