package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.gc_12.effect.Effect;

public class SpaceWorkMultiple extends SpaceWork {
	
	private static final int DEFAULT_REQUIRED_VALUE = 1;
	private static final List<Effect> DEFAULT_EFFECTS = new ArrayList<>();
	
	public SpaceWorkMultiple(WorkType workType, int requiredValue, SpaceWorkZone spaceWorkZone, List<Effect> effects) {
		super(workType, requiredValue, spaceWorkZone, effects);
	}

	public SpaceWorkMultiple(WorkType workType, SpaceWorkZone spaceWorkZone) {
		super(workType, DEFAULT_REQUIRED_VALUE, spaceWorkZone, DEFAULT_EFFECTS);
	}

	@Override
	public String toString() {
		return "SpaceWorkMultiple of type " + workType + " - required value: " + super.requiredValue;
	}
}
