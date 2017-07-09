package it.polimi.ingsw.gc12.view.client.rmi;

import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.view.client.ClientFactory;
import it.polimi.ingsw.gc12.view.client.ClientSender;
import it.polimi.ingsw.gc12.model.event.EventNewName;
import it.polimi.ingsw.gc12.view.client.ClientView;
import it.polimi.ingsw.gc12.view.server.RMIViewRemote;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Observable;

public class ClientRMI implements ClientSender { //Main class of the Clients using RMI

	private final static int RMI_PORT = 52365;
	public final static String HOST = "127.0.0.1";
	public final static int PORT = 52365;
	private static final String NAME = "lorenzo";

	private RMIViewRemote serverStub;

	public void start(ClientView view, String name) throws IOException, NotBoundException, AlreadyBoundException, CloneNotSupportedException {
		//Get the remote registry
		Registry registry = LocateRegistry.getRegistry(HOST, PORT);

		//get the stub (local object) of the remote view
		serverStub = (RMIViewRemote) registry.lookup(NAME);

		ClientRMIView rmiView = new ClientRMIView(name, view);
		ClientFactory.setClientHandler(rmiView);
		ClientFactory.setClientSender(this);
		System.out.println("You are being registered on the server...");
		// register the client view in the server side (to receive messages from the server)
		serverStub.registerClient(rmiView);
		//view = new ViewCLI(this, rmiView);
	}
	
	public void sendAction(int input) throws RemoteException {
		serverStub.receiveAction(input);
	}

	private void sendName(String name, int unauthorizedId) throws IOException {
		serverStub.receiveName(name, unauthorizedId);
	}

	private void sendColor(PlayerColor color) throws RemoteException {
		serverStub.receiveColor(color);
	}

	public void setServerStub(RMIViewRemote serverStub) {
		this.serverStub = serverStub;
	}



	@Override
	public void update(Observable o, Object arg) {
		try {
			if(arg instanceof Integer) {
				sendAction((Integer) arg);
			}
			else if (arg instanceof EventNewName) {
				EventNewName event = (EventNewName) arg;
				sendName(event.getName(), event.getUnauthorizedId());
			}
			else if(arg instanceof PlayerColor) {
				sendColor((PlayerColor) arg);
			}
		}
		catch (ConnectException e) {
			System.out.println("The server closed!");
			System.exit(1);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
