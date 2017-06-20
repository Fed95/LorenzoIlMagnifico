package it.polimi.ingsw.gc_12.event;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

public class EventStartMatch extends Event{
	
	private Match match;
	
	public EventStartMatch(Match match) {
		this.match = match;
	}
	
	public Match getMatch() {
		return match;
	}

	@Override
	public List<Object> getAttributes() {
		return new ArrayList<>();
	}

	@Override
	public List<EffectProvider> getEffectProviders() {
		return effectProviders;
	}

	@Override
	public String toString() {
		return "Match started!";
	}

}
