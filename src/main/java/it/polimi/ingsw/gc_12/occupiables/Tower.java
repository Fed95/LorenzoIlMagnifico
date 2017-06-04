package it.polimi.ingsw.gc_12.occupiables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.FamilyMemberColor;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Zone;
import it.polimi.ingsw.gc_12.card.*;
import it.polimi.ingsw.gc_12.effect.*;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.resource.*;

public class Tower implements Zone {
	private final CardType type;
	private final List<TowerFloor> floors = new ArrayList<>();
	private Map<Integer, CardDeck> decks;
	private boolean taken;
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
		this.taken = false;
		decks = Match.instance().cardDeckSet.getDecks().get(type);
		initializeFloors();

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

	public List<CardDevelopment> getCards(){
		List<CardDevelopment> cards = new ArrayList<>();
		for(TowerFloor floor : floors)
			cards.add(floor.getCard());
		return cards;
	}
	
	public void initializeFloors() {
		for (int i = 0; i < CardType.values().length; i++) {
			TowerFloor floor = new TowerFloor(i, DEFAULT_REQUIRED_VALUES.get(i), type);
			floors.add(floor);
		}
	}

	@Override
	public boolean canBeOccupiedBy(FamilyMember familyMember) {
		if (familyMember.getColor().equals(FamilyMemberColor.NEUTRAL))
			return true;

		for (TowerFloor floor : floors)
			for (FamilyMember occupier : floor.getOccupiers())
				if (!occupier.getColor().equals(FamilyMemberColor.NEUTRAL) && familyMember.getOwner().equals(occupier.getOwner()))
					return false;
		return true;
	}

	@Override
	public List<Occupiable> getOccupiables() {
		List<Occupiable> occupiables = new ArrayList<>();
		for(TowerFloor floor : floors) {
			if(!floor.isOccupied())
				occupiables.add(floor);
		}
		return occupiables;
	}

	//Fills all floors with new cards picked from the deck corresponding to the current period
	public void refresh(){
		for(TowerFloor floor : floors)
			floor.setCard(decks.get(Match.instance().getPeriodNum()).pickCard());
	}

	public void activateMalus(){
		List<Occupiable> floorList = new ArrayList<>();
		floorList.addAll(floors);
		Effect towerTakenMalusEffect = new EffectChangeResource(new EventPlaceFamilyMember(floorList), new ResourceExchange(towerTakenMalus, null), false);
		for(Occupiable floor : floors){
			floor.getEffects().add(towerTakenMalusEffect);
		}
		this.taken = true;
	}

	public void deactivateMalus(){
		for(TowerFloor floor : floors){
			floor.getEffects().remove(towerTakenMalus);
		}
	}



	@Override
	public String toString() {
		return "Tower of type " + type + "  (Taken: " + taken + ")";
	}
}
