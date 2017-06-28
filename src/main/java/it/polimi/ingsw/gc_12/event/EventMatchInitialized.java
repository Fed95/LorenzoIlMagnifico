package it.polimi.ingsw.gc_12.event;


import it.polimi.ingsw.gc_12.client.ClientFactory;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.java_fx.MainBoard;
import javafx.application.Platform;

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

		if(client.getView() instanceof MainBoard) {
			Platform.runLater(() -> {
				MainBoard mainBoard = (MainBoard) client.getView();
				try {
					mainBoard.changeScene("FXMLMainBoard", 1980, 1080, true, "Lorenzo il magnifico");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

		}

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
