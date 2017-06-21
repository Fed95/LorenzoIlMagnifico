package it.polimi.ingsw.gc_12.personal_board;

import java.io.Serializable;
import java.util.List;

import it.polimi.ingsw.gc_12.GameMode;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

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
