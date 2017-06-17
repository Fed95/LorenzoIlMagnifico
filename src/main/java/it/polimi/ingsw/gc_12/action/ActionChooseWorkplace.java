package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.EventWorkplaceChosen;


public class ActionChooseWorkplace extends Action {

    private FamilyMember familyMember;

    public ActionChooseWorkplace(Player player, FamilyMember familyMember) {
        super(player);
        this.familyMember = familyMember;
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        EventWorkplaceChosen event = new EventWorkplaceChosen(player, familyMember);
        match.getActionHandler().update(event);
        //Notifies the ServerRMIView
        match.notifyObserver(event);
    }

    @Override
    public String toString() {
        return "Workplace";
    }
}
