package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.resource.Resource;

public class TowerFloor extends Occupiable{
	private final int floorNum;
	private final int value;
	private final Resource[] resources;
	private Card card;
	
	
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

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
}
