package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.track.FaithSlot;

import java.util.ArrayList;
import java.util.List;

public class EventSupportChurch extends Event {

    public EventSupportChurch(Player player){
        super(player);
    }
    public EventSupportChurch(){
        super();
    }
    @Override
    public List<Object> getAttributes() {
        List<Object> attributes = new ArrayList<>();
        attributes.add(player.getCards());
        return attributes;
    }

    @Override
    public List<EffectProvider> getEffectProviders() {
        return effectProviders;
    }
}
