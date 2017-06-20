package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.EventTowerChosen;
import it.polimi.ingsw.gc_12.occupiables.Tower;

/**
 * Created by feder on 2017-06-17.
 */
public class ActionChooseTower extends Action {

    private Tower tower;
    private FamilyMember familyMember;

    public ActionChooseTower(Player player, FamilyMember familyMember, Tower tower) {
        super(player);
        this.familyMember = familyMember;
        this.tower = tower;
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        EventTowerChosen event = new EventTowerChosen(player, familyMember, tower);
        match.getActionHandler().update(event);
        //Notifies the ServerRMIView
        match.notifyObserver(event);
    }

    @Override
    public String toString() {
        return tower.getType() + " Tower";
    }
}
