package it.polimi.ingsw.gc_12;

import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;

public class SpaceWork extends Occupiable{
	
	protected WorkType workType;
	int requiredValue;
	protected SpaceWorkZone spaceWorkZone;
	
	public SpaceWork(WorkType workType, int requiredValue, SpaceWorkZone spaceWorkZone, List<Effect> effects){
		super(effects);
		this.workType = workType;
		this.requiredValue = requiredValue;
		this.spaceWorkZone = spaceWorkZone;
		spaceWorkZone.addSpaceWork(this);
	}

	@Override
	public boolean isOccupied() {
		if(occupiers.isEmpty())
			return false;
		return true;
	}

	@Override
	public boolean canBeOccupiedBy(FamilyMember occupier) {
		if(occupier.getValue() >= requiredValue && spaceWorkZone.canBeOccupiedBy(occupier))
			return true;
		return false;
	}
	
	public WorkType getWorkType() {
		return workType;
	}
}
