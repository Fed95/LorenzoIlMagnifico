package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.event.EventReceiveResource;
import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceExchange;

import java.util.ArrayList;
import java.util.List;

/**
 * The action is related to a specific ResourceExchange.
 * When the player selects this action the exchange is executed
 * which means the cost of the exchange will be removed from the player's resources and the bonus will be added.
 * During the process an EventReceiveResource is generated for every bonus.
 * This allows EffectProviders with effects related to this event to alter the bonus
 */

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
        match.addResources(player, newBonus);
    }

    @Override
    public String toString() {
        return exchange.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionChooseExchange)) return false;

        ActionChooseExchange that = (ActionChooseExchange) o;

        return exchange != null ? exchange.equals(that.exchange) : that.exchange == null;
    }

    @Override
    public int hashCode() {
        return exchange != null ? exchange.hashCode() : 0;
    }
}
