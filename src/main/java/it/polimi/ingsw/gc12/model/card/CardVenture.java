package it.polimi.ingsw.gc12.model.card;

import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.player.resource.MilitaryPoint;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceExchange;

import java.util.List;
/**
 * Representing the card venture of the game
 */
public class CardVenture extends CardDevelopment{

	private ResourceExchange militaryExchange;
    /**
     * Constructor
     * @param id id of the card
     * @param name name of the card
     * @param period period of the card
     * @param requirements requirements for taking the card
     * @param effects effect provided by the card
     */
	public CardVenture(int id, String name, int period, List<Resource> requirements, List<Effect> effects) {
		super(id, CardType.VENTURE, name, period, requirements, effects);
	}

	public CardVenture(int id, String name, int period, List<Resource> requirements, ResourceExchange militaryExchange, List<Effect> effects) {
		this(id, name, period, requirements, effects);
		this.militaryExchange = militaryExchange;
	}

    /**
     * Method for understand if the card has a choice in requirements
     * @return boolean
     */
	public boolean hasChoice(){
		return (militaryExchange != null);
	}

	public ResourceExchange getMilitaryExchange() {
		return militaryExchange;
	}

	public Resource getMilitaryCost(){
		return new MilitaryPoint(militaryExchange.getCost().get(0).getValue() - militaryExchange.getBonus().get(0).getValue());
	}

	public Resource getMilitaryRequirement(){
		return militaryExchange.getCost().get(0);
	}
}
