package it.polimi.ingsw.gc_12.occupiables;

import java.util.List;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.effect.Effect;

public class SpaceMarket extends Occupiable {

	public SpaceMarket(int requiredValue, List<Effect> effects){
		super(requiredValue, effects);
	}
	
	public void canBeOccupiedBy(FamilyMember occupier) throws RuntimeException{
		if(!occupiers.isEmpty())
			throw new RuntimeException("Occupiable already taken!");
	}
}
