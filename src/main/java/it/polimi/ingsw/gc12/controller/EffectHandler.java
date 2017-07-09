package it.polimi.ingsw.gc12.controller;

import it.polimi.ingsw.gc12.model.card.LeaderCard;
import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.effect.EffectProvider;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;

import java.util.ArrayList;
import java.util.List;

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
