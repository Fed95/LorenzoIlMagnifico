package it.polimi.ingsw.gc_12.mvc;

import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.client.ClientSender;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventMarketChosen;
import it.polimi.ingsw.gc_12.event.EventTowerChosen;
import it.polimi.ingsw.gc_12.event.EventWorkplaceChosen;
import it.polimi.ingsw.gc_12.java_fx.MainBoard;
import it.polimi.ingsw.gc_12.java_fx.MainBoardController;

import java.util.Observer;
import java.util.Observable;

public class GUIAdapter implements Observer {

	private Action action;

	public GUIAdapter() {}

	public void handleEvent(Event event, MainBoard view) {
		if(event instanceof EventTowerChosen || event instanceof EventMarketChosen || event instanceof EventWorkplaceChosen)
			view.getControllerMainBoard().notifyObservers(action);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Action)
			action = (Action)arg;
	}
}
