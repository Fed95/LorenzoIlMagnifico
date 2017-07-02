package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.LeaderCard;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.event.EventActivateLeaderCard;
import it.polimi.ingsw.gc_12.event.EventStartTurn;

public class ActionCopyLeaderCard extends Action {

    private LeaderCard myCard;
    private LeaderCard otherCard;

    public ActionCopyLeaderCard(Player player, LeaderCard myCard, LeaderCard otherCard) {
        super(player);
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
