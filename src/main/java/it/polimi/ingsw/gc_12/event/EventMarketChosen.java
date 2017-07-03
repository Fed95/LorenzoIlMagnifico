package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.*;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.java_fx.MainBoard;
import it.polimi.ingsw.gc_12.occupiables.SpaceMarket;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class EventMarketChosen extends Event implements EventView{

    private FamilyMember familyMember;

    public EventMarketChosen(Player player, FamilyMember familyMember) {
        super(player);
        this.familyMember = familyMember;
    }

    @Override
    public void setActions(ActionHandler actionHandler, Match match) {
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
