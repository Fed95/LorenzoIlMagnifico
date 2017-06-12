package it.polimi.ingsw.gc_12.event;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.MatchInstance;
import it.polimi.ingsw.gc_12.MatchRemote;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

public class EventStartMatch extends Event{
	
	private MatchRemote match;
	private MatchInstance matchInstance;
	
	public EventStartMatch(MatchRemote match) throws RemoteException, CloneNotSupportedException {
		this.match = match;
		this.matchInstance = match.getInstance();
	}
	
	public MatchRemote getMatch() {
		return match;
	}

	public MatchInstance getMatchInstance() {
		return matchInstance;
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
