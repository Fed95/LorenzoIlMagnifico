package it.polimi.ingsw.gc_12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.gc_12.effect.Effect;

public class SpaceActionSingle extends SpaceAction {

	private static Map<ActionType, SpaceActionSingle> instances = new HashMap<>();
	
	private SpaceActionSingle(ActionType actionType, List<Effect> effects) {
		super(actionType, effects);
	}
	
	private SpaceActionSingle(ActionType actionType) {
		super(actionType);
	}
	
	public static SpaceActionSingle instance(ActionType actionType) {
		if(instances.get(actionType) == null) instances.put(actionType, new SpaceActionSingle(actionType));
		return instances.get(actionType);
	}
}
