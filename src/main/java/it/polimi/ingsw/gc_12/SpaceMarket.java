package it.polimi.ingsw.gc_12;

import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.exceptions.OccupiableAlreadyTakenException;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;

public class SpaceMarket extends Occupiable{

	public SpaceMarket(List<Effect> effects, int requiredValue, int maxNumberOfPlayer){
		super(requiredValue, effects);
		// TODO Auto-generated method stub
	}
	public SpaceMarket(){
		super();
		// TODO Auto-generated method stub
	}

	public void isOccupied() throws OccupiableAlreadyTakenException{
		if(!occupiers.isEmpty())
			throw new OccupiableAlreadyTakenException();
	}
	
	@Override
	public void canBeOccupiedBy(FamilyMember occupier) throws OccupiableAlreadyTakenException{
		this.isOccupied();
	}
	
	
}
