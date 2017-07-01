package it.polimi.ingsw.gc_12.client;

import it.polimi.ingsw.gc_12.client.rmi.ClientRMI;

import java.io.IOException;
import java.util.Observer;

public interface ClientSender extends Observer{

	void sendAction(int input) throws IOException;
}
