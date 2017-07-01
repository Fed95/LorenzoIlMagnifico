package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.LeaderCard;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectCopyLeaderCard;
import it.polimi.ingsw.gc_12.effect.EffectSetFamilyMemberValue;
import it.polimi.ingsw.gc_12.event.EventActivateLeaderCard;
import it.polimi.ingsw.gc_12.event.EventDiscardAction;
import it.polimi.ingsw.gc_12.event.EventStartTurn;
import it.polimi.ingsw.gc_12.exceptions.ActionDeniedException;

public class ActionActivateLeaderCard extends Action {

    private LeaderCard card;

    public ActionActivateLeaderCard(Player player, LeaderCard card) {
        super(player);
        this.card = card;
    }

    @Override
    public boolean isValid(Match match) {
        return false;
    }

    @Override
    public void start(Match match) {
        try {
            match.getEffectHandler().executeEffects(match, new EventActivateLeaderCard(player, card));
        } catch (ActionDeniedException e) {
            e.printStackTrace();
        }
        card.setActivated(true);

        for(Effect effect : card.getEffects())
            if(effect instanceof EffectSetFamilyMemberValue || effect instanceof EffectCopyLeaderCard)
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
