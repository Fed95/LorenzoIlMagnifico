package it.polimi.ingsw.gc_12.mvc;

import it.polimi.ingsw.gc_12.client.ClientSender;

import java.io.IOException;

/**
 * Created by marco on 26/06/2017.
 */
public class Adapter {
	private ClientSender client;

	public Adapter(ClientSender client) {
		this.client = client;
	}

	public void sendAction(int input) throws IOException {
		client.sendAction(input);
	}
}
