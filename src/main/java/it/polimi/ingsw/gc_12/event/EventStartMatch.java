package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.MatchInstance;
import it.polimi.ingsw.gc_12.MatchInstanceCLI;
import it.polimi.ingsw.gc_12.MatchInstanceGUI;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.mvc.View;
import it.polimi.ingsw.gc_12.mvc.ViewCLI;

import java.util.ArrayList;
import java.util.List;

public class EventStartMatch extends Event{
	
	private Match match;
	
	public EventStartMatch(Match match) {
		this.match = match;
	}
	
	public Match getMatch() {
		return match;
	}

	@Override
	public List<Object> getAttributes() {
		return new ArrayList<>();
	}

	@Override
	public void executeClientSide(ClientHandler client) {
		MatchInstance matchInstance = createMatchInstance(client.getView());
		matchInstance.init(match);
		client.setMatch(matchInstance);

		if(client.getMainBoardController()!=null) {
            client.getMainBoardController().getChat().appendText("[SERVER]: Your Color is " + client.getColor() + "\n");
        }
    }

	private MatchInstance createMatchInstance(View view) {
		if(view instanceof ViewCLI)
			return MatchInstanceCLI.instance();
		else
			return MatchInstanceGUI.instance();
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
