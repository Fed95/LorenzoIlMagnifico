package it.polimi.ingsw.gc_12;

import java.util.List;

import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.effect.Effect;

public class TowerFloor extends Occupiable{
	private final int floorNum;
	private Card card;
	private CardType cardType;
	
	public TowerFloor(int floorNum, int requiredValue, CardType cardType, List<Effect> effects){
		super();
		this.floorNum = floorNum;
		this.cardType = cardType;
	}
		
	public TowerFloor(int floorNum, int requiredValue, CardType cardType){
		this(floorNum, requiredValue, cardType, null);
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
		return "Floor " + floorNum + " of " + cardType + " tower" /*+ " card: " + card*/;
	}

	@Override
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
