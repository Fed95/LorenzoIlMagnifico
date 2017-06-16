package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

import java.util.List;

/**
 * Created by feder on 2017-06-16.
 */
public class EventViewStatistics extends Event {

    private Player chosenPlayer;

    public EventViewStatistics(Player player, Player chosenPlayer) {
        super(player);
        this.chosenPlayer = chosenPlayer;
    }

    @Override
    public List<Object> getAttributes() {
        return null;
    }

    @Override
    public List<EffectProvider> getEffectProviders() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("line.separator"));
        sb.append("Viewing statistics of " + chosenPlayer.getName() + ":").append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("Resources:").append(System.getProperty("line.separator"));
        sb.append(chosenPlayer.printResources()).append(System.getProperty("line.separator"));
        sb.append("Excommunications:").append(System.getProperty("line.separator"));
        sb.append(chosenPlayer.getExcommunications()).append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("Cards:").append(System.getProperty("line.separator"));
        sb.append(chosenPlayer.getCards());
        return sb.toString();
    }
}
