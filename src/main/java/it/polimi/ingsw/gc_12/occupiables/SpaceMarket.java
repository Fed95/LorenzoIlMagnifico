package it.polimi.ingsw.gc_12.occupiables;

import java.util.List;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.effect.Effect;

public class SpaceMarket extends Occupiable {

	public SpaceMarket(int requiredValue, List<Effect> effects){
		super(requiredValue, effects);
	}
	
	public boolean isOccupied(){
		if(occupiers.isEmpty())
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "Space Market. Required value: " + requiredValue;
	}
}
