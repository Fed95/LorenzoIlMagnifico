package it.polimi.ingsw.gc_12;
import it.polimi.ingsw.gc_12.dice.Die;
import it.polimi.ingsw.gc_12.dice.DieColor;
import it.polimi.ingsw.gc_12.dice.SpaceDie;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class FamilyMember implements Observer, Serializable {
	
	private Player owner;
	private PlayerColor ownerColor;
	private FamilyMemberColor color;
	private int value;
	private boolean busy;
	
	public FamilyMember(Player owner, FamilyMemberColor color, int value) {
		this.owner = owner;
		this.ownerColor = owner.getColor();
		this.color = color;
		this.value = value;
		this.busy = false;
		try {
			if(color != null && value == 0) {
				DieColor dieColor = DieColor.valueOf(color.name());
				// Check if there is a die with the same color of the family member's one
				// (exclude neutral family member)
				Die die = SpaceDie.instance().getDie(dieColor);
				if(die != null) {
					this.value = die.getValue();

					// To update the family member's value when the dice have been rolled
					die.addObserver(this);
				}
			}

		}catch (IllegalArgumentException e) {
			this.value = 0;
		}	
	}

	public FamilyMember(Player owner, FamilyMemberColor color) {
		this(owner, color, 0);
	}

	public FamilyMember(PlayerColor ownerColor, FamilyMemberColor color) {
		this(new Player(ownerColor), color);
	}

	public FamilyMember(Player owner, int value) {
		this(owner, null, value);
	}

	public FamilyMember(FamilyMemberColor color, int value) {
		this.color = color;
		this.value = value;
	}

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


	//Receive notification that the dice have been rolled and update value
	@Override
	public void update(Observable o, Object value) {
		this.setValue((int) value);		
	}

	public Player getOwner() {
		if(owner == null) {
			throw new IllegalArgumentException("Trying to get the owner of a Family member without a owner");
		}
		else
			return owner;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("FamilyMember [");
		if(owner != null)
			sb.append(" Owner: " + owner.getName());
		if(ownerColor != null)
			sb.append(" OwnerColor: " + ownerColor);
		if(color != null)
			sb.append(" Color: " + color);

		sb.append(" busy: " + isBusy() + " ]");
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return true;
		else {
			FamilyMember other = (FamilyMember) obj;
			// The attributes of two family members are the same if at least one is null or they have the same value
			// Two family members are equals if they have the same color and the same owner
			if(this.getColor() == null || other.getColor() == null || this.getColor() == other.getColor()) {
				if(this.getOwner() == null || other.getOwner() == null || this.getOwner() == other.getOwner())
					return true;
			}
			return false;
		}
	}
}
