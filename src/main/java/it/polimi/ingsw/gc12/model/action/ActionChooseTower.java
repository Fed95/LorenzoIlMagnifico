package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.event.EventTowerChosen;
import it.polimi.ingsw.gc12.model.board.occupiable.Tower;

/**
 * This action has been implemented to improve the CLI experience.
 * When a player selects this action he is presented with a new set of actions
 * regarding the placements he can perform on the specified tower
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
        match.getActionHandler().update(event, match);
        //Notifies the ServerRMIView
        match.notifyObserver(event);
    }

    @Override
    public String toString() {
        return tower.getType() + " Tower";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionChooseTower)) return false;

        ActionChooseTower that = (ActionChooseTower) o;

        return tower != null ? tower.equals(that.tower) : that.tower == null;
    }

    @Override
    public int hashCode() {
        return tower != null ? tower.hashCode() : 0;
    }
}
