package it.polimi.ingsw.gc_12.client;


import it.polimi.ingsw.gc_12.java_fx.MainBoard;
import it.polimi.ingsw.gc_12.mvc.ClientView;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

public class ClientGUI {
	public static void main(String[] args) throws IOException, CloneNotSupportedException, NotBoundException, AlreadyBoundException {
        ClientView view = new MainBoard();
        view.start();
    }
}
