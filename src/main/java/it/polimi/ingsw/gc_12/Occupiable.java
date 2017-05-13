package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;

public abstract class Occupiable {
	
	public static final int DEFAULT_REQUIRED_VALUE = 1;
	private int requiredValue;
	private FamilyMember occupier;
	private List<Effect> effects = new ArrayList<>();
	
	public Occupiable(int requiredValue, List<Effect> effects) {
		this.requiredValue = requiredValue;
		this.effects = effects;
	}
	
	public Occupiable(List<Effect> effects) {
		this(DEFAULT_REQUIRED_VALUE, effects);
	}
	
	public Occupiable() {
		this(DEFAULT_REQUIRED_VALUE, new ArrayList<>());
	}
	
	public boolean isOccupied() {
		return occupier != null;
	}
	
	public FamilyMember getOccupier() {
		return occupier;
	}
	
	private boolean canBeOccupiedBy(FamilyMember occupier) {
		if(isOccupied())
			return false;
		
		return occupier.getValue() >= requiredValue;
	}
	
	public boolean placeFamilyMember(FamilyMember occupier) {
		if(!canBeOccupiedBy(occupier))
			return false;
		
		this.occupier = occupier;
		for(Effect effect : effects) {
			effect.execute();
		}
		return true;
	}
}
