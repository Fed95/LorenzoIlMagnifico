package it.polimi.ingsw.gc12.view.client;

import java.io.IOException;
import java.util.Observer;

public interface ClientSender extends Observer{

	void sendAction(int input) throws IOException;
}
