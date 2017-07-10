package it.polimi.ingsw.gc12.model.board.occupiable;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.effect.EffectProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that represent evry elements that can be occupied by a family member
 * All the occupiables has effects,required value in common
 */
public abstract class Occupiable implements EffectProvider, Serializable {

	protected List<Effect> effects = new ArrayList<>();
	protected List<FamilyMember> occupiers = new ArrayList<>();
	public final static int DEFAULT_MAXNUMBEROFPLAYERS = 1;
	public static final int DEFAULT_REQUIRED_VALUE = 1;
	protected final int requiredValue;

    /**
     * Constructor
     * @param requiredValue required value for place the family member on a certain occupiable
     * @param effects effects of the occupiable
     */
	public Occupiable(int requiredValue, List<Effect> effects) {
		this.requiredValue = requiredValue;
		if(effects != null)
			this.effects = effects;
	}

    /**
     * Construtor with a default required value
     * @param effects effects of the occupiable
     */
	public Occupiable(List<Effect> effects) {
		this(DEFAULT_REQUIRED_VALUE, effects);
	}

    /**
     * Constructor with a default required value and no effects
     */
	public Occupiable() {
		this(DEFAULT_REQUIRED_VALUE, new ArrayList<>());
	}

    /**
     * place a family member
     * @param occupier family member to place
     */
	public void placeFamilyMember(FamilyMember occupier) {
		if(occupier.getColor() != null)
			this.occupiers.add(occupier);
	}

    /**
     * Check i fthe required value is satisfied for place a family member
     * @param occupier family member to check the value
     * @return boolean
     */
	public boolean isRequiredValueSatisfied(FamilyMember occupier) {
		if (requiredValue > occupier.getValue())
			return false;
		return true;
	}

    /**
     * Revmoving the family member from the occpiers
     */
	public void free(){
		this.occupiers.clear();
	}

	public List<FamilyMember> getOccupiers() {
		return occupiers;
	}

	public int getRequiredValue() {
		return requiredValue;
	}

	@Override
	public List<Effect> getEffects() {
		return effects;
	}

	@Override
	public abstract boolean equals(Object o);

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		//sb.append("     Occupiers: " + this.getOccupiers()).append(System.getProperty("line.separator"));
		sb.append("			Effects: " + this.getEffects());
		//sb.append(System.getProperty("line.separator"));
		return sb.toString();
	}
}
