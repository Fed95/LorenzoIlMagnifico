package it.polimi.ingsw.gc12.model.card;

import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.player.resource.Resource;

import java.util.List;
/**
 * Representing the card character of the game
 */
public class CardCharacter extends CardDevelopment {
    /**
     * Constructor
     * @param id id of the card
     * @param name name of the card
     * @param period period of the card
     * @param requirements requirements for taking the card
     * @param effects effect provided by the card
     */
	public CardCharacter(int id, String name, int period, List<Resource> requirements, List<Effect> effects) {
		super(id, CardType.CHARACTER, name, period, requirements, effects);
	}
}
