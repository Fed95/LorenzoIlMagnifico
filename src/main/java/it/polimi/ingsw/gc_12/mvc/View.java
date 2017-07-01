package it.polimi.ingsw.gc_12.mvc;

import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.ClientSender;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

public interface View {

	void start() throws IOException, CloneNotSupportedException, NotBoundException, AlreadyBoundException;
	boolean isReady();
}
