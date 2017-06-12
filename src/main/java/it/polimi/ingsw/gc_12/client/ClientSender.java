package it.polimi.ingsw.gc_12.client;

import it.polimi.ingsw.gc_12.action.Action;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by marco on 12/06/2017.
 */
public interface ClientSender {

	void sendAction(Action action) throws IOException;
}
