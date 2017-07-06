package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.action.ActionChooseExchange;
import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.view.client.gui.MainBoard;
import it.polimi.ingsw.gc12.model.player.resource.ResourceExchange;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class EventChooseExchange extends Event implements EventView {

    private List<ResourceExchange> exchanges;

    public EventChooseExchange(Player player, List<ResourceExchange> exchanges) {
        super(player);
        this.exchanges = exchanges;
    }

    @Override
    public void setActions(ActionHandler actionHandler, Match match) {
        actions = new ArrayList<>();
        for (ResourceExchange exchange : exchanges)
            actions.add(new ActionChooseExchange(player, exchange));
    }

    public List<ResourceExchange> getExchanges() {
        return exchanges;
    }

    @Override
    public String toString() {
        return "Choose the exchange you would like to activate";
    }

    @Override
    public void executeViewSide(MainBoard view) {
        Platform.runLater(() -> view.getControllerMainBoard().chooseExchange(exchanges));
    }
}
