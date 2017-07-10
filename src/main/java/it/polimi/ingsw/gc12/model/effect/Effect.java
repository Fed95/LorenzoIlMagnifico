package it.polimi.ingsw.gc12.model.effect;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventReceiveResource;
import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceExchange;

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


	/**
	* An EffectReceiveResource is created and all effects regarding this event are executed, modifying the resource of the event
	* the new modified resource is then applied to the exchange bonus
	*/
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
