package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;

public abstract class Action {

	public abstract void start(Match match) throws RuntimeException, RequiredValueNotSatisfiedException;
}
