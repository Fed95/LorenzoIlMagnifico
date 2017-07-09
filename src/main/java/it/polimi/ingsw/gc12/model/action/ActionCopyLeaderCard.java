package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.card.LeaderCard;
import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.event.EventActivateLeaderCard;
import it.polimi.ingsw.gc12.model.event.EventStartTurn;

/**
 * When the player activates a LeaderCard with EffectCopyLeaderCard, he is presented with a set of ActionCopyLeaderCard.
 * Each action refers to a LeaderCard the player chan choose to copy.
 * When executed the action replaces all effects of the owned LeaderCard with the ones of the chosen card.
 * Furthermore it sets the boolean "permanent" of the owned card to the one of the selected card.
 */
public class ActionCopyLeaderCard extends Action {

    private LeaderCard myCard;
    private LeaderCard otherCard;

    public ActionCopyLeaderCard(Player player, LeaderCard myCard, LeaderCard otherCard) {
        super(player);
        if(myCard.equals(otherCard))
            throw new IllegalStateException("A card cannot copy itself");
        this.myCard = myCard;
        this.otherCard = otherCard;
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        myCard.getRequirements().clear();
        myCard.getCardRequirements().clear();
        myCard.getEffects().clear();

        myCard.getRequirements().addAll(otherCard.getRequirements());
        myCard.getCardRequirements().putAll(otherCard.getCardRequirements());
        myCard.getEffects().addAll(otherCard.getEffects());
        handleId();

        myCard.setPermanent(otherCard.isPermanent());
        myCard.setPlayed(true);

        EventStartTurn event = new EventStartTurn(player);
        match.getActionHandler().update(event, match);
        //Notifies the ServerRMIView
        match.notifyObserver(event);
    }

    private void handleId(){
        for(Effect effect : myCard.getEffects())
            if(effect.getEvent() instanceof EventActivateLeaderCard){
                EventActivateLeaderCard event = (EventActivateLeaderCard) effect.getEvent();
                event.setCardId(myCard.getId());
            }
    }

    @Override
    public String toString() {
        return otherCard.toString();
    }
}
