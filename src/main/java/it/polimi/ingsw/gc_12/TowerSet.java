package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.gc_12.card.CardDevelopmentType;

public class TowerSet {
	private Map<CardDevelopmentType, Tower> towers = new HashMap<>();
	
	public TowerSet(){
		for(CardDevelopmentType cardDevelopmentType: CardDevelopmentType.values()) {
			towers.put(cardDevelopmentType, new Tower(cardDevelopmentType));
		}
	}
	
	public Map<CardDevelopmentType, Tower> getTowers() {
		return towers;
	}
	
	public Tower getTower(CardDevelopmentType cardDevelopmentType) {
		return towers.get(cardDevelopmentType);
	}
	
	public List<Occupiable> getOccupiables() {
		List<Occupiable> occupiables = new ArrayList<>();
		for(Tower tower: towers.values()) {
			occupiables.addAll(tower.getFloors());
		}
		return occupiables;
	}

	@Override
	public String toString() {
		return "TowerSet [towers=" + towers + "]";
	}
	
	
}
