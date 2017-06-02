package it.polimi.ingsw.gc_12.network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerRMI extends UnicastRemoteObject implements ServerInterface {
	private int port;
	private MatchHandler matchHandler;

	public ServerRMI(int port, MatchHandler matchHandler) throws RemoteException {
		this.port = port;
		this.matchHandler = matchHandler;
	}

	public ServerRMI(MatchHandler matchHandler) throws RemoteException {
		this(ServerHandler.PORT_DEFAULT, matchHandler);
	}

	public void startServer() throws RemoteException {
		Registry reg = LocateRegistry.getRegistry();
		reg.rebind("server", this);

		System.out.println("ServerRMI up and running...");
	}

	@Override
	public void connect(ClientInterface client) throws RemoteException {
		Player player = client.getPlayer();
		matchHandler.addPlayer(player);
	}

	@Override
	public void disconnect(ClientInterface client) throws RemoteException {

	}

	@Override
	public void send(Message m) throws RemoteException {

	}
}
