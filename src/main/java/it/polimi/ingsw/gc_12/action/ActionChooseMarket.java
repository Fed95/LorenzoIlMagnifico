package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.EventMarketChosen;

import java.io.IOException;

/**
 * Created by feder on 2017-06-18.
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
    public void start(Match match) throws IOException {
        EventMarketChosen event = new EventMarketChosen(player, familyMember);
        match.getActionHandler().update(event);
        //Notifies the ServerRMIView
        match.notifyObserver(event);
    }

    @Override
    public String toString() {
        return "Market";
    }
}
