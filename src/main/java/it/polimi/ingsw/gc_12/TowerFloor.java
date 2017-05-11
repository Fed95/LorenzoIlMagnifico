package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.resource.Resource;

public class TowerFloor {
	private final int floorNum;
	private final int value;
	private final Resource[] resources;
	private Card card;
	private FamilyMember occupier;

	
	public TowerFloor(int floorNum, int value, Resource[] resources){
		this.floorNum = floorNum;
		this.value = value;
		this.resources = resources;
	}
		
	public TowerFloor(int floorNum, int value){
		this(floorNum, value, null);
	}

	public Resource[] getResources() {
		return resources;
	}

	public int getValue() {
		return value;
	}

	public int getFloorNum() {
		return floorNum;
	}

	public boolean isOccupied() {
		return occupier != null;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public FamilyMember getOccupier() {
		return occupier;
	}

	public void setOccupier(FamilyMember occupier) {
		this.occupier = occupier;
	}

}
