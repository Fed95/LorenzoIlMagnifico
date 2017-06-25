package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventReceiveResource;
import it.polimi.ingsw.gc_12.exceptions.ActionDeniedException;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Effect implements Serializable {
	
	protected Event event;
	
	public Effect(Event event) {
		this.event = event;
	}
	
	public Event getEvent() {
		return this.event;
	}

	public abstract void execute(Match match, Event event, boolean validation) throws ActionDeniedException;

	public void execute(Match match, Event event) throws ActionDeniedException {
		execute(match, event, false);
	}
	
	public abstract void discard(Match match, Event event);

	protected List<Resource> applyResourceBonus(ResourceExchange exchange, Match match, Player player) {
		List<Resource> newBonus  = new ArrayList<>();
		for(Resource resource : exchange.getBonus()) {
			if(resource == null)
				continue;
			EventReceiveResource e = new EventReceiveResource(player, resource);
			try {
				match.getEffectHandler().executeEffects(match, e);
			} catch (ActionDeniedException e1) {
				throw new IllegalStateException("Discard denied by effect");
			}
			newBonus.add(e.getResource());
		}
		return newBonus;
	}
}
