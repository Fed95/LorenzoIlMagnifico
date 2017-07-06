package it.polimi.ingsw.gc12.model.effect;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;

import java.util.ArrayList;
import java.util.List;

public class EffectDenyEffect extends Effect {

    private Match match;
    private EffectProvider effectProvider;
    private String description;

    public EffectDenyEffect(Event event, EffectProvider effectProvider, String description) {
        super(event);
        this.effectProvider = effectProvider;
        this.description = description;
    }

    public EffectDenyEffect(Event event, String description) {
        super(event);
        this.description = description;
    }

    @Override
    public void execute(Match match, Event event, boolean validation) {
        if(!validation) {
            this.match = match;
            for (Effect effect : findEffects(event))
                effect.discard(match, event);
        }
        //TODO: CHECK IF THERE SHOULD BE A VALIDATION PROCEDURE
    }

    @Override
    public void discard(Match match, Event event) {
        if(match == null)
            throw new IllegalStateException("EffectDenyEffect: trying to discard (execute) the effect when not executed (discarded)! confused? lol");
        for(Effect effect : findEffects(event)) {
            if(effect instanceof EffectChangeResource && ((EffectChangeResource) effect).hasChoice())
                throw new IllegalStateException("Trying to apply EffectDenyEffect to a ChangeResource effect with choice!");

            List<Effect> executedEffects = new ArrayList<>();
            try {
                executedEffects =  match.getEffectHandler().executeEffects(match, event);
            } catch (ActionDeniedException e) {
                match.getEffectHandler().discardEffects(match, executedEffects, event);
            }
        }
    }

    private List<Effect> findEffects(Event event){
        List<Effect> effects = new ArrayList<>();
        for(EffectProvider effectProvider : event.getEffectProviders()){
            if(effectProvider.equals(this.effectProvider)){
                for(Effect effect : effectProvider.getEffects())
                    if(effect.getEvent().equals(event))
                        effects.add(effect);
            }
        }
        return effects;
    }

    @Override
    public String toString() {
        return description;
    }
}
