package it.polimi.ingsw.gc_12.occupiables;

import java.io.Serializable;
import java.util.List;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.effect.Effect;

public class TowerFloor extends Occupiable{

	private final int floorNum;
	private CardDevelopment card;
	private CardType cardType;

	public TowerFloor(int floorNum, int requiredValue, CardType cardType, List<Effect> effects){
		super(requiredValue, effects);
		this.floorNum = floorNum;
		this.cardType = cardType;
	}
		
	public TowerFloor(int floorNum, int requiredValue, CardType cardType){
		this(floorNum, requiredValue, cardType, null);
	}

	public int getFloorNum() {
		return floorNum;
	}

	public CardDevelopment getCard() {
		return card;
	}

	public void setCard(CardDevelopment card) {
		this.card = card;
	}

	public void removeCard(){
		this.card = null;
	}

	public boolean isOccupied() {
		return !occupiers.isEmpty();
	}


	@Override
	public void placeFamilyMember(FamilyMember occupier) {
		System.out.println(this + ": placing " + occupier);
		this.occupiers.add(occupier);
		System.out.println(occupiers);
	}

	@Override
	public String toString() {
		return "Floor " + floorNum + " of " + cardType + " tower - required value: " + super.requiredValue + ". Card: [" + card + "], Occupiers: " + occupiers;
	}

	public CardType getType() {
		return cardType;
	}
}
