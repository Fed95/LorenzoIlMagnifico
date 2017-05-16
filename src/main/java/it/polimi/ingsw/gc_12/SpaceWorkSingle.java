package it.polimi.ingsw.gc_12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.gc_12.effect.Effect;

public class SpaceWorkSingle extends SpaceWork {

	private static Map<WorkType, SpaceWorkSingle> instances = new HashMap<>();
	
	private SpaceWorkSingle(WorkType workType, List<Effect> effects) {
		super(workType, effects);
	}
	
	private SpaceWorkSingle(WorkType workType) {
		super(workType);
	}
	
	public static SpaceWorkSingle instance(WorkType workType) {
		if(instances.get(workType) == null) instances.put(workType, new SpaceWorkSingle(workType));
		return instances.get(workType);
	}
}
