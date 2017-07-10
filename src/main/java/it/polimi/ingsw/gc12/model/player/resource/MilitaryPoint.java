package it.polimi.ingsw.gc12.model.player.resource;

/**
 * Extends resource, represent the faith point, is needed when a effects gave you some faith points
 */
public class MilitaryPoint extends Resource{
    /**
     * Constructor
     * @param value value of resource
     */
	public MilitaryPoint(int value){
		super(ResourceType.MILITARY_POINT, value);
	}
}
