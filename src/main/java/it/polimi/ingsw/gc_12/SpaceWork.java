package it.polimi.ingsw.gc_12;

import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;

public abstract class SpaceWork extends Occupiable{
	private WorkType workType;
	
	public SpaceWork(WorkType actionType, List<Effect> effects) {
		super(effects);
		this.workType = actionType;
	}
	
	public SpaceWork(WorkType actionType) {
		super();
		this.workType = actionType;
	}

}
