package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.EventReceiveResource;
import it.polimi.ingsw.gc_12.exceptions.ActionDeniedException;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActionChooseExchange extends Action {

    private ResourceExchange exchange;

    public ActionChooseExchange(Player player, ResourceExchange exchange) {
        super(player);
        this.exchange = exchange;
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        //Controls have already been made on the player's resources
        player.removeResources(exchange.getCost());
        List<Resource> newBonus  = new ArrayList<>();
        for(Resource resource : exchange.getBonus()) {
            EventReceiveResource e = new EventReceiveResource(player, resource);
            try {
                match.getEffectHandler().executeEffects(match, e);
            } catch (ActionDeniedException e1) {
                throw new IllegalStateException("Discard denied by effect");
            }
            newBonus.add(e.getResource());
        }
        player.addResources(newBonus);
    }

    @Override
    public String toString() {
        return exchange.toString();
    }
}
