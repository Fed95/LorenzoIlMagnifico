package it.polimi.ingsw.gc_12.mvc;

import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.ClientSender;
import it.polimi.ingsw.gc_12.client.ClientViewType;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.server.observer.Observer;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

public interface ClientView extends Observer<Event> {

	void start() throws IOException, CloneNotSupportedException, NotBoundException, AlreadyBoundException;
	boolean isReady();
	ClientViewType getType();
}
