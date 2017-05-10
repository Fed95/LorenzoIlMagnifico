package it.polimi.ingsw.gc_12;

public class Tower {
	private final CardType type;
	private final TowerFloor[] floors;

	public Tower(CardType type, int[] floorValues){
		this.type = type;
		floors = new TowerFloor[4];
		floors[0] = new TowerFloor(0, floorValues[0]);
		floors[1] = new TowerFloor(1, floorValues[1]);
		floors[2] = new TowerFloor(2, floorValues[2]);
		floors[3] = new TowerFloor(3, floorValues[3]);
	}

	public TowerFloor[] getFloors() {
		return floors;
	}

	public CardType getType() {
		return type;
	}

}
