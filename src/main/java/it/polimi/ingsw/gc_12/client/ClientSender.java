package it.polimi.ingsw.gc_12.client;

import it.polimi.ingsw.gc_12.action.Action;

import java.io.IOException;
public interface ClientSender {

	void sendAction(int input) throws IOException;
}
