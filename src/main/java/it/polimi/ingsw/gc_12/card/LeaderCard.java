package it.polimi.ingsw.gc_12.card;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.resource.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaderCard extends Card {

    private Map<CardType, Integer> cardRequirements = new HashMap<>();
    private boolean played = false;
    private boolean activated = false;
    private boolean anyCard = false;
    private boolean permanent;
    private int numOfRequiredCards;


    public LeaderCard(int id, String name, List<Resource> requirements, Map<CardType, Integer> cardRequirements, List<Effect> effects, boolean permanent) {
        super(id, name, requirements, effects);
        if(cardRequirements != null)
            this.cardRequirements = cardRequirements;
        this.permanent = permanent;
    }

    public LeaderCard(int id, String name, List<Resource> requirements, int numOfRequiredCards, List<Effect> effects, boolean permanent) {
        this(id, name, requirements, null, effects, permanent);
        this.anyCard = true;
        this.numOfRequiredCards = numOfRequiredCards;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public boolean isAnyCard() {
        return anyCard;
    }

    public int getNumOfRequiredCards() {
        return numOfRequiredCards;
    }

    public Map<CardType, Integer> getCardRequirements() {
        return cardRequirements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" " + name + "    (Played: " + played + " | Permanent: " + permanent + " | Activated: " + activated + ")").append(System.getProperty("line.separator"));
        sb.append("		Resource Requirements: " + requirements).append(System.getProperty("line.separator"));
        sb.append("		Card Requirements: " + cardRequirements).append(System.getProperty("line.separator"));
        sb.append("		Effects: ").append(System.getProperty("line.separator"));
        for(Effect effect : effects)
            sb.append("         - " + effect).append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }
}
