package it.polimi.ingsw.gc12.view.server;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.action.Action;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.misc.observer.Observable;
import it.polimi.ingsw.gc12.misc.observer.Observer;

public abstract class ServerView extends Observable<Action> implements Observer<Event> {
	protected Match match;
	protected int i;
	protected int incrementalId = 1;


	public ServerView(Match match) {
		this.match = match;
		i = 0;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
}
