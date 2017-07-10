package it.polimi.ingsw.gc12.model.player.resource;

/**
 * Extends resource, represent the council privilege
 */
public class CouncilPrivilege extends Resource{
    /**
     * Constructor
     * @param value value oof the resource
     */
	public CouncilPrivilege(int value){
		super(ResourceType.COUNCIL_PRIVILEGE, value);
	}
}
