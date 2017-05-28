package it.polimi.ingsw.gc_12.effect;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.EffectProvider;
import it.polimi.ingsw.gc_12.event.Event;

public class EffectHandler {
	
	private static EffectHandler instance;
	
	private EffectHandler() {}
	
	// EffectHandler is a Singleton
	public static EffectHandler instance() {
		if(instance == null) instance = new EffectHandler();
		return instance;
	}

	public void executeEffects(Event event) {
		List<Effect> effects = getPossibleEffects(event);
		
		// If there is an effect (from the players' card or the place where the family member has been put)...
		// then execute the effect
		for(Effect effect : effects) {
			if(event.equals(effect.getEvent()))
				effect.execute(event);
		}
	}
	
	public void discardEffects(Event event) {
		List<Effect> effects = getPossibleEffects(event);
		
		// If there is a card with an effect triggered by the event, discard the effect
		//(Discards all effects executed by executeEffects)
		for(Effect effect : effects) {
			if(event.equals(effect.getEvent()))
				effect.discard(event);
		}
	}
	
	private List<Effect> getPossibleEffects(Event event) {
		List<EffectProvider> effectProviders = event.getEffectProviders();
		List<Effect> effects = new ArrayList<>();
	
		for(EffectProvider effectProvider: effectProviders) {
			if(effectProvider.getEffects() != null) // TODO: better handle when effects are null
				effects.addAll(effectProvider.getEffects());
		}
		return effects;
	}

}
