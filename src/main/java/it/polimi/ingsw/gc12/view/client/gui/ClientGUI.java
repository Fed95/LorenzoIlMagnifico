package it.polimi.ingsw.gc12.view.client.gui;


import it.polimi.ingsw.gc12.view.client.ClientView;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

/**
 * Main class that start the GUI
 */
public class ClientGUI {
    /**
     * @throws IOException
     * @throws CloneNotSupportedException
     * @throws NotBoundException
     * @throws AlreadyBoundException
     */
	public static void main(String[] args) throws IOException, CloneNotSupportedException, NotBoundException, AlreadyBoundException {
        ClientView view = new MainBoard();
        view.start();
    }
}
