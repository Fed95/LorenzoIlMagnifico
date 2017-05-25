package it.polimi.ingsw.gc_12;

import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.exceptions.FamilyMemberAlreadyPresentException;
import it.polimi.ingsw.gc_12.exceptions.OccupiableAlreadyTakenException;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;

public class SpaceWork extends Occupiable{
	
	protected WorkType workType;
	protected int requiredValueWork;
	protected SpaceWorkZone spaceWorkZone;
	
	public SpaceWork(WorkType workType, int requiredValueWork, SpaceWorkZone spaceWorkZone, List<Effect> effects){
		super(effects,ListOccupiable.SPACE_WORK);
		this.workType = workType;
		this.requiredValueWork = requiredValueWork;
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
