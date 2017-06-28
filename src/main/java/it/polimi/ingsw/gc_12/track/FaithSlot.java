package it.polimi.ingsw.gc_12.track;

import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.VictoryPoint;

public class FaithSlot {

    private int victoryPoints;

    public FaithSlot(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public Resource getVictoryPoints() {
        return new VictoryPoint(victoryPoints);
    }
}
