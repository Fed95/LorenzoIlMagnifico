package it.polimi.ingsw.gc_12.occupiables;

import it.polimi.ingsw.gc_12.Occupiable;
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

	//Fills the towerfloors with new cards from the corresponding deck
	//Deactivates malus if it has been activated during the turn
	public void refresh(){
		for(Tower tower : towers.values()){
			tower.refresh();
			tower.deactivateMalus();
		}
	}

	@Override
	public String toString() {
		return "TowerSet [towers=" + towers + "]";
	}
	
	
}
