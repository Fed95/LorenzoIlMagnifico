package it.polimi.ingsw.gc12.model.board.occupiable;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;

import java.io.Serializable;
import java.util.List;

/**
 * Interface that represented the classes that have zone,
 * so classes that contains other object that extends occupiables in most cases
 */
public interface Zone extends Serializable {

	boolean canBeOccupiedBy(FamilyMember familyMember);
	List<Occupiable> getOccupiables();

	@Override
	String toString();
}
