package it.polimi.ingsw.gc12.model.board.occupiable;

import it.polimi.ingsw.gc12.model.effect.Effect;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the space works where you can put multiple
 * family members and extends the main class space work
 */
public class SpaceWorkMultiple extends SpaceWork {
	
	private static final int DEFAULT_REQUIRED_VALUE = 1;
	private static final List<Effect> DEFAULT_EFFECTS = new ArrayList<>();

    /**
     * Constructor
     * @param workType type of the space work
     * @param requiredValue required value for placing the family member
     * @param effects effects of the space work
     */
	public SpaceWorkMultiple(WorkType workType, int requiredValue,List<Effect> effects) {
		super(workType, requiredValue, effects);
	}

    /**
     * Constructor with default required value and no effects
     * @param workType type of the work space
     */
	public SpaceWorkMultiple(WorkType workType) {
		super(workType, DEFAULT_REQUIRED_VALUE, DEFAULT_EFFECTS);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getWorkType() + " SpaceWorkMultiple");//.append(System.getProperty("line.separator"));
		sb.append(super.toString());
		return sb.toString();
	}

}
