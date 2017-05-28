package it.polimi.ingsw.gc_12;

import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.exceptions.FamilyMemberAlreadyPresentException;
import it.polimi.ingsw.gc_12.exceptions.OccupiableAlreadyTakenException;

public class SpaceWork extends Occupiable{
	
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
	public void canBeOccupiedBy(FamilyMember occupier) throws FamilyMemberAlreadyPresentException, OccupiableAlreadyTakenException {
		if(!spaceWorkZone.canBeOccupiedBy(occupier))
			throw new FamilyMemberAlreadyPresentException();
	}
	
	public WorkType getWorkType() {
		return workType;
	}
}
