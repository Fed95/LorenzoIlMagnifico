package it.polimi.ingsw.gc12.controller;

import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.effect.EffectProvider;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;

import java.util.ArrayList;
import java.util.List;

/**
 * The EffectHandler's purpose is ti activate all effects relative to a certain event.
 * When "executeEffects" is invoked, the handler collects all the possible effect providers of that particular moment.
 * The event contained in every effect of every effect provider is compared with the received event and, if it is considered equal, the effect is executed.
 * "executeEffects" can be invoked in validation mode (used by the match to determine which actions can be performed by the player).
 * Every effect handles the validation mode differently: some effects will be executed and discarded at the end of the validation, others will simply not execute.
 */
public class EffectHandler {

	public EffectHandler() {
	}

	public List<Effect> executeEffects(Match match, Event event, boolean validation) throws ActionDeniedException {
		List<Effect> effects = getPossibleEffects(event);
		List<Effect> executedEffects = new ArrayList<>();
		// If there is an effect (from the players' cards or the place where the family member has been placed):
		// then execute the effect
		try {
			for (Effect effect : effects) {
				if (event.equals(effect.getEvent())) {
					effect.execute(match, event, validation);
					executedEffects.add(effect);
				}
			}
		}catch(ActionDeniedException e){
			discardEffects(match, executedEffects, event);
			throw e;
		}
		return executedEffects;
	}

	public List<Effect> executeEffects(Match match, Event event) throws ActionDeniedException {
		return executeEffects(match, event, false);
	}

	public void discardEffects(Match match, List<Effect> executedEffects, Event event) {

		//(Discards all effects executed by executeEffects)
		for(Effect effect : executedEffects) {
			effect.discard(match, event);
		}
	}
	
	private List<Effect> getPossibleEffects(Event event) {
		List<EffectProvider> effectProviders = event.getEffectProviders();
		List<Effect> effects = new ArrayList<>();

		for(EffectProvider effectProvider: effectProviders) {
			List<Effect> effectList = effectProvider.getEffects();
			if(effectList != null)
				effects.addAll(effectList);
		}
		return effects;
	}

}
