package it.polimi.ingsw.gc_12;

import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;

public abstract class SpaceAction extends Occupiable{
	private ActionType actionType;
	
	public SpaceAction(ActionType actionType, List<Effect> effects) {
		super(effects);
		this.actionType = actionType;
	}
	
	public SpaceAction(ActionType actionType) {
		super();
		this.actionType = actionType;
	}

}
