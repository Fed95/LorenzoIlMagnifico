package it.polimi.ingsw.gc_12.card;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.resource.Resource;

import java.util.List;

public class CardBuilding extends CardDevelopment{

	public CardBuilding(int id, String name, int period,  List<Resource> requirements, List<Effect> effects) {
		super(id, CardType.BUILDING, name, period, requirements, effects);
	}

}
