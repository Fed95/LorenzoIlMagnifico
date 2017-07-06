package it.polimi.ingsw.gc12.model.board.occupiable;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.effect.EffectProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Occupiable implements EffectProvider, Serializable {

	protected List<Effect> effects = new ArrayList<>();
	protected List<FamilyMember> occupiers = new ArrayList<>();
	public final static int DEFAULT_MAXNUMBEROFPLAYERS = 1;
	public static final int DEFAULT_REQUIRED_VALUE = 1;
	protected final int requiredValue;


	public Occupiable(int requiredValue, List<Effect> effects) {
		this.requiredValue = requiredValue;
		if(effects != null)
			this.effects = effects;
	}

	public Occupiable(List<Effect> effects) {
		this(DEFAULT_REQUIRED_VALUE, effects);
	}

	public Occupiable() {
		this(DEFAULT_REQUIRED_VALUE, new ArrayList<>());
	}


	public void placeFamilyMember(FamilyMember occupier) {
		if(occupier.getColor() != null)
			this.occupiers.add(occupier);
	}

	public boolean isRequiredValueSatisfied(FamilyMember occupier) {
		if (requiredValue > occupier.getValue())
			return false;
		return true;
	}

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
