package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.exceptions.ActionDeniedException;

import java.util.ArrayList;
import java.util.List;

public class EffectHandler {

	public EffectHandler() {
	}

	public List<Effect> executeEffects(Match match, Event event) throws ActionDeniedException {
		List<Effect> effects = getPossibleEffects(event);
		List<Effect> executedEffects = new ArrayList<>();
		// If there is an effect (from the players' cards or the place where the family member has been placed):
		try {// then execute the effect
			for (Effect effect : effects) {
				//System.out.println("effect handler: events found: " + effect + ": "+ effect.event);
				if (event.equals(effect.getEvent())) {
					effect.execute(match, event);
					executedEffects.add(effect);
				}
			}
		}catch(ActionDeniedException e){
			discardEffects(executedEffects, event);
			throw e;
		}
		return executedEffects;
	}

	public void discardEffects(List<Effect> executedEffects, Event event) {

		//(Discards all effects executed by executeEffects)
		for(Effect effect : executedEffects) {
			effect.discard(event);
		}
	}
	
	private List<Effect> getPossibleEffects(Event event) {
		List<EffectProvider> effectProviders = event.getEffectProviders();
		List<Effect> effects = new ArrayList<>();

		for(EffectProvider effectProvider: effectProviders) {
			if(effectProvider.getEffects() != null)
				effects.addAll(effectProvider.getEffects());
		}
		return effects;
	}

}
