package it.polimi.ingsw.gc12.model.player.resource;

/**
 * Extend resource, creates wood
 */
public class Wood extends Resource{
    /**
     * Constructor
     * @param value value of the resource
     */
	public Wood(int value){
		super(ResourceType.WOOD, value);
	}

}
