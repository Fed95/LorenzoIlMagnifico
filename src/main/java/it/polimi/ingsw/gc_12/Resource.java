package it.polimi.ingsw.gc_12;

public class Resource extends ResourceGeneric{
	private ResourceType type;
	
	public Resource(ResourceType type, int value){
		super(value);
		this.type = type;
	}
	
	ResourceType getType(){
		return type;
	}

}
