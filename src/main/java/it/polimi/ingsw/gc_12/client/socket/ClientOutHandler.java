package it.polimi.ingsw.gc_12.client.socket;

import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.ClientSender;
import it.polimi.ingsw.gc_12.client.NewName;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;


public class ClientOutHandler implements Runnable, ClientSender {

	private ObjectOutputStream socketOut;
	private String name;

	public ClientOutHandler(ObjectOutputStream socketOut, String name) {
		this.socketOut = socketOut;
		this.name = name;
	}

	@Override
	public void run() {

		// Handles output messages, from the client to the server
		try {
			socketOut.writeObject(name);
			socketOut.flush();
			socketOut.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getName() {
		return name;
	}

	@Override
	public void sendAction(int input){

		try {
			socketOut.writeObject(input);
			socketOut.flush();
			socketOut.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendName(String name, int unauthorizedId) throws IOException {
		try {
			socketOut.writeObject(new NewName(unauthorizedId, name));
			socketOut.flush();
			socketOut.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
