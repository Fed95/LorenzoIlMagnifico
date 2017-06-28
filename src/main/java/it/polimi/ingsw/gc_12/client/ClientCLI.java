package it.polimi.ingsw.gc_12.client;

import it.polimi.ingsw.gc_12.mvc.View;
import it.polimi.ingsw.gc_12.mvc.ViewCLI;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

public class ClientCLI {

	public static void main(String[] args) throws IOException, CloneNotSupportedException, NotBoundException, AlreadyBoundException {
		View view = new ViewCLI();
        view.start();
	}
}
