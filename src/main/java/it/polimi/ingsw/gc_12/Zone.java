package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.occupiables.Occupiable;

import java.io.Serializable;
import java.util.List;

public interface Zone extends Serializable {

	boolean canBeOccupiedBy(FamilyMember familyMember);
	List<Occupiable> getOccupiables();

	@Override
	String toString();
}
