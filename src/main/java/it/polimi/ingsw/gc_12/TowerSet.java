package it.polimi.ingsw.gc_12;

import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.gc_12.card.CardType;

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

	@Override
	public String toString() {
		return "TowerSet [towers=" + towers + "]";
	}
	
	
}
