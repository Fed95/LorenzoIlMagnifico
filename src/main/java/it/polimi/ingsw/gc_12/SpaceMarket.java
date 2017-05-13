package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;

public class SpaceMarket extends Occupiable{
	public SpaceMarket(List<Effect> effects, int requiredValue, int maxNumberOfPlayer){
		super(requiredValue,effects);
	}
	public SpaceMarket(){
		super();
	}
	
}
