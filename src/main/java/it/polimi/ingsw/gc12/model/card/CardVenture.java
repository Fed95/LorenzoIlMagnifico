package it.polimi.ingsw.gc12.model.card;

import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.player.resource.MilitaryPoint;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceExchange;

import java.util.List;

public class CardVenture extends CardDevelopment{

	private ResourceExchange militaryExchange;

	public CardVenture(int id, String name, int period, List<Resource> requirements, List<Effect> effects) {
		super(id, CardType.VENTURE, name, period, requirements, effects);
	}

	public CardVenture(int id, String name, int period, List<Resource> requirements, ResourceExchange militaryExchange, List<Effect> effects) {
		this(id, name, period, requirements, effects);
		this.militaryExchange = militaryExchange;
	}

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
