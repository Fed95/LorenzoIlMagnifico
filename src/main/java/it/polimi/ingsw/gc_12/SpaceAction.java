package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.resource.Resource;

public abstract class SpaceAction implements Occupiable{
	public final int REQUIRED_VALUE = 1;
	private ActionType actionType;
	private List<Resource> requirements = new ArrayList<>();
	
	public SpaceAction(ActionType actionType, List<Resource> requirements) {
		this.actionType = actionType;
		this.requirements = requirements;
	}

	@Override
	public List<Resource> getRequirements() {
		return this.requirements;
	}
}
