package it.polimi.ingsw.gc_12.personalBoard;

import it.polimi.ingsw.gc_12.resource.ResourceType;

public class ResourceContainer {
    private ResourceType type;
    private int quantity;

    public ResourceContainer(ResourceType type) {
        this.type = type;
        quantity = 0;
    }
}
