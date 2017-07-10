package it.polimi.ingsw.gc12.model.player.familymember;

import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * This class represent the family member of the player with all his characteristics
 */
public class FamilyMember implements Observer, Serializable {
	
	private Player owner;
	private PlayerColor ownerColor;
	private FamilyMemberColor color;
	private int value;
	private boolean busy;
	private boolean friendly = false;

    /**
     * Constructor
     * @param owner player owner of the family member
     * @param color family memeber color
     * @param value value of the family member
     */
	public FamilyMember(Player owner, FamilyMemberColor color, int value) {
		this.owner = owner;
		this.ownerColor = owner.getColor();
		this.color = color;
		this.value = value;
		this.busy = false;
	}

    /**
     * Constructor with default value 0
     * @param owner player owner
     * @param color color of the family
     */
	public FamilyMember(Player owner, FamilyMemberColor color) {
		this(owner, color, 0);
	}

    /**
     * Constructor with player color instead of the player
     * @param ownerColor player color
     * @param color family member color
     */
	public FamilyMember(PlayerColor ownerColor, FamilyMemberColor color) {
		this(new Player(ownerColor), color);
	}

    /**
     * Constructor of a family member with no color
     * @param owner
     * @param value
     */
	public FamilyMember(Player owner, int value) {
		this(owner, null, value);
	}

    /**
     * Constructor of a family member with no owner
     * @param color family member color
     * @param value value
     */
	public FamilyMember(FamilyMemberColor color, int value) {
		this.color = color;
		this.value = value;
	}
    /**
     * Constructor of a family member with no owner with 0 value
     * @param color family member color
     */
	public FamilyMember(FamilyMemberColor color) {
		this(color, 0);
	}

	public FamilyMember(int value) {
		this.value = value;
	}

	public FamilyMember() {}
	
	public FamilyMemberColor getColor(){
		return color;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}

	public boolean isBusy(){
		return busy;
	}

	public void setBusy(boolean busy) {
		if(owner == null)
			throw new IllegalArgumentException("Setting the attribute busy to a family member without owner");
		else if(color == null)
			throw new IllegalArgumentException("Setting the attribute busy to a family member without color");
		this.busy = busy;
	}

	public boolean isFriendly() {
		return friendly;
	}

	public void setFriendly(boolean friendly) {
		this.friendly = friendly;
	}

	//Receive notification that the dice have been rolled and update value
	@Override
	public void update(Observable o, Object value) {
		this.value = (int)value;
	}

	public Player getOwner() {
		return owner;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("FamilyMember [");
		/*
		if(owner != null)
			sb.append(" Owner: " + owner.getName() + ",");
		if(ownerColor != null)
			sb.append(" OwnerColor: " + ownerColor + ",");
		*/
		if(color != null)
			sb.append(" Color: " + color + ",");

		sb.append(" Value: " + value + ", busy: " + isBusy() + " ]");
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FamilyMember)) return false;

		FamilyMember that = (FamilyMember) o;

		// The attributes of two family members are the same if at least one is null or they have the same value
		// Two family members are equals if they have the same color and the same owner
		if(this.getColor() == null || that.getColor() == null || this.getColor().equals(that.getColor())) {
			if(this.getOwner() == null || that.getOwner() == null || this.getOwner().equals(that.getOwner()))
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = ownerColor != null ? ownerColor.hashCode() : 0;
		result = 31 * result + (color != null ? color.hashCode() : 0);
		return result;
	}
}
