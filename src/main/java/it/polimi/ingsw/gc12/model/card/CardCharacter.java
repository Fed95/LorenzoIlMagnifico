package it.polimi.ingsw.gc12.model.card;

import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.player.resource.Resource;

import java.util.List;

public class CardCharacter extends CardDevelopment {

	public CardCharacter(int id, String name, int period, List<Resource> requirements, List<Effect> effects) {
		super(id, CardType.CHARACTER, name, period, requirements, effects);
	}
}
