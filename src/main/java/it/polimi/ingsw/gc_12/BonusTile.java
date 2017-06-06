package it.polimi.ingsw.gc_12;

import java.util.List;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

public class BonusTile implements EffectProvider {
	GameMode gameMode;
	List<Effect> effects;
	
	public BonusTile(GameMode gameMode,List<Effect> effects){
		this.gameMode=gameMode;
		this.effects=effects;
	}
	@Override
	public List<Effect> getEffects(){
		return effects;
	}
}
