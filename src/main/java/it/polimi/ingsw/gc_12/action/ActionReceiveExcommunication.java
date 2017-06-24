package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.excommunication.ExcommunicationTile;

/**
 * Created by feder on 2017-06-24.
 */
public class ActionReceiveExcommunication extends Action {

    private ExcommunicationTile tile;

    public ActionReceiveExcommunication(Player player, ExcommunicationTile tile) {
        super(player);
        this.tile = tile;
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        player.getExcommunications().add(tile);
        match.countReport();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Deny your support to the church").append(System.getProperty("line.separator"));
        sb.append("    you will receive the following Excommunication: ").append(System.getProperty("line.separator"));
        sb.append(tile).append(System.getProperty("line.separator")).append(System.getProperty("line.separator"));
        return sb.toString();
    }
}
