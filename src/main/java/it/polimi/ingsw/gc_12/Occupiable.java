package it.polimi.ingsw.gc_12;

import java.util.List;

public interface Occupiable{
	
	public boolean isOccupied();
	
	public List<FamilyMember> getOccupiers();
	
	public boolean canBeOccupiedBy(FamilyMember occupier);
	
	public boolean placeFamilyMember(FamilyMember occupier);
}
