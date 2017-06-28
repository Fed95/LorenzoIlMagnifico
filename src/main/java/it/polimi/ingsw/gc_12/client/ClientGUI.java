package it.polimi.ingsw.gc_12.client;


import it.polimi.ingsw.gc_12.java_fx.MainBoard;
import it.polimi.ingsw.gc_12.mvc.View;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

public class ClientGUI extends Client {
	public static void main(String[] args) throws IOException, CloneNotSupportedException, NotBoundException, AlreadyBoundException {
        View view = new MainBoard();
        view.start();
		// TODO: remove this and the superclass because it will ask for name and technology directly inside the view
    }
}
