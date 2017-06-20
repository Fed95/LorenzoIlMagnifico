package it.polimi.ingsw.gc_12.mvc;

import it.polimi.ingsw.gc_12.client.ClientSender;

import java.io.IOException;

public class GUIAdapter {
	private ClientSender client;


	public GUIAdapter(ClientSender client) {
		this.client = client;
	}

	public void sendAction(int input) throws IOException {
		client.sendAction(input);
	}
}
