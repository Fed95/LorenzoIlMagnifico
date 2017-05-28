package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.card.*;
import it.polimi.ingsw.gc_12.effect.*;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.resource.*;

public class Tower {
	private final CardType type;
	private final List<TowerFloor> floors = new ArrayList<>();
	private CardDeck cardDeck;
	// It will be loaded from JSON file
	private final static Resource towerTakenMalus = new Money(3);
	private final static List<Integer> DEFAULT_REQUIRED_VALUES = new ArrayList<Integer>() {{
		add(1);
		add(3);
		add(5);
		add(6);
	}};

	public Tower(CardType type){
		this.type = type;
		cardDeck = Match.instance().cardDeckSet.getDecks().get(type).get(Match.instance().getPeriodNum());
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

	//Fills all floors with new cards
	public void refresh(){
		for(TowerFloor floor : floors)
			floor.setCard(cardDeck.pickCard());
	}

	public void activateMalus(){
		List<Occupiable> floorList = new ArrayList<>();
		floorList.addAll(floors);
		Effect towerTakenMalusEffect = new EffectChangeResource(new EventPlaceFamilyMember(floorList), towerTakenMalus, null);
		for(Occupiable floor : floorList){
			floor.getEffects().add(towerTakenMalusEffect);
		}
	}
	public void deactivateMalus(){
		for(TowerFloor floor : floors){
			floor.getEffects().remove(towerTakenMalus);
		}
	}



	@Override
	public String toString() {
		return "Tower of type " + type + ", floors=" + floors;
	}

}
