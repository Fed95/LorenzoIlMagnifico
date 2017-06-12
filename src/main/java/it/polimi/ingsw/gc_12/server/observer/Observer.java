package it.polimi.ingsw.gc_12.server.observer;

import java.io.IOException;
import java.rmi.RemoteException;

public interface Observer<C> {

	public default void update(C o) throws IOException, RemoteException {

		System.out.println("I am the " + this.getClass().getSimpleName() +
				". I have been notified with the "+o.getClass().getSimpleName());
	}

	public void update();
}
