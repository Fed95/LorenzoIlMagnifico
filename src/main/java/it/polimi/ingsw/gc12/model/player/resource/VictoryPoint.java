package it.polimi.ingsw.gc12.model.player.resource;

/**
 * Extend resource, buil victory point resource, is needed when som effect gave you victory points, or cost you
 */
public class VictoryPoint extends Resource{
    /**
     * Constructor
     * @param value value of the resource
     */
	public VictoryPoint(int value){
		super(ResourceType.VICTORY_POINT, value);
	}
}
