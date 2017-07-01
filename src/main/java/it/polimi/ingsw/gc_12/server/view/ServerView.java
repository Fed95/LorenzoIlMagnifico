package it.polimi.ingsw.gc_12.server.view;

import it.polimi.ingsw.gc_12.Config;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.client.rmi.ClientViewRemote;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.json.loader.LoaderConfig;
import it.polimi.ingsw.gc_12.server.observer.Observable;
import it.polimi.ingsw.gc_12.server.observer.Observer;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public abstract class ServerView extends Observable<Action> implements Observer<Event> {

	protected Timer timer = new Timer();
	public final int TIMEOUT_ACTION;
	protected Match match;
	protected int i;
	protected int incrementalId = 1;


	public ServerView(Match match) {
		this.TIMEOUT_ACTION = new LoaderConfig().get(match).getTimeoutAction();
		this.match = match;
		i = 0;
	}

	protected void setTimeoutAction() {
		timer.cancel();
		timer.purge();
		timer = new Timer();
		timer.schedule(new TimerActionTask(match), TIMEOUT_ACTION);
	}

	class TimerActionTask extends TimerTask {

		private Match match;

		public TimerActionTask(Match match) {
			this.match = match;
		}

		@Override
		public void run() {
			match.excludeCurrentPlayer();
			match.newTurn();
		}
	}

	public void setMatch(Match match) {
		this.match = match;
	}
}
