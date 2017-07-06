package it.polimi.ingsw.gc12.model.player.personalboard;

import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.effect.EffectProvider;

import java.io.Serializable;
import java.util.List;

public class BonusTile implements EffectProvider, Serializable {

	List<Effect> effects;
	
	public BonusTile(List<Effect> effects){
		this.effects = effects;
	}

	@Override
	public List<Effect> getEffects(){
		return effects;
	}
}
