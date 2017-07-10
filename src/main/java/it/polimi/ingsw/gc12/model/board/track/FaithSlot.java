package it.polimi.ingsw.gc12.model.board.track;

import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.VictoryPoint;

/**
 * This class represent the single slot of the faith points track, it gaves you victory points
 */
public class FaithSlot {

    private int victoryPoints;

    /**
     * Constructor
     * @param victoryPoints victory points gained from the slot
     */
    public FaithSlot(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public Resource getVictoryPoints() {
        return new VictoryPoint(victoryPoints);
    }
}
