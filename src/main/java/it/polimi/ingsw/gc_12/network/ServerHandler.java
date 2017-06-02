package it.polimi.ingsw.gc_12.network;


import java.rmi.RemoteException;

public class ServerHandler {

	public static final int PORT_DEFAULT = 1337;

	public static void main(String[] args) throws RemoteException {
		MatchHandler matchHandler = new MatchHandler();
		ServerRMI serverRMI = new ServerRMI(matchHandler);
		serverRMI.startServer();
		ServerSkt serverSkt = new ServerSkt(matchHandler);
		serverSkt.startServer();
	}
}
