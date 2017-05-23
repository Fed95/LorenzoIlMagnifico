package it.polimi.ingsw.gc_12;

import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;

public class SpaceMarket extends Occupiable{
	public SpaceMarket(List<Effect> effects, int requiredValue, int maxNumberOfPlayer){
		super();
		// TODO Auto-generated method stub
	}
	public SpaceMarket(){
		super();
		// TODO Auto-generated method stub
	}

	public boolean isOccupied() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean canBeOccupiedBy(FamilyMember occupier) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
