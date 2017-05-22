package it.polimi.ingsw.gc_12;

import java.util.List;
import it.polimi.ingsw.gc_12.effect.Effect;

public class SpaceWorkSingle extends SpaceWork{

	public SpaceWorkSingle(WorkType workType, int requiredValue, SpaceWorkZone spaceWorkZone, List<Effect> effects) {
		super(workType, requiredValue, spaceWorkZone, effects);
	}
}
