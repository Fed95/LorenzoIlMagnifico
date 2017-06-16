package it.polimi.ingsw.gc_12.client.socket;

import it.polimi.ingsw.gc_12.client.rmi.ClientRMIView;
import it.polimi.ingsw.gc_12.mvc.View;
import it.polimi.ingsw.gc_12.mvc.ViewCLI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientSocket {
// Main class of the client implemented with sockets

	private final static int PORT = 29999;
	private final static String IP = "127.0.0.1";
	private View view;

	public void startClient() throws UnknownHostException, IOException {

		Socket socket = new Socket(IP, PORT);

		System.out.println("Connection created");

		ExecutorService executor = Executors.newFixedThreadPool(2);

		Scanner stdIn = new Scanner(System.in);
		System.out.println("Choose a name");
		String name = "";
		while (true) {
			name = stdIn.nextLine();
			if(!"\n".equals(name) && !"".equals(name)) {

				System.out.println("Open input and output streams");
				//Creates one thread to send messages to the server
				ClientOutHandler clientOut = new ClientOutHandler(new ObjectOutputStream(socket.getOutputStream()), name, stdIn);
				executor.submit(clientOut);

				ClientInHandler clientIn = new ClientInHandler(new ObjectInputStream(socket.getInputStream()), clientOut);
				//Creates one thread to receive messages from the server
				executor.submit(clientIn);
				System.out.println("Creation of the view..");

				view = new ViewCLI(clientOut, clientIn);
				view.start();
				break;
			}
			else {
				System.out.println("Choose a name");
				//stdIn.next();
			}
		}



	}
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		ClientSocket client=new ClientSocket();
		client.startClient();
	}
}
