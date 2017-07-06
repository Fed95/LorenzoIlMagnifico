package it.polimi.ingsw.gc12.model.effect;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventActivateLeaderCard;
import it.polimi.ingsw.gc12.model.event.EventSetFamilyMemberValue;
import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;

/**
 * Created by feder on 2017-06-30.
 */
public class EffectSetFamilyMemberValue extends Effect {

    private int value;

    public EffectSetFamilyMemberValue(Event event, int value) {
        super(event);
        this.value = value;
    }

    @Override
    public void execute(Match match, Event e, boolean validation) throws ActionDeniedException {
        if(!validation) {
            if (!(e instanceof EventActivateLeaderCard))
                throw new IllegalStateException();
            EventSetFamilyMemberValue event = new EventSetFamilyMemberValue(e.getPlayer(), value);
            match.getActionHandler().update(event, match);
            match.notifyObserver(event);
        }
    }

    @Override
    public void discard(Match match, Event event) {

    }

    @Override
    public String toString() {
        return "Set a family member's value to " + value;
    }
}
