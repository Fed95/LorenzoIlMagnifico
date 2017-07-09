package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.event.EventMarketChosen;

/**
 * This action has been implemented to improve the CLI experience.
 * When a player selects this action he is presented with a new set of actions
 * regarding the placements he can perform on the market
 */
public class ActionChooseMarket extends Action {

    private FamilyMember familyMember;

    public ActionChooseMarket(Player player, FamilyMember familyMember) {
        super(player);
        this.familyMember = familyMember;
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        EventMarketChosen event = new EventMarketChosen(player, familyMember);
        match.getActionHandler().update(event, match);
        //Notifies the ServerRMIView
        match.notifyObserver(event);
    }

    @Override
    public String toString() {
        return "Market";
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof ActionChooseMarket;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
