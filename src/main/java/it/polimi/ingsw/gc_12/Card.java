package it.polimi.ingsw.gc_12;

public class Card {
	private final String name;
	private Resource[] requirements;
	

	public Card(String name, Resource[] requirements){
		this.name = name;
		this.requirements = requirements;
	}


	public String getName() {
		return name;
	}


	public Resource[] getRequirements() {
		return requirements;
	}
}
