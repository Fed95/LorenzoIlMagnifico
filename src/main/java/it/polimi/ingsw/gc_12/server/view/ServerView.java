package it.polimi.ingsw.gc_12.server.view;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.server.observer.Observable;
import it.polimi.ingsw.gc_12.server.observer.Observer;

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
