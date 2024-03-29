package it.polimi.ingsw.gc12.model.player.resource;

/**
 * Method for creating resource from json file
 */
public class ResourceBuilder {

	private ResourceBuilder() {}

	public static Resource create(ResourceType resourceType, int value) throws IllegalArgumentException{
		Resource resource;

		switch(resourceType) {
			case FAITH_POINT:
				return new FaithPoint(value);
			case MILITARY_POINT:
				return new MilitaryPoint(value);
			case MONEY:
				return new Money(value);
			case SERVANT:
				return new Servant(value);
			case STONE:
				return new Stone(value);
			case VICTORY_POINT:
				return new VictoryPoint(value);
			case WOOD:
				return new Wood(value);
			case COUNCIL_PRIVILEGE:
				return new CouncilPrivilege(value);
			default:
				throw new IllegalArgumentException("The ResourceType does not exist.");

		}
	}
}
