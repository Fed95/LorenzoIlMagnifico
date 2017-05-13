package it.polimi.ingsw.gc_12.event;

import java.util.List;

import it.polimi.ingsw.gc_12.EffectProvider;

public interface EventInterface {
	
	public List<Object> getAttributes();

	public List<EffectProvider> getEffectProviders();
}
