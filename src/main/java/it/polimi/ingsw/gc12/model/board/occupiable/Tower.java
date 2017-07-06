package it.polimi.ingsw.gc12.model.board.occupiable;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc12.model.card.CardDeck;
import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.card.CardType;
import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.effect.EffectChangeResource;
import it.polimi.ingsw.gc12.model.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc12.misc.json.loader.LoaderConfig;
import it.polimi.ingsw.gc12.model.player.resource.Money;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceExchange;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tower implements Zone, Serializable{
	private final CardType type;
	private final List<TowerFloor> floors = new ArrayList<>();
	private Map<Integer, CardDeck> decks;
	// It will be loaded from JSON file
	private final static Resource towerTakenMalus = new Money(3);

	public Tower(CardType type){
		this.type = type;
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
		List<Integer> requiredValues = new LoaderConfig().get(null).getRequiredValues();
		for (int i = 0; i < CardType.values().length; i++) {
			TowerFloor floor = new TowerFloor(i, requiredValues.get(i), type);
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
	public void refresh(int period){
		for(TowerFloor floor : floors)
			floor.setCard(decks.get(period).pickCard());
		deactivateMalus();
	}

	public void activateMalus(){
		List<Occupiable> floorList = new ArrayList<>();
		floorList.addAll(floors);
		Effect towerTakenMalusEffect = new EffectChangeResource(new EventPlaceFamilyMember(floorList), new ResourceExchange(towerTakenMalus, null), false);
		for(Occupiable floor : floors){
			List<Effect> effects = floor.getEffects();
			if(effects != null)
				floor.getEffects().add(towerTakenMalusEffect);
		}
	}

	public void deactivateMalus(){
		for(TowerFloor floor : floors){
			floor.getEffects().remove(towerTakenMalus);
		}
	}

	public void setDecks(Map<Integer, CardDeck> decks) {
		this.decks = decks;
	}

	public boolean isTaken() {
		return floors.stream().anyMatch(floor -> floor.isOccupied());
	}

	@Override
	public String toString() {
		return "Tower of type " + type + "  (Taken: " + isTaken() + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Tower)) return false;

		Tower tower = (Tower) o;

		return type == tower.type;
	}

	@Override
	public int hashCode() {
		return type != null ? type.hashCode() : 0;
	}
}
