package it.polimi.ingsw.gc_12.occupiables;

import java.io.Serializable;
import java.util.List;

import it.polimi.ingsw.gc_12.WorkType;
import it.polimi.ingsw.gc_12.effect.Effect;

public abstract class SpaceWork extends Occupiable implements Serializable {
	
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
		return !occupiers.isEmpty();
	}
	
	public WorkType getWorkType() {
		return workType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SpaceWork)) return false;
		if(!(o.getClass().equals(this.getClass()))) return false;

		SpaceWork spaceWork = (SpaceWork) o;

		return workType == spaceWork.workType;
	}

	@Override
	public int hashCode() {
		return workType.hashCode();
	}

	@Override
	public String toString(){
		return super.toString();
	}
}
