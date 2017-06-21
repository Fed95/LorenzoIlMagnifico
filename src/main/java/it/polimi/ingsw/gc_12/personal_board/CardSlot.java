package it.polimi.ingsw.gc_12.personal_board;

import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.resource.Resource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CardSlot implements EffectProvider, Serializable{
    private CardDevelopment card;
    private List<Resource> requisites = new ArrayList<>();
    private List<Effect> effects = new ArrayList<>();

    public CardSlot(List<Resource> requisites, List<Effect> effects){
        this.requisites = requisites;
        this.effects = effects;
    }

    public CardSlot(){
        this(null, null);
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
