package it.polimi.ingsw.gc12.model.player.resource;

/**
 * Extends resource, create resource money
 */
public class Money extends Resource{
    /**
     * Constructor
     * @param value value of the resource
     */
	public Money(int value){
		super(ResourceType.MONEY, value);
	}
}
