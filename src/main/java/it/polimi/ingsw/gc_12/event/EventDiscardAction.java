package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

import java.util.List;

/**
 * Created by feder on 2017-06-17.
 */
public class EventDiscardAction extends Event{

    public EventDiscardAction(Player player) {
        super(player);
    }

    @Override
    public List<Object> getAttributes() {
        return null;
    }

    @Override
    public String toString() {
        return "Action discarded";
    }
}
