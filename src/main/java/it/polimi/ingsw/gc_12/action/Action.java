package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;

public abstract class Action  implements Serializable {

	public abstract void start(Match match) throws RuntimeException, RequiredValueNotSatisfiedException, IOException, RemoteException;

}
