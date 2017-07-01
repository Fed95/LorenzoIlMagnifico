package it.polimi.ingsw.gc_12.event;


import it.polimi.ingsw.gc_12.client.ClientFactory;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.java_fx.MainBoard;
import it.polimi.ingsw.gc_12.mvc.ClientView;

import java.io.IOException;
import java.util.List;

public class EventMatchInitialized extends Event{

	@Override
	public void executeClientSide(ClientHandler client) {}

	@Override
	public String toString() {
		return null;
	}
}
