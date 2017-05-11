package it.polimi.ingsw.gc_12;

public abstract class Occupiable {
	
	public static final int REQUIRED_VALUE = 1;
	private FamilyMember occupier;
	private FamilyMember requiredFamilyMember;
	
	public Occupiable(int value) {
		this.requiredFamilyMember = new FamilyMember(value);
	}
	
	public Occupiable() {
		this(REQUIRED_VALUE);
	}
	
	public boolean isOccupied() {
		return occupier != null;
	}
	
	public FamilyMember getOccupier() {
		return occupier;
	}
	
	public boolean canBeOccupiedBy(FamilyMember occupier) {
		if(isOccupied())
			return false;
		
		return occupier.getValue() >= requiredFamilyMember.getValue();
	}
}
