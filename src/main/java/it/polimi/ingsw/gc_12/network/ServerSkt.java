package it.polimi.ingsw.gc_12.network;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSkt {
	private int port;
	private MatchHandler matchHandler;

	public ServerSkt(int port, MatchHandler matchHandler) throws RemoteException {
		this.port = port;
		this.matchHandler = matchHandler;
	}

	public ServerSkt(MatchHandler matchHandler) throws RemoteException {
		this(ServerHandler.PORT_DEFAULT, matchHandler);
	}

	public void startServer() {
		ExecutorService executor = Executors.newCachedThreadPool();
		ServerSocket serverSocket;
		try {
			// Open TCP port
			serverSocket = new ServerSocket(port);
		} catch(IOException e) {
			System.err.println(e.getMessage());
			return;
		}
		System.out.println("ServerSocket ready");

		while(true) {
			try {
				// Waiting for new connections
				Socket socket = serverSocket.accept();
				// Start a free thread from the pool
				executor.submit(new ServerSktHandler(socket, matchHandler));
			} catch (IOException e) {
				// Enter here if serverSocket closes
				break;
			}
		}

		try {
			serverSocket.close();
		} catch(IOException e) {
			System.err.println(e.getMessage());
			return;
		}

		executor.shutdown();
	}
}
