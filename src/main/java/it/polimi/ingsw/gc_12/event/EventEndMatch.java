package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

import java.util.ArrayList;
import java.util.List;

public class EventEndMatch extends Event {


    public EventEndMatch(Player player) {
        super(player);

        effectProviders.addAll(player.getCards());
        effectProviders.add(player.getPersonalBoard().getResourceContainer());
    }

    public EventEndMatch(){
    }

    @Override
    public List<Object> getAttributes() {
        return new ArrayList<Object>();
    }

    @Override
    public List<EffectProvider> getEffectProviders() {
        return effectProviders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("line.separator"));
        sb.append("The Match has ended!");
        // TODO: 2017-06-16 tell the player whether he won or not
        return sb.toString();
    }
}
