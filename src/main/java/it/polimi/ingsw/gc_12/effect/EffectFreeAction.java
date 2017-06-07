package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.event.Event;


public class EffectFreeAction extends Effect {

    private CardType type;
    private int value;

    public EffectFreeAction(Event event, CardType type, int value) {
        super(event);
        this.type = type;
        this.value = value;
    }

    @Override
    public void execute(Event event) throws RuntimeException {
        Player player = event.getPlayer();
        //player.getMatch().getControllerPlayer().freeAction(type, value);
    }

    @Override
    public void discard(Event event) throws RuntimeException {

    }
}
