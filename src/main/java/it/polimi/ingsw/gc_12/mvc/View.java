package it.polimi.ingsw.gc_12.mvc;

import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.ClientSender;

import java.io.IOException;

public interface View {

	void start() throws IOException;
	ClientSender getClientSender();
	void setClientSender(ClientSender clientSender);
	void setClientHandler(ClientHandler clientHandler);
}
