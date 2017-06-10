package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.occupiables.CouncilPalace;

public class ActionPlaceOnCouncil extends ActionPlace {

    private CouncilPalace councilPalace;

    public ActionPlaceOnCouncil(FamilyMember familyMember, CouncilPalace councilPalace) {
        super(familyMember);
        this.councilPalace = councilPalace;
    }

    public boolean canBeExecuted(Player player, Event event) throws RequiredValueNotSatisfiedException {
        player.getEffectHandler().executeEffects(event);

        if (!councilPalace.isRequiredValueSatisfied(familyMember))
            throw new RequiredValueNotSatisfiedException();
        return true;
    }

    @Override
    public void start(Match match) throws RequiredValueNotSatisfiedException {
        System.out.println("ActionPlaceOnCouncil: starting...");
        Player player = match.getBoard().getTrackTurnOrder().getCurrentPlayer();
    	familyMember = getRealFamilyMember(match);
    	councilPalace = getRealCouncilPalace(match);
        Event event = new EventPlaceFamilyMember(player, councilPalace, familyMember);
        System.out.println("Created the event.");
        if (canBeExecuted(player, event)) {
            System.out.println("Event can be executed.");
            familyMember.setBusy(true);
            councilPalace.placeFamilyMember(familyMember);
            System.out.println("FamilyMember placed!");
        }else {
            player.getEffectHandler().discardEffects(event);
            System.out.println("Event cannot be executed.");
        }
    }

    private CouncilPalace getRealCouncilPalace(Match match) {
        return match.getBoard().getCouncilPalace();
    }
}