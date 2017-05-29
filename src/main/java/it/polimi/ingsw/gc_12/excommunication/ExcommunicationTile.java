package it.polimi.ingsw.gc_12.excommunication;

import java.util.List;

import it.polimi.ingsw.gc_12.EffectProvider;
import it.polimi.ingsw.gc_12.effect.Effect;

public class ExcommunicationTile implements EffectProvider {
	private int period;
	List<Effect> effects;
	public ExcommunicationTile(int period, List<Effect> effects){
		this.period = period;
		this.effects = effects;
	}
	public int getPeriod() {
		return period;
	}
	@Override
	public List<Effect> getEffects(){
		return effects;
	}
}
