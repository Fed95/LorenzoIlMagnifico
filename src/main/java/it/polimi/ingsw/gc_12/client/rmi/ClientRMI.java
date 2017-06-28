package it.polimi.ingsw.gc_12.client.rmi;

import it.polimi.ingsw.gc_12.client.ClientFactory;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.ClientSender;
import it.polimi.ingsw.gc_12.java_fx.MainBoard;
import it.polimi.ingsw.gc_12.mvc.View;
import it.polimi.ingsw.gc_12.mvc.ViewCLI;
import it.polimi.ingsw.gc_12.server.view.RMIViewRemote;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientRMI implements ClientSender { //Main class of the Clients using RMI

	private final static int RMI_PORT = 52365;
	public final static String HOST = "127.0.0.1";
	public final static int PORT = 52365;
	private static final String NAME = "lorenzo";

	private RMIViewRemote serverStub;
	private Registry registry;
	private ClientRMIView rmiView;

	public void start(View view, String name) throws IOException, NotBoundException, AlreadyBoundException, CloneNotSupportedException {
		//Get the remote registry
		registry = LocateRegistry.getRegistry(HOST, PORT);

		//get the stub (local object) of the remote view
		serverStub = (RMIViewRemote) registry.lookup(NAME);

		rmiView = new ClientRMIView(name, view);
		ClientFactory.setClientHandler(rmiView);
		System.out.println("You are being registered on the server...");
		// register the client view in the server side (to receive messages from the server)
		serverStub.registerClient(rmiView);
		//view = new ViewCLI(this, rmiView);
		view.setClientSender(this);
		view.setClientHandler(rmiView);
		//view.start();
	}
	
	/*public void askAction(boolean isFMPlaced) throws RemoteException {
		view.askAction(isFMPlaced);
	}*/
	
	public void sendAction(int input) throws RemoteException {
		serverStub.receiveAction(input);
	}
}
