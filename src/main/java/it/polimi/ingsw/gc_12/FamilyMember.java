package it.polimi.ingsw.gc_12;
import it.polimi.ingsw.gc_12.dice.Die;
import it.polimi.ingsw.gc_12.dice.DieColor;
import it.polimi.ingsw.gc_12.dice.SpaceDie;

import java.util.Observable;
import java.util.Observer;

public class FamilyMember implements Observer {
	
	private final Player owner;
	private FamilyMemberColor color;
	private int value;
	boolean busy;
	
	public FamilyMember(Player owner, FamilyMemberColor color, int value) {
		this.owner = owner;
		this.color = color;
		this.value = value;
		this.busy = false;
		try {
			if(color != null) {
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

	public FamilyMember(FamilyMemberColor color, int value) {
		this(null, color, value);
	}

	public FamilyMember(FamilyMemberColor color) {
		this(null, color);
	}

	public FamilyMember(int value) {
		this(null, null, value);
	}

	public FamilyMember() {
		this.owner = null;
	}
	
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
		this.busy = busy;
	}


	//Receive notification that the dice have been rolled and update value
	@Override
	public void update(Observable o, Object value) {
		this.setValue((int) value);		
	}


	public Player getOwner() {
		return owner;
	}

	@Override
	public String toString() {
		return "FamilyMember [Owner: " +getOwner().getName()+ ", color: " + color + ", value: " + value + ", busy: " + isBusy() + "]";
	}
}
