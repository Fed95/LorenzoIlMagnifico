package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;

public abstract class Occupiable extends EffectProvider {
	
	protected List<FamilyMember> occupiers = new ArrayList<>();
	public final static int DEFAULT_MAXNUMBEROFPLAYER = 1;
	public static final int DEFAULT_REQUIRED_VALUE = 1;
	protected final int requiredValue;
	
	public Occupiable(int requiredValue, List<Effect> effects) {
		super(effects);
		this.requiredValue = requiredValue;
	}
	
	public Occupiable(List<Effect> effects) {
		this(DEFAULT_REQUIRED_VALUE, effects);
	}
	
	public Occupiable() {
		this(DEFAULT_REQUIRED_VALUE, new ArrayList<>());
	}
	
	public List<FamilyMember> getOccupiers() {
		return occupiers;
	};
	
	public int getRequiredValue() {
		return requiredValue;
	}
	
	public boolean placeFamilyMember(FamilyMember occupier) {
		if(this.canBeOccupiedBy(occupier) && isRequiredValueSatisfied(occupier)){
			this.occupiers.add(occupier);
			return true;
		}
		return false;
	}

	protected boolean isRequiredValueSatisfied(FamilyMember occupier) {
		if(requiredValue > occupier.getValue())
			return false;
		return true;
	}
	
	public abstract boolean canBeOccupiedBy(FamilyMember occupier);
}
