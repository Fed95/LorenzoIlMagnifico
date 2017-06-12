package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.effect.EffectProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 11/06/2017.
 */
public class EventActionUnavailable extends Event {

	private boolean isFMPlaced;

	public EventActionUnavailable(boolean isFMPlaced) {
		this.isFMPlaced = isFMPlaced;
	}

	public boolean isFMPlaced() {
		return isFMPlaced;
	}

	@Override
	public List<Object> getAttributes() {
		return new ArrayList<>();
	}

	@Override
	public List<EffectProvider> getEffectProviders() {
		return effectProviders;
	}
}
