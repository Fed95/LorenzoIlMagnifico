package it.polimi.ingsw.gc_12.occupiables;

import java.util.List;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.WorkType;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.exceptions.CannotPlaceFamilyMemberException;

public abstract class SpaceWork extends Occupiable {
	
	protected WorkType workType;
	protected SpaceWorkZone spaceWorkZone;
	
	public SpaceWork(WorkType workType, int requiredValue, List<Effect> effects){
		super(requiredValue, effects);
		this.workType = workType;
	}

	public SpaceWork(WorkType workType, int requiredValue, SpaceWorkZone spaceWorkZone, List<Effect> effects){
		this(workType, requiredValue, effects);
		this.spaceWorkZone = spaceWorkZone;
		spaceWorkZone.addSpaceWork(this);
	}

	public boolean isOccupied() {
		if(occupiers.isEmpty())
			return false;
		return true;
	}

	@Override
	public void canBeOccupiedBy(FamilyMember occupier) throws CannotPlaceFamilyMemberException {
		if(!spaceWorkZone.canBeOccupiedBy(occupier))
			throw new CannotPlaceFamilyMemberException("A family member of your family is already working here!");
	}
	
	public WorkType getWorkType() {
		return workType;
	}
}
