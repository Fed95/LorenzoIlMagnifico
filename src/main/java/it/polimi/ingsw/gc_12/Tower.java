package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.List;

public class Tower {
	private final CardType type;
	private final List<TowerFloor> floors = new ArrayList<>();

	public Tower(CardType type){
		this.type = type;
	}

	public CardType getType() {
		return type;
	}

}
