package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;

import java.util.List;

public class EventChooseCost extends Event {

    private ResourceExchange militaryExchange;
    private List<Resource> requirements;

    public EventChooseCost(Player player, ResourceExchange militaryExchange, List<Resource> requirements) {
        super(player);
        this.militaryExchange = militaryExchange;
        this.requirements = requirements;
    }

    public EventChooseCost(ResourceExchange militaryExchange, List<Resource> requirements) {
        this.militaryExchange = militaryExchange;
        this.requirements = requirements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("line.separator"));
        sb.append("How would you like to pay for this card?");
        return sb.toString();
    }}
