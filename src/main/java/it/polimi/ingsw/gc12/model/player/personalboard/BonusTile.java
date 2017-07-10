package it.polimi.ingsw.gc12.model.player.personalboard;

import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.effect.EffectProvider;

import java.io.Serializable;
import java.util.List;

/**
 * Representing th etiles on the personal board.
 */
public class BonusTile implements EffectProvider, Serializable {

	private List<Effect> effects;
	private int id;

    /**
     * Constructor
     * @param effects effects of the tile
     * @param id id of the tile
     */
	public BonusTile(List<Effect> effects, int id){
		this.effects = effects;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public List<Effect> getEffects(){
		return effects;
	}
}
