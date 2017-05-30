package it.polimi.ingsw.gc_12.occupiables;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.EffectProvider;
import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.effect.Effect;

public abstract class Occupiable implements EffectProvider {

	private List<Effect> effects = new ArrayList<>();
	protected List<FamilyMember> occupiers = new ArrayList<>();
	public final static int DEFAULT_MAXNUMBEROFPLAYERS = 1;
	public static final int DEFAULT_REQUIRED_VALUE = 1;
	protected final int requiredValue;

	public Occupiable(int requiredValue, List<Effect> effects) {
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
	}

	public int getRequiredValue() {
		return requiredValue;
	}

	@Override
	public List<Effect> getEffects() {
		return effects;
	}

	public void placeFamilyMember(FamilyMember occupier) {
		this.occupiers.add(occupier);
	}

	public boolean isRequiredValueSatisfied(FamilyMember occupier) {
		if (requiredValue > occupier.getValue())
			return false;
		return true;
	}
}
