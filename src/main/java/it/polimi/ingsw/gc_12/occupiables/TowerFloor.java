package it.polimi.ingsw.gc_12.occupiables;

import java.util.List;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.exceptions.*;

public class TowerFloor extends Occupiable {

	private final int floorNum;
	private CardDevelopment card;
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

	public void setCard(CardDevelopment card) {
		this.card = card;
	}

	public boolean isOccupied() {
		return !occupiers.isEmpty();
	}

	@Override
	public void canBeOccupiedBy(FamilyMember occupier) throws CannotPlaceFamilyMemberException, CannotPlaceCardException, NotEnoughResourcesException {
		if(isOccupied())
			throw new CannotPlaceFamilyMemberException("Occupiable already taken!");
		if(!card.equals(null)) {
			//Can throw an exception
			Player player = occupier.getOwner();
			player.getPersonalBoard().canPlaceCard(player, card);
		}
	}

	@Override
	public void placeFamilyMember(FamilyMember occupier) throws CannotPlaceFamilyMemberException, CannotPlaceCardException, NotEnoughResourcesException {
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
