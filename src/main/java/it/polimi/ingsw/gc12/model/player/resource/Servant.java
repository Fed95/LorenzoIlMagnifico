package it.polimi.ingsw.gc12.model.player.resource;

/**
 * Extends resource, build servant resource
 */
public class Servant extends Resource{
    /**
     * Constructor
     * @param value value of the resource
     */
	public Servant(int value){
		super(ResourceType.SERVANT, value);
	}
}
