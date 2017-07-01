package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.EventStartTurn;

public class ActionSetFamilyMemberValue extends Action {

    private FamilyMember familyMember;
    private int value;

    public ActionSetFamilyMemberValue(Player player, FamilyMember familyMember, int value) {
        super(player);
        this.familyMember = familyMember;
        this.value = value;
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        familyMember.setValue(value);

        EventStartTurn event = new EventStartTurn(player);
        match.getActionHandler().update(event, match);
        //Notifies the ServerRMIView
        match.notifyObserver(event);
    }

    @Override
    public String toString() {
        return "Set to " + value + " the value of: " + familyMember;
    }
}
