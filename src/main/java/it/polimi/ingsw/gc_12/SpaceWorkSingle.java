package it.polimi.ingsw.gc_12;

import java.util.List;
import it.polimi.ingsw.gc_12.effect.Effect;

public class SpaceWorkSingle extends Occupiable{

	private final WorkType workType;
	private SpaceWork spaceWork;

	public SpaceWorkSingle(WorkType workType, int requiredValue, SpaceWork spaceWork, List<Effect> effects) {
		super(effects);
		this.workType = workType;
		this.spaceWork = spaceWork;
		spaceWork.setSpaceWorkSingle(this);
	}
	
	@Override
	public boolean isOccupied() {
		if(occupiers.isEmpty())
			return false;
		return true;
	}

	// TODO: Consider possibility of having more than one occupier due to Leader card effects
	@Override
	public boolean canBeOccupiedBy(FamilyMember occupier) {
		if(!this.isOccupied() && occupier.getValue() >= requiredValue && spaceWork.canBeOccupiedBy(occupier))
			return true;
		return false;
	}

	public WorkType getWorkType() {
		return workType;
	}
}
