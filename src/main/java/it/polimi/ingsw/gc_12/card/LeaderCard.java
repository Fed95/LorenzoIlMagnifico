package it.polimi.ingsw.gc_12.card;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.resource.Resource;

import java.util.List;
import java.util.Map;

public class LeaderCard extends Card {

    Map<CardType, Integer> cardRequirements;
    private boolean active = false;

    public LeaderCard(int id, String name, List<Resource> requirements, Map<CardType, Integer> cardRequirements, List<Effect> effects) {
        super(id, name, requirements, effects);
        this.cardRequirements = cardRequirements;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Map<CardType, Integer> getCardRequirements() {
        return cardRequirements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" " + name + "    (Active: " + active + ")").append(System.getProperty("line.separator"));
        sb.append("		Resource Requirements: " + requirements).append(System.getProperty("line.separator"));
        sb.append("		Card Requirements: " + cardRequirements).append(System.getProperty("line.separator"));
        sb.append("		Effects: ").append(System.getProperty("line.separator"));
        for(Effect effect : effects)
            sb.append("         - " + effect).append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }
}
