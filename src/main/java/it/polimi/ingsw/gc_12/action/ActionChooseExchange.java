package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;

/**
 * Created by feder on 2017-06-20.
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
        player.addResources(exchange.getBonus());
    }
}
