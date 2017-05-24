package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.exceptions.FamilyMemberAlreadyPresentException;
import it.polimi.ingsw.gc_12.exceptions.InvalidParametersException;
import it.polimi.ingsw.gc_12.exceptions.OccupiableAlreadyTakenException;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;

import java.util.Set;

public interface ActionInterface {
	
	public void start() throws RequiredValueNotSatisfiedException, FamilyMemberAlreadyPresentException, InvalidParametersException, OccupiableAlreadyTakenException;
	
	public Set<Object> getAttributes();

	public boolean equals(Object obj);

}
