package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.EffectProvider;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.track.FaithSlot;

import java.util.ArrayList;
import java.util.List;

public class EventSupportChurch extends Event {

    private FaithSlot faithSlot;

    public EventSupportChurch(Player player, FaithSlot faithSlot){
        this.player = player;
        this.faithSlot = faithSlot;

        effectProviders.addAll(Match.instance().getBoard().getFaithSlots());
    }

    @Override
    public List<Object> getAttributes() {
        List<Object> attributes = new ArrayList<>();
        attributes.add(faithSlot);
        return attributes;
    }

    @Override
    public List<EffectProvider> getEffectProviders() {
        return effectProviders;
    }
}
