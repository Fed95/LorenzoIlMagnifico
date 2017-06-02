package it.polimi.ingsw.gc_12.network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Client extends UnicastRemoteObject implements ClientInterface {
	private String ip;
	private int port;
	private Scanner stdin;
	private static Player player;

	public Client(String ip, int port) throws RemoteException{
		this.ip = ip;
		this.port = port;
		this.stdin = new Scanner(System.in);
		player = createPlayer();
	}

	public static void main(String[] args) throws RemoteException, NotBoundException {


		Client client = new Client("127.0.0.1", 1337);

		System.out.println("Write the technology (Socket/RMI)");
		String tech = client.stdin.nextLine();
		if(tech.equals("Socket")) {
			try {
				client.startClientSocket();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (tech.equals("RMI")) {
			client.startClientRMI();
		}
	}

	public void startClientSocket() throws IOException {
		Socket socket = new Socket(ip, port);
		System.out.println("Connection established");

		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(player);

		Scanner in = new Scanner(socket.getInputStream());
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		try {
			while(true) {
				String command = stdin.next();
				Integer parameter = stdin.nextInt();

				Message message = new Message(command, parameter);
				oos.writeObject(message);
			}
		} catch (NoSuchElementException e) {
			System.out.println("Connection closed");
		} finally {
			stdin.close();
			in.close();
			out.close();
			socket.close();
		}

		oos.close();
		socket.close();
	}

	public Player createPlayer() {
		System.out.println("Insert the name of the first player");
		String name = stdin.nextLine();
		/** TODO: handling if name is already taken **/
		return new Player(name);
	}
	
	public void startClientRMI() throws RemoteException, NotBoundException {
		Registry reg = LocateRegistry.getRegistry();
        ServerInterface server = (ServerInterface) reg.lookup("server");

        server.connect(this);
	}

	public Player getPlayer() throws RemoteException {
		return player;
	}
	
	

}
