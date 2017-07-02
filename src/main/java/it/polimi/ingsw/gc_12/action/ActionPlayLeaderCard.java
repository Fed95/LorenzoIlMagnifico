package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.LeaderCard;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectCopyLeaderCard;
import it.polimi.ingsw.gc_12.event.EventActivateLeaderCard;
import it.polimi.ingsw.gc_12.event.EventDiscardAction;
import it.polimi.ingsw.gc_12.event.EventStartTurn;
import it.polimi.ingsw.gc_12.exceptions.ActionDeniedException;

import java.util.ArrayList;
import java.util.List;

public class ActionPlayLeaderCard extends Action {

    private LeaderCard card;

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
        player.getPersonalBoard().getLeaderCards().get(player.getPersonalBoard().getLeaderCards().indexOf(card)).setPlayed(true);
        List<Effect> executedEffects = new ArrayList<>();
        if(card.isPermanent())
            try {
                executedEffects = match.getEffectHandler().executeEffects(match, new EventActivateLeaderCard(player, card));
            } catch (ActionDeniedException e) {
                e.printStackTrace();
            }

        for(Effect effect : executedEffects)
            if(effect instanceof EffectCopyLeaderCard)
                return;

        EventStartTurn event = new EventStartTurn(player);
        match.getActionHandler().update(event, match);
        //Notifies the ServerRMIView
        match.notifyObserver(event);
    }

    @Override
    public String toString() {
        return card.toString();
    }
}
