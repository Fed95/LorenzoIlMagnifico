package it.polimi.ingsw.gc12.model.player.resource;

/**
 * Extends resource, create stone resource
 */
public class Stone extends Resource{
    /**
     * Constructor
     * @param value value of the resource
     */
	public Stone(int value){
		super(ResourceType.STONE, value);
	}
}
