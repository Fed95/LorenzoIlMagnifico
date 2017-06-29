package it.polimi.ingsw.gc_12.card;

import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.resource.Resource;

import java.util.List;
import java.util.Map;

public class LeaderCard extends Card {

    Map<CardType, Integer> cardRequirements;

    public LeaderCard(int id, String name, List<Resource> requirements, Map<CardType, Integer> cardRequirements, List<Effect> effects) {
        super(id, name, requirements, effects);
        this.cardRequirements = cardRequirements;
    }


}
