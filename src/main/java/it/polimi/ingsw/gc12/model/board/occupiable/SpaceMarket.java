package it.polimi.ingsw.gc12.model.board.occupiable;

import it.polimi.ingsw.gc12.model.effect.Effect;

import java.io.Serializable;
import java.util.List;

/**
 *This class implements the single market
 */
public class SpaceMarket extends Occupiable implements Serializable{

	private int marketNum;

    /**
     * Constructor
     * @param marketNum number of the market
     * @param requiredValue required value for place
     *                     a family member on the marker
     * @param effects effects of the market
     */
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
