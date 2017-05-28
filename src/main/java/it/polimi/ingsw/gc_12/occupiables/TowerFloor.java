package it.polimi.ingsw.gc_12.occupiables;

import java.util.List;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.exceptions.*;

public class TowerFloor extends Occupiable {
	private final int floorNum;
	private Card card;
	private CardType cardType;
	private Tower tower;
	
	public TowerFloor(Tower tower, int floorNum, int requiredValue, CardType cardType, List<Effect> effects){
		super(requiredValue, effects);
		this.tower = tower;
		this.floorNum = floorNum;
		this.cardType = cardType;
	}
		
	public TowerFloor(Tower tower, int floorNum, int requiredValue, CardType cardType){
		this(tower, floorNum, requiredValue, cardType, null);
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

	public boolean isOccupied() {
		return !occupiers.isEmpty();
	}

	@Override
	public void canBeOccupiedBy(FamilyMember occupier) throws OccupiableAlreadyTakenException {
		if(isOccupied())
			throw new OccupiableAlreadyTakenException();
	}

	@Override
	public void placeFamilyMember(FamilyMember occupier) throws RequiredValueNotSatisfiedException, FamilyMemberAlreadyPresentException, OccupiableAlreadyTakenException {
		isRequiredValueSatisfied(occupier);
		this.canBeOccupiedBy(occupier);

		//Followind code is only executed if two previous calls do not throw exceptions

		if(tower.getFloors().stream().allMatch(floor -> !floor.isOccupied())){ //If no floor of the tower has been occupied yet
			tower.activateMalus();
		}
		this.occupiers.add(occupier);
	}

	@Override
	public String toString() {
		return "Floor " + floorNum + " of " + cardType + " tower - required value: " + super.requiredValue ;
	}
}