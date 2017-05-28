package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.card.CardType;

public class Tower {
	private final CardType type;
	private final List<TowerFloor> floors = new ArrayList<>();
	// It will be loaded from JSON file
	private final static List<Integer> DEFAULT_REQUIRED_VALUES = new ArrayList<Integer>() {{
		add(1);
		add(3);
		add(5);
		add(7);
	}};

	public Tower(CardType type){
		this.type = type;
		for (int i = 0; i < 4; i++) {
			initializeFloor(null, i);
		}
	}

	public CardType getType() {
		return type;
	}
	
	public List<TowerFloor> getFloors() {
		return floors;
	}
	
	public TowerFloor getFloor(int floorNum) {
		return floors.get(floorNum);
	}
	
	public void initializeFloor(Card card, int i) {
		TowerFloor floor = new TowerFloor(this, i, DEFAULT_REQUIRED_VALUES.get(i), type);
		//floor.setCard(card);
		floors.add(floor);
	}

	/*
	//TODO: must implement -3 coins malus when there is another FM on the tower
	public boolean canBeOccupiedBy(FamilyMember occupier) {
		if(occupier.getColor().equals(FamilyMemberColor.NEUTRAL))
			return true;

		for(TowerFloor floor : floors)
			for(FamilyMember i: floor.getOccupiers())
				if(!occupier.getColor().equals(FamilyMemberColor.NEUTRAL) && occupier.getOwner().equals(i.getOwner()))
					return false;
		return true;
	}
	*/

	@Override
	public String toString() {
		return "Tower of type " + type + ", floors=" + floors;
	}

}
