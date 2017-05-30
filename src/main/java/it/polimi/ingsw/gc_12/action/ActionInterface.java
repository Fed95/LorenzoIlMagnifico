package it.polimi.ingsw.gc_12.action;

import java.util.Set;

public interface ActionInterface {
	
	public void start() throws RuntimeException;
	
	public Set<Object> getAttributes();

	public boolean equals(Object obj);

}
