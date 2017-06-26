package it.polimi.ingsw.gc_12.client.socket;

import it.polimi.ingsw.gc_12.client.ClientSender;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;


public class ClientOutHandler implements Runnable, ClientSender {

	private ObjectOutputStream socketOut;
	private String name;

	public ClientOutHandler(ObjectOutputStream socketOut, String name, Scanner scanner) {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
