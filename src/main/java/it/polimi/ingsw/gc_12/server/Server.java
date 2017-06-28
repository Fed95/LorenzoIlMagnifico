package it.polimi.ingsw.gc_12.server;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.PlayerColor;
import it.polimi.ingsw.gc_12.client.rmi.ClientViewRemote;
import it.polimi.ingsw.gc_12.server.controller.Controller;
import it.polimi.ingsw.gc_12.server.view.RMIViewRemote;
import it.polimi.ingsw.gc_12.server.view.ServerRMIView;
import it.polimi.ingsw.gc_12.server.view.ServerSocketView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	//Main class from the server side
	private final static int PORT = 29999;

	private final String NAME = "lorenzo";
	private final String MODEL_NAME = "match";
	private final static int RMI_PORT = 52365;

	private Match match;
	private Controller controller;
	private Registry registry;
	private Stack<ServerRMIView> views;
	private List<Player> playersSocket;
	public int numOfClients;
	private LinkedList<PlayerColor> playerColors = new LinkedList<>();
	private List<Player> waitingPlayers = new ArrayList<>();

	public Server() {
		this.match = new Match();
		this.controller = new Controller(match);
		this.views = new Stack<>();
		this.playersSocket = new ArrayList<>();
		this.playerColors.addAll(Arrays.asList(PlayerColor.values()));
		this.numOfClients = 0;
	}

	private void startRMI() throws RemoteException, AlreadyBoundException{

		//create the registry to publish remote objects
		registry = LocateRegistry.createRegistry(RMI_PORT);
		System.out.println("Constructing the RMI registry");

		// Create the RMI View, that will be shared with the client
		ServerRMIView serverRmiView = new ServerRMIView(this, match, playerColors);
		views.push(serverRmiView);

		//controller observes this view
		serverRmiView.registerObserver(this.controller);

		//this view observes the model
		this.match.registerObserver(serverRmiView);

		// publish the view in the registry as a remote object
		RMIViewRemote viewRemote = (RMIViewRemote) UnicastRemoteObject.exportObject(serverRmiView, 0);
		
		System.out.println("Binding the server implementation to the registry");
		registry.bind(NAME, viewRemote);
	}

	public synchronized void startMatch() throws AlreadyBoundException, CloneNotSupportedException, RemoteException {
		Map<PlayerColor, Player> players = new HashMap<>();
		Iterator<Player> itr = waitingPlayers.iterator();
		int i = 1;
		while(itr.hasNext()) {

			Player player = itr.next();
			players.put(player.getColor(), player);
			itr.remove();

			//Make sure that it doesn't create matches with more than the allowed players
			if(i == 4) //TODO put it in a variable
				break;
			i++;
		}
		match.init(players);

	}

	public void newMatch() throws RemoteException {
		// publish the view in the registry as a remote object
		/*RMIViewRemote viewRemote=(RMIViewRemote) UnicastRemoteObject.
				exportObject(serverRmiView, 0);*/
		numOfClients = 0;
		playersSocket = new ArrayList<>();
		match = new Match();
		controller = new Controller(match);
		playerColors = new LinkedList<>();
		playerColors.addAll(Arrays.asList(PlayerColor.values()));
		ServerRMIView serverRmiView = new ServerRMIView(this, match, playerColors);
		views.push(serverRmiView);

		// publish the view in the registry as a remote object
		RMIViewRemote viewRemote=(RMIViewRemote) UnicastRemoteObject.
				exportObject(serverRmiView, 0);

		registry.rebind(NAME, viewRemote);
		//controller observes this view
		serverRmiView.registerObserver(this.controller);

		//this view observes the model
		this.match.registerObserver(serverRmiView);
	}
	
	private void startSocket() throws IOException, AlreadyBoundException, CloneNotSupportedException {

		// creates the thread pool to handle clients
		ExecutorService executor = Executors.newCachedThreadPool();

		//creats the socket
		ServerSocket serverSocket = new ServerSocket(PORT);

		System.out.println("SERVER SOCKET READY ON PORT " + PORT);

		while (true) {
			System.out.println("while true");
			//Waits for a new client to connect
			Socket socket = serverSocket.accept();

			// creates the view (server side) associated with the new client
			ServerSocketView view = new ServerSocketView(socket, this.match, this, playerColors);

			// the view observes the model
			this.match.registerObserver(view);

			// the controller observes the view
			view.registerObserver(this.controller);

			// a new thread handle the connection with the view
			executor.submit(view);

		}
	}

	/*public void increaseClientsNum() throws CloneNotSupportedException, IOException, AlreadyBoundException {
		numOfClients++;
		System.out.println(numOfClients);
		if(numOfClients == 2) {
			startMatch();
			newMatch();
		}
	}*/

	public void addPlayer(Player player) throws CloneNotSupportedException, AlreadyBoundException, RemoteException {
		System.out.println("Adding player " + player.getName());
		waitingPlayers.add(player);
		System.out.println(numOfClients);
		if(waitingPlayers.size() == 2) {
			startMatch();
			newMatch();
		}
	}

	public static void main(String[] args) throws IOException, AlreadyBoundException, CloneNotSupportedException {
		Server server = new Server();
		System.out.println("START RMI");
		server.startRMI();
		System.out.println("START SOCKET");
		server.startSocket();
	}
}
