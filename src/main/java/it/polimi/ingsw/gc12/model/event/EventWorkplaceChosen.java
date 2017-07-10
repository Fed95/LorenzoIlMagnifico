package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.action.*;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.view.client.gui.MainBoard;
import it.polimi.ingsw.gc12.model.board.occupiable.SpaceWork;
import it.polimi.ingsw.gc12.model.board.occupiable.SpaceWorkZone;
import javafx.application.Platform;

import java.util.ArrayList;

/**
 * Generated from an ActionChooseWorkplace, contains all the possible placement actions on the SpaceWorks
 */
public class EventWorkplaceChosen extends Event implements EventView{

    private FamilyMember familyMember;

    public EventWorkplaceChosen(Player player, FamilyMember familyMember) {
        super(player);
        this.familyMember = familyMember;
    }

    @Override
    public void setActions(Match match) {
        actions = new ArrayList<>();
        for(SpaceWorkZone spaceWorkZone : match.getBoard().getSpaceWorkZones().values()){
            for(SpaceWork spaceWork : spaceWorkZone.getSpaceWorks()){
                ActionPlace action = ActionFactory.createActionPlace(player, familyMember, spaceWork);
                if(action.isValid(match))
                    actions.add(new ActionPlaceOnSpaceWork(player, familyMember, spaceWork));
            }
        }
        actions.add(new DiscardAction(player));
    }

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    @Override
    public String toString() {
        return player.getName() + " is viewing the Workplace";
    }

    @Override
    public void executeViewSide(MainBoard view) {
        Platform.runLater(() -> {
            view.getControllerMainBoard().sendAction();
        });
    }
}
