package it.polimi.ingsw.gc12.model.player.resource;

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
		sb.append(type + ": " + value);
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Resource)) return false;

		Resource resource = (Resource) o;

		if (value != resource.value) return false;
		return type == resource.type;
	}

	@Override
	public int hashCode() {
		int result = type.hashCode();
		result = 31 * result + value;
		return result;
	}
}
