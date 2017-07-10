package it.polimi.ingsw.gc12.model.effect;

import it.polimi.ingsw.gc12.model.board.occupiable.TowerFloor;
import it.polimi.ingsw.gc12.model.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventPickCard;
import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;

/**
 * Sets "noRequisites: true" in the card. In this way when the "canPlaceOnPersonalBoard "check is executed,
 * the player does not have to satisfy the requisites set by the personal board to take the card
 */

public class EffectNoRequisites extends Effect{

    private String description;

    public EffectNoRequisites(Event event, String description) {
        super(event);
        this.description = description;
    }

    @Override
    public void execute(Match match, Event event, boolean validation) throws ActionDeniedException {
        applyChange(event, true);
    }

    @Override
    public void discard(Match match, Event event) {
        applyChange(event, false);
     }

    private void applyChange(Event event, boolean bool){
        if(!(event instanceof EventPlaceFamilyMember))
            throw new IllegalStateException();
        if(!(((EventPlaceFamilyMember) event).getOccupiable() instanceof TowerFloor))
            throw new IllegalStateException();
        ((TowerFloor) ((EventPlaceFamilyMember) event).getOccupiable()).getCard().setNoRequisites(bool);
    }

    @Override
    public String toString() {
        return description;
    }
}
