package it.polimi.ingsw.gc_12;

import java.util.List;
import it.polimi.ingsw.gc_12.effect.Effect;

public class SpaceWorkMultiple extends SpaceWork {
	
	public SpaceWorkMultiple(WorkType workType, int requiredValue, SpaceWorkZone spaceWorkZone, List<Effect> effects) {
		super(workType, requiredValue, spaceWorkZone, effects);
	}
}
