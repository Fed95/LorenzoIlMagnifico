package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.event.EventRequiredValueNotSatisfied;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.occupiables.Tower;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;

import java.io.IOException;
import java.util.List;

public class FreeAction extends ActionPlaceOnTower {

    public FreeAction(FamilyMember familyMember, Tower tower, TowerFloor towerFloor) {
        super(familyMember, tower, towerFloor);
    }

    @Override
    public void start(Match match) throws RuntimeException, IOException {
    	Player player = match.getBoard().getTrackTurnOrder().getCurrentPlayer();
        Event event = new EventPlaceFamilyMember(player, towerFloor, familyMember);

        //Can throw exceptions (in which case effects are discarded directly in EffectHandler)
        List<Effect> executedEffects = player.getEffectHandler().executeEffects(event);
        try {
            this.canBeExecuted();
            /*TODO: WAITING FOR JSON
            CardDevelopment card = towerFloor.getCard();
            player.getPersonalBoard().placeCard(card);
            towerFloor.removeCard();
            executeImmediateEffects(player, card);
            */
        }
        catch(RequiredValueNotSatisfiedException e) {
            Event eventException = new EventRequiredValueNotSatisfied(player, towerFloor, familyMember);
            match.notifyObserver(eventException);
        }catch(Exception e) {
            player.getEffectHandler().discardEffects(executedEffects, event);
            System.out.println("effects discarded");
            throw e;
        }
    }
}
