package it.polimi.ingsw.gc_12.server;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.MatchRemote;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.client.rmi.ClientViewRemote;
import it.polimi.ingsw.gc_12.server.controller.Controller;
import it.polimi.ingsw.gc_12.server.view.RMIView;
import it.polimi.ingsw.gc_12.server.view.RMIViewRemote;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Server {

	//Main class from the server side
	private final static int PORT = 29999;

	private final String NAME = "lorenzo";
	private final String MODEL_NAME = "match";
	private final static int RMI_PORT = 52365;

	private Match match;
	private Controller controller;
	private Registry registry;
	private Stack<RMIView> views;

	public Server() {
		this.match = new Match();
		this.controller = new Controller(match);
		views = new Stack<>();

	}

	private void startRMI() throws RemoteException, AlreadyBoundException{

		//create the registry to publish remote objects
		registry = LocateRegistry.createRegistry(RMI_PORT);
		System.out.println("Constructing the RMI registry");

		// Create the RMI View, that will be shared with the client
		RMIView rmiView = new RMIView(this);
		views.push(rmiView);

		//controller observes this view
		rmiView.registerObserver(this.controller);

		//this view observes the model
		this.match.registerObserver(rmiView);

		// publish the view in the registry as a remote object
		RMIViewRemote viewRemote = (RMIViewRemote) UnicastRemoteObject.exportObject(rmiView, 0);
		
		System.out.println("Binding the server implementation to the registry");
		registry.bind(NAME, viewRemote);
	}

	public void startMatch() throws RemoteException, AlreadyBoundException {
		RMIView rmiView = views.peek();
		List<Player> players = new ArrayList<>();
		for(ClientViewRemote client : rmiView.getClients()) {
			Player player = new Player(client.getName(), new HashMap<>());
			players.add(player);
		}
		match.init(players);
		MatchRemote matchRemote = (MatchRemote) UnicastRemoteObject.exportObject(match, 0);
		registry.rebind(MODEL_NAME, matchRemote);
		match.start();
		
	}

	public void newMatch() throws RemoteException {
		// publish the view in the registry as a remote object
		/*RMIViewRemote viewRemote=(RMIViewRemote) UnicastRemoteObject.
				exportObject(rmiView, 0);*/
		match = new Match();
		controller = new Controller(match);
		RMIView rmiView = new RMIView(this);
		views.push(rmiView);

		// publish the view in the registry as a remote object
		RMIViewRemote viewRemote=(RMIViewRemote) UnicastRemoteObject.
				exportObject(rmiView, 0);

		registry.rebind(NAME, viewRemote);
		//controller observes this view
		rmiView.registerObserver(this.controller);

		//this view observes the model
		this.match.registerObserver(rmiView);
	}
	
	/*private void startSocket() throws IOException {

		// creates the thread pool to handle clients
		ExecutorService executor = Executors.newCachedThreadPool();

		//creats the socket
		ServerSocket serverSocket = new ServerSocket(PORT);

		System.out.println("SERVER SOCKET READY ON PORT" + PORT);

		while (true) {
			//Waits for a new client to connect
			Socket socket = serverSocket.accept();

			// creates the view (server side) associated with the new client
			ServerSocketView view = new ServerSocketView(socket, this.match);

			// the view observes the model
			this.match.registerObserver(view);

			// the controller observes the view
			view.registerObserver(this.controller);

			// a new thread handle the connection with the view
			executor.submit(view);
		}
	}*/

	public static void main(String[] args) throws IOException, AlreadyBoundException {
		Server server = new Server();
		System.out.println("START RMI");
		server.startRMI();
		/*System.out.println("START SOCKET");
		server.startSocket();*/
	}
}