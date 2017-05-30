package it.polimi.ingsw.gc_12.occupiables;

import java.util.List;

import it.polimi.ingsw.gc_12.WorkType;
import it.polimi.ingsw.gc_12.effect.Effect;

public abstract class SpaceWork extends Occupiable {
	
	protected WorkType workType;

	public SpaceWork(WorkType workType, int requiredValue, List<Effect> effects){
		super(requiredValue, effects);
		this.workType = workType;
	}

	public SpaceWork(WorkType workType, int requiredValue, SpaceWorkZone spaceWorkZone, List<Effect> effects){
		this(workType, requiredValue, effects);
		spaceWorkZone.addSpaceWork(this);
	}

	public boolean isOccupied() {
		if(occupiers.isEmpty())
			return false;
		return true;
	}
	
	public WorkType getWorkType() {
		return workType;
	}
}
