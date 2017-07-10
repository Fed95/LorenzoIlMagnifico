package it.polimi.ingsw.gc12.model.card;

import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.effect.EffectProvider;
import it.polimi.ingsw.gc12.model.player.resource.Resource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the card and has subclasses cardDevelopment, CardLeader for representing the cards
 */
public class Card implements EffectProvider, Serializable {

	private final int id;
	protected final String name;
	protected List<Resource> requirements;
	protected List<Effect> effects = new ArrayList<>();

    /**
     *Constructor
     * @param id id of the card
     * @param name name of the card
     * @param requirements requirements for taking a card
     * @param effects effects that the card provides
     */
	public Card(int id, String name, List<Resource> requirements, List<Effect> effects){
		this.id = id;
		this.name = name;
		this.requirements = requirements;
		this.effects = effects;
	}

	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public List<Resource>  getRequirements() {
		return requirements;
	}

	public List<Effect> getEffects() {
		return effects;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Card)) return false;

		Card card = (Card) o;

		return id == card.id;
	}

	@Override
	public int hashCode() {
		return id;
	}
}
