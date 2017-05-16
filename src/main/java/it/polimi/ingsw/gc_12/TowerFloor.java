package it.polimi.ingsw.gc_12;

import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;

public class TowerFloor extends Occupiable{
	private final int floorNum;
	private Card card;
	
	public TowerFloor(int floorNum, int requiredValue, List<Effect> effects){
		super(requiredValue, effects);
		this.floorNum = floorNum;
	}
		
	public TowerFloor(int floorNum, int requiredValue){
		this(floorNum, requiredValue, null);
	}

	public int getFloorNum() {
		return floorNum;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	@Override
	public String toString() {
		return "TowerFloor" + floorNum + " card: " + card;
	}
	
	
}
