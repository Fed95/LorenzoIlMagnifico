package it.polimi.ingsw.gc_12.server.view;

import it.polimi.ingsw.gc_12.server.controller.Change;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.server.observer.Observable;
import it.polimi.ingsw.gc_12.server.observer.Observer;

public abstract class View extends Observable<Action>
	implements Observer<Change>
	{

}
