package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.*;
import it.polimi.ingsw.gc_12.java_fx.MainBoard;
import javafx.application.Platform;

import java.util.ArrayList;

public class EventDiscardAction extends Event implements EventView{

    public EventDiscardAction(Player player) {
        super(player);
    }

    @Override
    public void setActions(ActionHandler actionHandler, Match match) {
        actions = new ArrayList<>();

        if(!actionHandler.hasPlaced()) {
            for (FamilyMember familyMember : player.getAvailableFamilyMembers()) {
                actions.add(new ActionChooseFamilyMember(player, familyMember));
            }
        }
        if(player.getPlayableLeaderCards().size() > 0)
            actions.add(new ActionViewPlayableLeaderCards(player));
        if(player.getAvailableLeaderCards().size() > 0)
            actions.add(new ActionViewAvailableLeaderCards(player));
        if(player.getPersonalBoard().getLeaderCards().size() > 0)
            actions.add(new ActionViewDiscardableLeaderCards(player));
        actions.add(new ActionPassTurn(player));
        actions.add(new ActionRequestStatistics(player));
    }

    @Override
    public String toString() {
        return "Action discarded";
    }

    @Override
    public void executeViewSide(MainBoard view) {
        Platform.runLater(() -> view.getControllerMainBoard().sendAction());
    }
}
