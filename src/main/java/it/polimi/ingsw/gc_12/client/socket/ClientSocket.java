package it.polimi.ingsw.gc_12.client.socket;

import it.polimi.ingsw.gc_12.client.ClientFactory;
import it.polimi.ingsw.gc_12.mvc.ClientView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientSocket {
// Main class of the client implemented with sockets

	private final static int PORT = 29999;
	private final static String IP = "127.0.0.1";

	public void startClient(ClientView view, String name) throws IOException {

		Socket socket = new Socket(IP, PORT);

		System.out.println("Connection created");

		ExecutorService executor = Executors.newFixedThreadPool(2);

		System.out.println("Open input and output streams");
		//Creates one thread to send messages to the server
		ClientOutHandler clientOut = new ClientOutHandler(new ObjectOutputStream(socket.getOutputStream()), name);
		executor.submit(clientOut);
		ClientFactory.setClientSender(clientOut);

		ClientInHandler clientIn = new ClientInHandler(new ObjectInputStream(socket.getInputStream()), clientOut, view);
		//Creates one thread to receive messages from the server
		executor.submit(clientIn);
		ClientFactory.setClientHandler(clientIn);
		System.out.println("Creation of the view..");
		//view.start();
	}
}
