package it.polimi.ingsw.gc_12.event;


import it.polimi.ingsw.gc_12.client.ClientFactory;
import it.polimi.ingsw.gc_12.client.ClientHandler;

import java.io.IOException;
import java.util.List;

public class EventMatchInitialized extends Event{

	@Override
	public void executeClientSide(ClientHandler client) {
		if(client.getView().isReady()) {
			try {
				ClientFactory.getClientSender().sendAction(0);


			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		else
			client.setStarted(true);

	}

	@Override
	public List<Object> getAttributes() {
		return null;
	}

	@Override
	public String toString() {
		return null;
	}
}
