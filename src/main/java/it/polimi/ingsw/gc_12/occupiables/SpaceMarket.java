package it.polimi.ingsw.gc_12.occupiables;

import it.polimi.ingsw.gc_12.effect.Effect;

import java.io.Serializable;
import java.util.List;

public class SpaceMarket extends Occupiable implements Serializable{

	private int marketNum;

	public SpaceMarket( int marketNum, int requiredValue, List<Effect> effects){
		super(requiredValue, effects);
		this.marketNum = marketNum;
	}
	
	public boolean isOccupied(){
		return !occupiers.isEmpty();
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

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Marketplace n." + marketNum + "        ");//.append(System.getProperty("line.separator"));
		sb.append(super.toString());
		return sb.toString();
	}
}
