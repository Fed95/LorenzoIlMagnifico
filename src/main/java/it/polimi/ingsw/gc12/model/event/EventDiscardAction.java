package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.action.*;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.view.client.gui.MainBoard;
import javafx.application.Platform;

import java.util.ArrayList;

public class EventDiscardAction extends Event implements EventView{

    public EventDiscardAction(Player player) {
        super(player);
    }

    @Override
    public void setActions(Match match) {
        actions = new ArrayList<>();

        if(!match.getActionHandler().hasPlaced()) {
            for (FamilyMember familyMember : player.getAvailableFamilyMembers()) {
                actions.add(new ActionChooseFamilyMember(player, familyMember));
            }
        }
        if(player.getPlayableLeaderCards().size() > 0)
            actions.add(new ActionViewPlayableLeaderCards(player));
        if(player.getAvailableLeaderCards().size() > 0)
            actions.add(new ActionViewAvailableLeaderCards(player));
        if(player.getPersonalBoard().getLeaderCardsSpace().getCards().size() > 0)
            actions.add(new ActionViewDiscardableLeaderCards(player));
        if(match.getActionHandler().hasPlaced())
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
