package it.polimi.ingsw.gc_12.occupiables;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.exceptions.CannotPlaceFamilyMemberException;

public class SpaceMarket extends Occupiable {

	public SpaceMarket(int requiredValue, List<Effect> effects){
		super(requiredValue, effects);
	}
	
	@Override
	public void canBeOccupiedBy(FamilyMember occupier) throws CannotPlaceFamilyMemberException{
		if(!occupiers.isEmpty())
			throw new CannotPlaceFamilyMemberException("Occupiable already taken!");
	}
}
