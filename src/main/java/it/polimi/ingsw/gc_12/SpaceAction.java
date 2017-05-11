package it.polimi.ingsw.gc_12;

public abstract class SpaceAction extends Occupiable{
	private ActionType actionType;
	
	public SpaceAction(ActionType actionType) {
		super();
		this.actionType = actionType;
	}

}
