package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.exceptions.FamilyMemberAlreadyPresentException;
import it.polimi.ingsw.gc_12.exceptions.OccupiableAlreadyTakenException;

public class SpaceWorkSingle extends SpaceWork{
	
	private static final int DEFAULT_REQUIRED_VALUE = 1;
	private static final List<Effect> DEFAULT_EFFECTS = new ArrayList<>(); 

	public SpaceWorkSingle(WorkType workType, int requiredValue, SpaceWorkZone spaceWorkZone, List<Effect> effects) {
		super(workType, requiredValue, spaceWorkZone, effects);
	}

	public SpaceWorkSingle(WorkType workType, int requiredValue, List<Effect> effects) {
		super(workType, requiredValue, effects);
	}

	public SpaceWorkSingle(WorkType workType, SpaceWorkZone spaceWorkZone) {
		super(workType, DEFAULT_REQUIRED_VALUE, spaceWorkZone, DEFAULT_EFFECTS);
	}

	@Override
	public String toString() {
		return "SpaceWorkSingle of type " + workType + "- required value: " + super.requiredValue;
	}


	@Override
	public void canBeOccupiedBy(FamilyMember occupier) throws FamilyMemberAlreadyPresentException, OccupiableAlreadyTakenException {
		if(!spaceWorkZone.canBeOccupiedBy(occupier))
			throw new FamilyMemberAlreadyPresentException();
		if(isOccupied())
			throw new OccupiableAlreadyTakenException();
	}
}
