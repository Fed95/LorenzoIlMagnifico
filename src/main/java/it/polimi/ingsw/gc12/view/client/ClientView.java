package it.polimi.ingsw.gc12.view.client;

import it.polimi.ingsw.gc12.view.client.ClientViewType;
import it.polimi.ingsw.gc12.model.event.EventView;
import it.polimi.ingsw.gc12.misc.observer.Observer;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

public interface ClientView extends Observer<EventView> {

	void start() throws IOException, CloneNotSupportedException, NotBoundException, AlreadyBoundException;
	boolean isReady();
	ClientViewType getType();
}
