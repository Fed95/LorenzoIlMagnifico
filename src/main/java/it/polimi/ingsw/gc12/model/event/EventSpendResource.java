package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;


public class EventSpendResource extends Event {

    private ResourceType resourceType;
    private int value;

    public EventSpendResource(Player player, Resource resource) {
        super(player);
        this.resourceType = resource.getType();
        this.value = resource.getValue();
    }

    public EventSpendResource(Resource resource) {
        super();
        this.resourceType = resource.getType();
        this.value = resource.getValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        EventSpendResource other = (EventSpendResource) obj;

        if(this.resourceType.equals(other.resourceType))
            return true;
        return false;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("line.separator"));
        sb.append(player.getName() + " spent some " + resourceType + "! In the amount of " + value);
        return sb.toString();
    }
}
