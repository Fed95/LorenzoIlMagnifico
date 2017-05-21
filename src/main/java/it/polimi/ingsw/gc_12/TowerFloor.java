package it.polimi.ingsw.gc_12;

import java.util.List;

import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.effect.Effect;

public class TowerFloor implements Occupiable{
	private final int floorNum;
	private Card card;
	
	public TowerFloor(int floorNum, int requiredValue, List<Effect> effects){
		super();
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

	@Override
	public boolean isOccupied() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<FamilyMember> getOccupiers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canBeOccupiedBy(FamilyMember occupier) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean placeFamilyMember(FamilyMember occupier) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
