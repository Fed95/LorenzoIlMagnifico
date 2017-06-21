package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.event.Event;

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
            throw new RuntimeException("EffectDenyEffect: trying to discard (execute) the effect when not executed (discarded)! confused? lol");
        for(Effect effect : findEffects(event))
            effect.execute(match, event);
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
