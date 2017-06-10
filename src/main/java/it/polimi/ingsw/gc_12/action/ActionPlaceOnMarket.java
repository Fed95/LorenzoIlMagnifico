package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.occupiables.SpaceMarket;

public class ActionPlaceOnMarket extends ActionPlace {

    private SpaceMarket spaceMarket;

    public ActionPlaceOnMarket(FamilyMember familyMember, SpaceMarket spaceMarket) {
        super(familyMember);
        this.spaceMarket = spaceMarket;
    }

    public boolean canBeExecuted(Player player, Event event) throws RuntimeException {
        player.getEffectHandler().executeEffects(event);

        if(spaceMarket.isOccupied())
                throw new RuntimeException("This SpaceMarket is already taken!");
        if(!spaceMarket.isRequiredValueSatisfied(familyMember))
            throw new RuntimeException("Your FamilyMember does not satisfy the required value for this placement!");
        return true;
    }

    @Override
    public void start(Match match) throws RuntimeException {
    	Player player = match.getBoard().getTrackTurnOrder().getCurrentPlayer();
        Event event = new EventPlaceFamilyMember(player, spaceMarket, familyMember);

        if(canBeExecuted(player, event)) {
            familyMember.setBusy(true);
            spaceMarket.placeFamilyMember(familyMember);
        }else
            player.getEffectHandler().discardEffects(event);
    }

    private SpaceMarket getRealSpaceMarket(Match match){
        return match.getBoard().getMarket().getSpaceMarkets().get(spaceMarket.getMarketNum());
    }
}
