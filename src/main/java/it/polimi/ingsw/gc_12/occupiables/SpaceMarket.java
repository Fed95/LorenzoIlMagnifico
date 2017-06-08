package it.polimi.ingsw.gc_12.occupiables;

import java.io.Serializable;
import java.util.List;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.effect.Effect;

public class SpaceMarket extends Occupiable implements Serializable{

	public SpaceMarket(int requiredValue, List<Effect> effects){
		super(requiredValue, effects);
	}
	
	public boolean isOccupied(){
		return !occupiers.isEmpty();
	}

	@Override
	public String toString() {
		return "Space Market. Required value: " + requiredValue + ". Effects: " + getEffects();
	}
}
