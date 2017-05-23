package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.gc_12.effect.Effect;

public class SpaceWorkSingle extends SpaceWork{
	
	private static final int DEFAULT_REQUIRED_VALUE = 1;
	private static final List<Effect> DEFAULT_EFFECTS = new ArrayList<>(); 

	public SpaceWorkSingle(WorkType workType, int requiredValue, SpaceWorkZone spaceWorkZone, List<Effect> effects) {
		super(workType, requiredValue, spaceWorkZone, effects);
	}

	public SpaceWorkSingle(WorkType workType, SpaceWorkZone spaceWorkZone) {
		super(workType, DEFAULT_REQUIRED_VALUE, spaceWorkZone, DEFAULT_EFFECTS);
	}
}
