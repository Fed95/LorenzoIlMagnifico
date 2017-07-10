package it.polimi.ingsw.gc12.model.player.personalboard;

import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.effect.EffectProvider;
import it.polimi.ingsw.gc12.model.player.resource.Resource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representing a card owned by the player
 */
public class CardSlot implements EffectProvider, Serializable{
    private CardDevelopment card;
    private List<Resource> requisites = new ArrayList<>();
    private List<Effect> effects = new ArrayList<>();

    public CardSlot(List<Resource> requisites, List<Effect> effects){
        this.requisites = requisites;
        this.effects = effects;
    }

    public CardSlot(){
    }

    public void placeCard(CardDevelopment card){
        this.card = card;
    }

    public CardDevelopment getCard(){
        return card;
    }

    public boolean isEmpty(){
        if(card == null)
            return true;
        return false;
    }

    public List<Resource> getRequisites(){
        return requisites;
    }

    @Override
    public List<Effect> getEffects() {
        return null;
    }
}
