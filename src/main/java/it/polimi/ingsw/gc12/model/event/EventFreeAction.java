package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.action.*;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.board.occupiable.Occupiable;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.view.client.gui.MainBoard;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;


/**
 * Presents the player with all the placement actions related to the specified occupiables
 * (excluding the ones the player cannot perform in the moment the action is created)
 */
public class EventFreeAction extends Event implements EventView{

    private FamilyMember familyMember;
    private List<Occupiable> occupiables;
    private List<Resource> discounts = new ArrayList<>();

    public EventFreeAction(Player player, FamilyMember familyMember, List<Occupiable> occupiables, List<Action> actions) {
        super(player);
        this.familyMember = familyMember;
        this.occupiables = occupiables;
        this.actions = actions;
    }

    public EventFreeAction(Player player, FamilyMember familyMember, List<Occupiable> occupiables, List<Action> actions, List<Resource> discounts) {
        this(player, familyMember, occupiables, actions);
        if(discounts != null)
            this.discounts = discounts;
    }

    public EventFreeAction(Player player, FamilyMember familyMember, List<Occupiable> occupiables){
        this(player, familyMember, occupiables, new ArrayList<>());
    }

    public void setDiscounts(List<Resource> discounts){
        this.discounts = discounts;
    }

    @Override
    public void setActions(Match match) {
        actions = new ArrayList<>();
        for(Occupiable occupiable: occupiables) {
            ActionPlace action = ActionFactory.createActionPlace(player, familyMember, occupiable, discounts);
            if(action.isValid(match))
                actions.add(action);
        }
        actions.add(new DiscardAction(player));
    }

    public List<Occupiable> getOccupiables() {
        return occupiables;
    }

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    public List<Resource> getDiscounts() {
        return discounts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("line.separator"));
        sb.append(player.getName()+ " has received a free action!").append(System.getProperty("line.separator"));
        if(discounts.size() > 0)
            sb.append("(This free action comes with a discount: " + discounts + " )");
        return sb.toString();
    }

    @Override
    public void executeViewSide(MainBoard view) {
        Platform.runLater(() -> view.getControllerMainBoard().notifyFreeAction());

    }
}
