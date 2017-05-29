package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.exceptions.*;

import java.util.Set;

public interface ActionInterface {
	
	public void start() throws CannotPlaceFamilyMemberException, CannotPlaceCardException, NotEnoughResourcesException;
	
	public Set<Object> getAttributes();

	public boolean equals(Object obj);

}
