package it.polimi.ingsw.gc_12.personal_board;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

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
