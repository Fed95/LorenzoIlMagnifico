package it.polimi.ingsw.gc12.model.board.occupiable;

import it.polimi.ingsw.gc12.model.effect.Effect;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the space work where you can put only a family member annd extends
 * the main class spacework
 */
public class SpaceWorkSingle extends SpaceWork{
	
	private static final int DEFAULT_REQUIRED_VALUE = 1;
	private static final List<Effect> DEFAULT_EFFECTS = new ArrayList<>();

    /**
     * Constructor
     * @param workType type of the workspace
     * @param requiredValue value fro place  a family member
     * @param effects effects of the work space
     */
	public SpaceWorkSingle(WorkType workType, int requiredValue, List<Effect> effects) {
		super(workType, requiredValue, effects);
	}

    /**
     * Constructor with default required value and no effects
     * @param workType type of the work space
     */
	public SpaceWorkSingle(WorkType workType) {
		super(workType, DEFAULT_REQUIRED_VALUE, DEFAULT_EFFECTS);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getWorkType() + " SpaceWorkSingle");//.append(System.getProperty("line.separator"));
		sb.append(super.toString());
		return sb.toString();
	}


}
