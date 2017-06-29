package it.polimi.ingsw.gc_12.occupiables;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.effect.Effect;

import java.util.List;

public class TowerFloor extends Occupiable{

	private int floorNum;
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

	public TowerFloor(int floorNum, CardType cardType){
		this(floorNum, 0, cardType, null);
	}

	public TowerFloor(int floorNum){
		this(floorNum, 0, null, null);
	}

	public TowerFloor(){
		this(-1, 0, null, null);
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
		this.occupiers.add(occupier);
		System.out.println(occupiers);
	}

	public CardType getType() {
		return cardType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TowerFloor)) return false;

		TowerFloor that = (TowerFloor) o;

		if (that.floorNum != -1 && floorNum != that.floorNum) return false;

		if(this.cardType == null || that.cardType == null)
			return true;

		return cardType == that.cardType;
	}

	@Override
	public int hashCode() {
		int result = floorNum;
		result = 31 * result + cardType.hashCode();
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getType() + " Tower, Floor n." + floorNum + " (required value " + requiredValue + ")");
		sb.append(super.toString()).append(System.getProperty("line.separator"));
		sb.append(card);
		return sb.toString();
	}
}
