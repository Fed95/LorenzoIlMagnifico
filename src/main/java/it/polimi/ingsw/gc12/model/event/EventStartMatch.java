package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.view.client.MatchInstance;
import it.polimi.ingsw.gc12.view.client.ClientHandler;
import javafx.application.Platform;

public class EventStartMatch extends Event{
	
	private Match match;
	
	public EventStartMatch(Match match) {
		this.match = match;
	}
	
	public Match getMatch() {
		return match;
	}

	@Override
	public void executeClientSide(ClientHandler client) {
		// Setup the simplified model of the match based on the copy of the real match received
		client.initMatch(match);

		// Add the notification to the section of the GUI
		if(client.getMainBoardController()!= null) {
            Platform.runLater(() ->client.getMainBoardController().getChat().appendText("[SERVER]: Hi "+match.getPlayers().get(client.getColor()).getName()+" welcome to Lorenzo il Magnifico, your color is "+client.getColor() + "\n"));
        }
    }

	@Override
	public String toString() {
		return "Match started!";
	}

	@Override
	public String toStringClient() {
		return "The match has started!";

	}

    @Override
    public String toStringClientGUI() {
        return "The match has started!";
    }
}
