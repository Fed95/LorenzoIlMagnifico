package it.polimi.ingsw.gc12.model.effect;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventFreeAction;
import it.polimi.ingsw.gc12.model.board.occupiable.Occupiable;
import it.polimi.ingsw.gc12.model.player.resource.Resource;

import java.util.ArrayList;
import java.util.List;


public class EffectFreeAction extends Effect {


    private List<Occupiable> occupiables;
    private List<Resource> discounts = new ArrayList<>();
    private FamilyMember familyMember;
    private String description;
    int value;

    public EffectFreeAction(Event event, List<Occupiable> occupiables, int value) {
        super(event);
        this.occupiables = occupiables;
        this.value = value;
    }

    public EffectFreeAction(Event event, List<Occupiable> occupiables, int value, List<Resource> discounts) {
        this(event, occupiables, value);
        this.discounts = discounts;
    }

    public EffectFreeAction(Event event, List<Occupiable> occupiables, int value, String description) {
        this(event, occupiables, value);
        this.description = description;
    }

    @Override
    public void execute(Match match, Event event, boolean validation) {
        if(!validation) {
            this.familyMember = new FamilyMember(match.getBoard().getTrackTurnOrder().getCurrentPlayer(), value);
            List<Occupiable> realOccupiables = new ArrayList<>();
            for (Occupiable occupiable : this.occupiables) {
                for (Occupiable matchOccupiable : match.getBoard().getOccupiables()) {
                    if (matchOccupiable.equals(occupiable))
                        realOccupiables.add(matchOccupiable);
                }
            }
            Event eventFreeAction = new EventFreeAction(match.getBoard().getTrackTurnOrder().getCurrentPlayer(), familyMember, realOccupiables);
            if (discounts != null && discounts.size() > 0)
                ((EventFreeAction) eventFreeAction).setDiscounts(discounts);
            match.getActionHandler().update(eventFreeAction, match);
            match.notifyObserver(eventFreeAction);
        }
    }

    @Override
    public void discard(Match match, Event event) {
        //TODO: CHECK THIS
    }

    @Override
    public String toString() {
        if(description == null)
            return event.getClass().getSimpleName() + ": " + this.getClass().getSimpleName() + " of value " + value;
        return description;
    }
}
