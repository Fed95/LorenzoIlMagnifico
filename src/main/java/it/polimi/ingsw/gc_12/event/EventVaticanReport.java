package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.excommunication.ExcommunicationTile;

import java.util.List;

public class EventVaticanReport extends Event {

    private ExcommunicationTile tile;

    public EventVaticanReport(Player player, ExcommunicationTile tile) {
        super(player);
        this.tile = tile;
    }

    public ExcommunicationTile getTile() {
        return tile;
    }

    @Override
    public List<Object> getAttributes() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public String toStringClient() {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------------------").append(System.getProperty("line.separator"));
        sb.append("----------\\\\ VATICAN REPORT //----------").append(System.getProperty("line.separator"));
        sb.append("----------------------------------------").append(System.getProperty("line.separator"));
        return sb.toString();
    }
}
