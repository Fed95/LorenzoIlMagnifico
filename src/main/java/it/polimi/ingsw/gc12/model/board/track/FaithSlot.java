package it.polimi.ingsw.gc12.model.board.track;

import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.VictoryPoint;

public class FaithSlot {

    private int victoryPoints;

    public FaithSlot(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public Resource getVictoryPoints() {
        return new VictoryPoint(victoryPoints);
    }
}
