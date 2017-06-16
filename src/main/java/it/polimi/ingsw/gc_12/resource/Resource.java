package it.polimi.ingsw.gc_12.resource;

import java.io.Serializable;

public abstract class Resource implements Serializable {
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
		StringBuilder sb = new StringBuilder();
		sb.append(type + ": " + value).append(System.getProperty("line.separator"));
		return sb.toString();
	}
}
