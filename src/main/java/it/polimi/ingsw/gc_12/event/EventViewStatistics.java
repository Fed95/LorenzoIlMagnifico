package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

import java.util.List;

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

    public Player getChosenPlayer() {
        return chosenPlayer;
    }

    @Override
    public String toString() {
        return player.getName() + " is viewing " + chosenPlayer.getName() + "'s stats.";
    }
}
