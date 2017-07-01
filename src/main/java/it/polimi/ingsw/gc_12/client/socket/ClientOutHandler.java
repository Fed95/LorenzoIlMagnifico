package it.polimi.ingsw.gc_12.client.socket;

import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.ClientSender;
import it.polimi.ingsw.gc_12.client.NewName;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Observable;
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
		sendObject(name);
	}

	public String getName() {
		return name;
	}

	@Override
	public void sendAction(int input){
		sendObject(input);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Integer)
			sendAction((Integer) arg);
	}

	private void sendObject(Object object) {
		try {
			socketOut.writeObject(object);
			socketOut.flush();
			socketOut.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
