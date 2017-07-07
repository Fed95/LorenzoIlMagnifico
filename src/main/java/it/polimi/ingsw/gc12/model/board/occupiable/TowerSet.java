package it.polimi.ingsw.gc12.model.board.occupiable;

import it.polimi.ingsw.gc12.model.card.CardDeckSet;
import it.polimi.ingsw.gc12.model.card.CardType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TowerSet implements Serializable{
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
	public void refresh(int period){
		for(Tower tower : towers.values())
			tower.refresh(period);
	}

	public void setCards(CardDeckSet cardDeckSet) {
		for(Tower tower: towers.values()) {
			tower.setDecks(cardDeckSet.getDeck(tower.getType()));
		}
	}

	@Override
	public String toString() {
		return "TowerSet [towers=" + towers + "]";
	}
	
	
}