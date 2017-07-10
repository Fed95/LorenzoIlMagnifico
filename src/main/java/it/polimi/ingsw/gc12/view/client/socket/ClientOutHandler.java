package it.polimi.ingsw.gc12.view.client.socket;

import it.polimi.ingsw.gc12.view.client.ClientSender;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Observable;

/**
 * Class responsible of sending the chosen message to the ServerSocketView through sockets.
 */

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
		sendObject(arg);
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
