package it.polimi.ingsw.gc_12.effect;

import java.util.List;

import it.polimi.ingsw.gc_12.Player;
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
		Player player = event.getPlayer();
		
		List<Effect> cardsEffects = player.getCardsEffects();
		
		// If there is a card with an effect triggered by the event, execute the effect
		for(Effect effect : cardsEffects) {
			if(event.equals(effect.getEvent()))
				effect.execute();
		}
	}
	
	public void discardEffects(Event event) {
		Player player = event.getPlayer();
		
		List<Effect> cardsEffects = player.getCardsEffects();
		
		// If there is a card with an effect triggered by the event, execute the effect
		for(Effect effect : cardsEffects) {
			if(event.equals(effect.getEvent()))
				effect.discard();
		}
	}

}
