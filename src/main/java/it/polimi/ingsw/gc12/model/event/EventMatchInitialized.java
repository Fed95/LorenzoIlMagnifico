package it.polimi.ingsw.gc12.model.event;


import it.polimi.ingsw.gc12.view.client.ClientFactory;
import it.polimi.ingsw.gc12.view.client.ClientHandler;
import it.polimi.ingsw.gc12.view.client.gui.MainBoard;
import javafx.application.Platform;

import java.io.IOException;

public class EventMatchInitialized extends Event implements EventView{

	@Override
	public void executeClientSide(ClientHandler client) {}

	@Override
	public String toString() {
		return "";
	}

	@Override
	public void executeViewSide(MainBoard view) {
		// If the client receives the event when has already finished loading,
		// send to the server that the client is ready to play
		if(view.isReady())
			view.getControllerMainBoard().notifyObservers(0);
		else {
			// The view has not loaded completely, the notification of the client as ready will be send
			// after the view has finished loading
			ClientFactory.getClientHandler().setStarted(true);
		}


		// Load the main board of the GUI
		Platform.runLater(() -> {
			try {
				view.changeScene("FXMLMainBoard", 1980, 1080, true, "Lorenzo il magnifico");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}
