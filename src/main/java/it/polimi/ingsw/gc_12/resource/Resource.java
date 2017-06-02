package it.polimi.ingsw.gc_12.resource;

public abstract class Resource {
	private final ResourceType type;
	private int value;
	
	public Resource(ResourceType type, int value){
		this.value = value;
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public ResourceType getType(){
		return type;
	}

	@Override
	public String toString() {
		return type + ": " + value;
	}
}
