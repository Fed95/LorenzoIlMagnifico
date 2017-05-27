package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.card.CardDeck;
import it.polimi.ingsw.gc_12.card.CardType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TowerSet {
	private Map<CardType, Tower> towers = new HashMap<>();
	
	public TowerSet(){
		for(CardType cardType: CardType.values()) {
			towers.put(cardType, new Tower(cardType));
		}
	}
	
	public Map<CardType, Tower> getTowers() {
		return towers;
	}
	
	public Tower getTower(CardType cardType) {
		return towers.get(cardType);
	}
	
	public List<Occupiable> getOccupiables() {
		List<Occupiable> occupiables = new ArrayList<>();
		for(Tower tower: towers.values()) {
			occupiables.addAll(tower.getFloors());
		}
		return occupiables;
	}

	//Fills all towers with new cards
	public void refresh(){
		for(Tower tower : towers.values()){
			//Retrieves the deck of the corresponding type and period
			CardDeck cardDeck = Match.instance().cardDeckSet.getDecks().get(tower.getType()).get(Match.instance().getPeriodNum());
			for(TowerFloor floor : tower.getFloors()){
				floor.setCard(cardDeck.pickCard());
			}
		}
	}

	@Override
	public String toString() {
		return "TowerSet [towers=" + towers + "]";
	}
	
	
}
