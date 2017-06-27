package it.polimi.ingsw.gc_12.client;

import it.polimi.ingsw.gc_12.mvc.View;
import it.polimi.ingsw.gc_12.mvc.ViewCLI;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

public class ClientCLI extends Client {

	public static void main(String[] args) throws IOException, CloneNotSupportedException, NotBoundException, AlreadyBoundException {
		View view = new ViewCLI();
		Client client = new ClientCLI();
		client.start(view);

	}
}
