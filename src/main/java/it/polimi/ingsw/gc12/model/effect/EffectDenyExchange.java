package it.polimi.ingsw.gc12.model.effect;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceExchange;

import java.util.ArrayList;
import java.util.List;


/**
 * This effectd reverts a bonus or malus from all ResourceExchange effects of a specified EffectProvider.
 * During a placement validation it is executed and at the end of the validation it is discarded (the effect is reverted)
 */
public class EffectDenyExchange extends Effect {

    private Match match;
    private EffectProvider effectProvider;
    private String description;
    List<Resource> changedResources = new ArrayList<>();
    private boolean bonus; // If true, the bonus is reverted, if false the cost is reverted

    public EffectDenyExchange(Event event, EffectProvider effectProvider, String description, boolean bonus){
        super(event);
        if(effectProvider != null)
            this.effectProvider = effectProvider;
        this.description = description;
        this.bonus = bonus;
    }

    public EffectDenyExchange(Event event, String description, boolean bonus) {
        this(event, null, description, bonus);
    }

    @Override
    public void execute(Match match, Event event, boolean validation) {
        this.match = match;
        for (Effect effect : findEffects(event)) {
            if (!(effect instanceof EffectChangeResource))
                throw new IllegalStateException("Can only deny ResourceExchange effects");
            EffectChangeResource e = (EffectChangeResource) effect;
            if (bonus) {
                for (ResourceExchange exchange : e.getExchanges()) {
                    event.getPlayer().removeResources(exchange.getBonus());
                    changedResources.addAll(exchange.getBonus());
                }
            }else{
                for (ResourceExchange exchange : e.getExchanges()) {
                    event.getPlayer().addResources(exchange.getCost());
                    changedResources.addAll(exchange.getCost());
                }
            }
        }
    }

    @Override
    public void discard(Match match, Event event) {
        if(bonus)
            event.getPlayer().addResources(changedResources);
        else
            event.getPlayer().removeResources(changedResources);
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

    public EffectProvider getEffectProvider() {
        return effectProvider;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
