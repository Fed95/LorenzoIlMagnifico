package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.effect.EffectProvider;

import java.util.List;

public interface EventInterface {
	
	public List<Object> getAttributes();

	public List<EffectProvider> getEffectProviders();
}
