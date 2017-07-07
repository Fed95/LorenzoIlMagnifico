package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.action.*;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.view.client.gui.MainBoard;
import it.polimi.ingsw.gc12.model.board.occupiable.SpaceMarket;
import javafx.application.Platform;

import java.util.ArrayList;

public class EventMarketChosen extends Event implements EventView{

    private FamilyMember familyMember;

    public EventMarketChosen(Player player, FamilyMember familyMember) {
        super(player);
        this.familyMember = familyMember;
    }

    @Override
    public void setActions(Match match) {
        actions = new ArrayList<>();
        for(SpaceMarket spaceMarket : match.getBoard().getMarket().getSpaceMarkets()){
            ActionPlace action = ActionFactory.createActionPlace(player, familyMember, spaceMarket);
            if(action.isValid(match))
                actions.add(new ActionPlaceOnMarket(player, familyMember, spaceMarket));
        }
        actions.add(new DiscardAction(player));
    }

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    @Override
    public String toString() {
        return player.getName() + " is viewing the Market";
    }

    @Override
    public void executeViewSide(MainBoard view) {
        Platform.runLater(() -> {
            view.getControllerMainBoard().sendAction();
        });
    }
}
