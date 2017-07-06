package it.polimi.ingsw.gc12.model.board.occupiable;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;

import java.io.Serializable;
import java.util.List;

public interface Zone extends Serializable {

	boolean canBeOccupiedBy(FamilyMember familyMember);
	List<Occupiable> getOccupiables();

	@Override
	String toString();
}
