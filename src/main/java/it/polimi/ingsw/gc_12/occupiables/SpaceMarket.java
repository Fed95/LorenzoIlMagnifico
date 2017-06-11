package it.polimi.ingsw.gc_12.occupiables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.effect.Effect;

public class SpaceMarket extends Occupiable implements Serializable{

	private int marketNum;

	public SpaceMarket( int marketNum, int requiredValue, List<Effect> effects){
		super(requiredValue, effects);
		this.marketNum = marketNum;
	}
	
	public boolean isOccupied(){
		return !occupiers.isEmpty();
	}

	@Override
	public String toString() {
		return "Space Market. Required value: " + requiredValue + ". Effects: " + getEffects();
	}

	public int getMarketNum() {
		return marketNum;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SpaceMarket)) return false;

		SpaceMarket that = (SpaceMarket) o;

		return marketNum == that.marketNum;
	}

	@Override
	public int hashCode() {
		return marketNum;
	}
}
