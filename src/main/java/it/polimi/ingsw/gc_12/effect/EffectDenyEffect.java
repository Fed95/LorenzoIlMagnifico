package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.exceptions.ActionDeniedException;

import java.util.ArrayList;
import java.util.List;

public class EffectDenyEffect extends Effect {

    private Match match;
    private EffectProvider effectProvider;

    public EffectDenyEffect(Event event, EffectProvider effectProvider) {
        super(event);
        this.effectProvider = effectProvider;
    }

    public EffectDenyEffect(Event event) {
        super(event);
    }

    @Override
    public void execute(Match match, Event event) {
        this.match = match;
        for(Effect effect : findEffects(event))
            effect.discard(event);
    }

    @Override
    public void discard(Event event) {
        if(match == null)
            throw new IllegalStateException("EffectDenyEffect: trying to discard (execute) the effect when not executed (discarded)! confused? lol");
        for(Effect effect : findEffects(event)) {
            if(effect instanceof EffectChangeResource && ((EffectChangeResource) effect).hasChoice())
                throw new IllegalStateException("Trying to apply EffectDenyEffect to a ChangeResource effect with choice!");

            List<Effect> executedEffects = new ArrayList<>();
            try {
                executedEffects =  match.getEffectHandler().executeEffects(match, event);
            } catch (ActionDeniedException e) {
                match.getEffectHandler().discardEffects(executedEffects, event);
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
}
