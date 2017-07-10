package it.polimi.ingsw.gc12;

import it.polimi.ingsw.gc12.model.match.Config;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.view.client.rmi.ClientViewRemote;
import it.polimi.ingsw.gc12.model.event.EventMatchInitialized;
import it.polimi.ingsw.gc12.misc.json.loader.LoaderConfig;
import it.polimi.ingsw.gc12.controller.Controller;
import it.polimi.ingsw.gc12.view.server.RMIViewRemote;
import it.polimi.ingsw.gc12.view.server.ServerRMIView;
import it.polimi.ingsw.gc12.view.server.ServerSocketView;

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

	private final int TIMEOUT_START;
	private final int MIN_PLAYERS;
	private final int MAX_PLAYERS;
	private Timer timer;

	private Match match;
	private Controller controller;
	private Registry registry;
	private Map<Player, Match> playingPlayers = new HashMap<>();
	private LinkedList<PlayerColor> playerColors = new LinkedList<>();
	private List<Player> waitingPlayers = new ArrayList<>();
	private HashMap<Match, Controller> controllers = new HashMap<>();

	public Server() {
		this.match = new Match();
		this.controller = new Controller(match);
		controllers.put(match, controller);
		this.playerColors.addAll(Arrays.asList(PlayerColor.values()));
		Config config = new LoaderConfig().get(null);
		MIN_PLAYERS = config.getMinPlayers();
		MAX_PLAYERS = config.getMaxPlayers();
		TIMEOUT_START = config.getTimeoutStart();
		this.timer = new Timer();
	}

	private void startRMI() throws RemoteException, AlreadyBoundException {

		//create the registry to publish remote objects
		registry = LocateRegistry.createRegistry(RMI_PORT);
		System.out.println("Constructing the RMI registry");

		// Create the RMI ServerView, that will be shared with the client
		ServerRMIView serverRmiView = new ServerRMIView(this, match, playerColors);

		//controller observes this view
		serverRmiView.registerObserver(this.controller);

		//this view observes the model
		this.match.registerObserver(serverRmiView);

		// publish the view in the registry as a remote object
		RMIViewRemote viewRemote = (RMIViewRemote) UnicastRemoteObject.exportObject(serverRmiView, 0);

		System.out.println("Binding the server implementation to the registry");
		registry.bind(NAME, viewRemote);
	}

	/**
	 * Takes the MAX_PLAYERS players waiting for the match to start, and initializes the match with those players.
	 *
	 * @throws AlreadyBoundException
	 * @throws CloneNotSupportedException
	 * @throws RemoteException
	 */
	private synchronized void startMatch() throws AlreadyBoundException, CloneNotSupportedException, RemoteException {
		Map<PlayerColor, Player> players = new HashMap<>();
		Iterator<Player> itr = waitingPlayers.iterator();
		int i = 1;
		while (itr.hasNext()) {

			Player player = itr.next();
			players.put(player.getColor(), player);
			playingPlayers.put(player, match);
			itr.remove();

			//Make sure that it doesn't create matches with more than the allowed players
			if (i == MAX_PLAYERS)
				break;
			i++;
		}
		match.init(players);
		match.notifyObserver(new EventMatchInitialized());
		timer.purge();
		timer.cancel();
	}

	/**
	 * Creates a new match with the related controller and ServerRMIView.
	 * The registry will have this new ServerRMIView binded, so, every new player using RMI,
	 * will connect to the view of the new match.
	 * @throws RemoteException
	 */
	private void newMatch() throws RemoteException {
		// publish the view in the registry as a remote object
		/*RMIViewRemote viewRemote=(RMIViewRemote) UnicastRemoteObject.
				exportObject(serverRmiView, 0);*/
		match = new Match();
		controller = new Controller(match);
		controllers.put(match, controller);
		playerColors = new LinkedList<>();
		playerColors.addAll(Arrays.asList(PlayerColor.values()));
		ServerRMIView serverRmiView = new ServerRMIView(this, match, playerColors);

		// publish the view in the registry as a remote object
		RMIViewRemote viewRemote = (RMIViewRemote) UnicastRemoteObject.
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

	/**
	 * Called by the ServerSocketView and ServerRMIView to register a new connected player.
	 * If the number of players is equivalent to MIN_PLAYERS, it starts a timer for the match to start.
	 * The match can start before the timer if the number of player reaches MAX_PLAYERS.
	 * @param player
	 * @throws CloneNotSupportedException
	 * @throws AlreadyBoundException
	 * @throws RemoteException
	 */

	public void addPlayer(Player player) throws CloneNotSupportedException, AlreadyBoundException, RemoteException {
		System.out.println("Adding player " + player.getName());
		waitingPlayers.add(player);
		if (waitingPlayers.size() == MIN_PLAYERS)  {
			timer = new Timer();
			timer.schedule(new TimerMatchTask(), TIMEOUT_START);  // TODO: set a proper number in config file before the deadline
		}
		else if (waitingPlayers.size() == MAX_PLAYERS) {
			startMatch();
			newMatch();
		}
	}

	/**
	 * Checks among the players in a match o among the ones that are waiting for the match to start,
	 * if there is already a player with that name.
	 * It's used to keep the names of the current players unique, so that in case of disconnection,
	 * they can reconnect login in with the same name.
	 * @param name
	 * @return
	 */
	public boolean isNameTaken(String name) {
		for (Player player : playingPlayers.keySet()) {
			if (name.toLowerCase().equals(player.getName().toLowerCase()))
				return true;
		}
		for (Player player : waitingPlayers) {
			if (name.toLowerCase().equals(player.getName().toLowerCase()))
				return true;
		}
		return false;
	}

	/**
	 * Reconnects the player if there is a started match containing that player.
	 * @param player Player using RMI to reconnect
	 * @param client clientRMI to reconnect
	 * @return
	 */
	public boolean tryReconnection(Player player, ClientViewRemote client) {
		Match match = playingPlayers.get(player);
		if(match != null && match.getPlayer(player.getName()).isDisconnected()) {
			match.setReconnectedPlayer(player, client);
			return true;
		}
		return false;
	}

	/**
	 * Reconnects the player if there is a started match containing that player.
	 * The view is already observed by the controller of a not started match.
	 * Hence, it has to unregister this controller to register the controller of the match to reconnect.
	 * @param player Player using socket to reconnect
	 * @param view SocketView to reconnect
	 * @return
	 */
	public boolean tryReconnection(Player player, ServerSocketView view) {
		if(!playingPlayers.containsKey(player))
			return false;
		Match match = playingPlayers.get(player);
		match.registerObserver(view);
		view.unregisterObserver(Controller.class);
		view.registerObserver(controllers.get(match));

		if(match.getPlayer(player.getName()).isDisconnected()) {
			view.setMatch(match);
			match.setReconnectedPlayer(player);
			return true;
		}
		return false;
	}

	/**
	 * Removes the match from the controllers Map and removes the match players from the list of playing players.
	 * @param match Match to end
	 */
	public void endMatch(Match match) {
		if(controllers.containsKey(match))
			controllers.remove(match);
		playingPlayers.entrySet().removeIf(entry -> entry.getValue().equals(match));
	}

	public static void main(String[] args) throws IOException, AlreadyBoundException, CloneNotSupportedException {
		Server server = new Server();
		System.out.println("START RMI");
		server.startRMI();
		System.out.println("START SOCKET");
		server.startSocket();
	}

	class TimerMatchTask extends TimerTask {

		@Override
		public void run() {
			try {
				startMatch();
				newMatch();
			} catch (AlreadyBoundException | CloneNotSupportedException | RemoteException e) {
				e.printStackTrace();
			}
		}
	}
}
