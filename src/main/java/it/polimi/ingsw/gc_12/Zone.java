package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.occupiables.Occupiable;

import java.util.List;

public interface Zone {

	boolean canBeOccupiedBy(FamilyMember familyMember);
	List<Occupiable> getOccupiables();

	@Override
	String toString();
}
