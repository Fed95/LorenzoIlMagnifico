package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.event.EventPlayLeaderCard;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.card.LeaderCard;
import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.effect.EffectCopyLeaderCard;
import it.polimi.ingsw.gc12.model.event.EventActivateLeaderCard;
import it.polimi.ingsw.gc12.model.event.EventStartTurn;
import it.polimi.ingsw.gc12.misc.exception.ActionDeniedException;

import java.util.ArrayList;
import java.util.List;

public class ActionPlayLeaderCard extends Action {

    private LeaderCard card;
    List<Effect> executedEffects = new ArrayList<>();

    public ActionPlayLeaderCard(Player player, LeaderCard card) {
        super(player);
        this.card = card;
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        player.getPersonalBoard().getLeaderCardsSpace().getLeaderCard(card.getId()).setPlayed(true);
        executedEffects = new ArrayList<>();
        if(card.isPermanent())
            try {
                executedEffects = match.getEffectHandler().executeEffects(match, new EventActivateLeaderCard(player, card));
            } catch (ActionDeniedException e) {
                e.printStackTrace();
            }

        for(Effect effect : executedEffects)
            if(effect instanceof EffectCopyLeaderCard)
                return;

        EventPlayLeaderCard event = new EventPlayLeaderCard(player, card.getId());
        match.getActionHandler().update(event, match);
        match.notifyObserver(event);
        /*EventStartTurn event = new EventStartTurn(player);

        //Notifies the ServerRMIView
        match.notifyObserver(event);*/
    }

    //Used for testing
    public List<Effect> getExecutedEffects() {
        return executedEffects;
    }

    @Override
    public String toString() {
        return card.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionPlayLeaderCard)) return false;

        ActionPlayLeaderCard that = (ActionPlayLeaderCard) o;

        return card.equals(that.card);
    }

    @Override
    public int hashCode() {
        return card.hashCode();
    }
}
