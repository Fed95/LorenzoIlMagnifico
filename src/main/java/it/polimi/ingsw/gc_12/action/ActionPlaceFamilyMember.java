package it.polimi.ingsw.gc_12.action;

import java.util.HashSet;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.exceptions.FamilyMemberAlreadyPresentException;
import it.polimi.ingsw.gc_12.exceptions.InvalidParametersException;
import it.polimi.ingsw.gc_12.exceptions.OccupiableAlreadyTakenException;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;

public class ActionPlaceFamilyMember extends Action implements ActionInterface{

	private FamilyMember familyMember;
	private Occupiable occupiable;
	
	public ActionPlaceFamilyMember(Player player, FamilyMember familyMember) {
		super(player);
		this.familyMember = familyMember;
	}
	
	public ActionPlaceFamilyMember(Player player, FamilyMember familyMember, Occupiable occupiable) {
		this(player, familyMember);
		this.occupiable = occupiable;
	}
	
	public FamilyMember getFamilyMember() {
		return familyMember;
	}
	
	public Occupiable getOccupiable() {
		return occupiable;
	}
	
	public void setOccupiable(Occupiable occupiable) {
		this.occupiable = occupiable;
	}

	@Override
	public HashSet<Object> getAttributes() {
		HashSet<Object> attributes = new HashSet<>();
		attributes.add(familyMember);
		attributes.add(occupiable);
		return attributes;
	}
	
	@Override
	public void start() throws RequiredValueNotSatisfiedException, FamilyMemberAlreadyPresentException, InvalidParametersException, OccupiableAlreadyTakenException {
		if(familyMember == null || occupiable == null)
			throw new InvalidParametersException();

		player.placeFamilyMember(familyMember, occupiable);
	}

	/*@Override
	public String toString() {
		return "ActionPlaceFamilyMember [familyMember=" + familyMember + ", occupiable=" + occupiable + "]";
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((familyMember == null) ? 0 : familyMember.hashCode());
		result = prime * result + ((occupiable == null) ? 0 : occupiable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (getClass() != obj.getClass())
			return false;
		ActionPlaceFamilyMember other = (ActionPlaceFamilyMember) obj;
		if (familyMember == null) {
			if (other.familyMember != null)
				return false;
		} else if (!familyMember.equals(other.familyMember))
			return false;
		if (occupiable == null) {
			if (other.occupiable != null)
				return false;
		} else if (!occupiable.equals(other.occupiable))
			return false;
		return true;
	}
}
