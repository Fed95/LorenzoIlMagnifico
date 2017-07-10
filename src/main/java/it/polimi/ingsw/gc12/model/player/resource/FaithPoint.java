package it.polimi.ingsw.gc12.model.player.resource;

/**
 * Extends resource, represent the faith point, is needed when a effects gave you some faith points
 */
public class FaithPoint extends Resource{
    /**
     * Constructor
     * @param value value of the resource
     */
	public FaithPoint(int value){
		super(ResourceType.FAITH_POINT, value);
	}
}
