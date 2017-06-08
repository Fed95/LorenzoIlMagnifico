package it.polimi.ingsw.gc_12.effect;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPickCard;

public class EffectHandler {

	public EffectHandler() {
	}

	public void executeEffects(Event event) throws RuntimeException {
		List<Effect> effects = getPossibleEffects(event);

		//System.out.println("effect handler: this event is: " + event);

		// If there is an effect (from the players' card or the place where the family member has been placed)...
		// then execute the effect
		for(Effect effect : effects) {
			//System.out.println("effect handler: events found: " + effect + ": "+ effect.event);
			if(event.equals(effect.getEvent()))
				effect.execute(event);
		}
	}

	
	public void discardEffects(Event event) throws RuntimeException {
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
			try {
				effects.addAll(effectProvider.getEffects());
			}catch(NullPointerException e){}
		}
		return effects;
	}

}
