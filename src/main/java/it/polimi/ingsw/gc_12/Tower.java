package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tower {
	private final CardType type;
	private final List<TowerFloor> floors = new ArrayList<>();
	// It will be loaded from JSON file
	private final static List<Integer> DEFAULT_REQUIRED_VALUES = new ArrayList<Integer>() {{
		add(1);
		add(3);
		add(5);
		add(6);
	}};

	public Tower(CardType type){
		this.type = type;
		List<Card> cards = Match.instance().getCards(type);
		Collections.shuffle(cards);
		for (int i = 0; i < 4; i++) {
			Card card = cards.get(i);
			initializeFloor(card, i);
		}
	}

	public CardType getType() {
		return type;
	}
	
	public void initializeFloor(Card card, int i) {
		TowerFloor floor = new TowerFloor(i, DEFAULT_REQUIRED_VALUES.get(i));
		floor.setCard(card);
		floors.add(floor);
	}

	@Override
	public String toString() {
		return "Tower of type " + type + ", floors=" + floors;
	}

}
