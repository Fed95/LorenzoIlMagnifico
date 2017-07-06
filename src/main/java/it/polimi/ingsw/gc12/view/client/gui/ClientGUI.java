package it.polimi.ingsw.gc12.view.client.gui;


import it.polimi.ingsw.gc12.view.client.ClientView;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

public class ClientGUI {
	public static void main(String[] args) throws IOException, CloneNotSupportedException, NotBoundException, AlreadyBoundException {
        ClientView view = new MainBoard();
        view.start();
    }
}
