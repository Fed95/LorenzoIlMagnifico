package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.EffectProvider;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.excommunication.ExcommunicationTile;

import java.util.List;

public class EventReceiveExcommunication extends Event {
    private ExcommunicationTile tile;

    public EventReceiveExcommunication(Player player, ExcommunicationTile tile){
        this.player = player;
        this.tile = tile;

        effectProviders.addAll(Match.instance().getBoard().getExcommunicationSpace().getTiles().values());
    }
    @Override
    public List<Object> getAttributes() {
        return this.getAttributes();
    }

    @Override
    public List<EffectProvider> getEffectProviders() {
        return null;
    }
}
