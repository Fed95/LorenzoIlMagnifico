package it.polimi.ingsw.gc_12.personal_board;

import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceType;

public class ResourceContainer {

    private ResourceType type;
    private Resource resource;

    public ResourceContainer(ResourceType type) {
        this.type = type;
    }

    public void setResource(Resource resource){
        this.resource = resource;
    }
}
