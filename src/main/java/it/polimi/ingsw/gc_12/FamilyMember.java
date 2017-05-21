package it.polimi.ingsw.gc_12;
import java.util.Observable;
import java.util.Observer;

public class FamilyMember implements Observer {
	
	private final Player owner;
	private FamilyMemberColor color;
	private int value;
	
	public FamilyMember(Player owner, FamilyMemberColor color) {
		
		this.owner = owner;
		this.color = color;
		try {
			
			DieColor dieColor = DieColor.valueOf(color.name());
			// Check if there is a die with the same color of the family member's one 
			// (exclude neutral family member)
			Die die = SpaceDie.instance().getDie(dieColor);
			if(die != null) {
				this.value = die.getValue();
				
				// To update the family member's value when the dice have been rolled
				die.addObserver(this);
			}
		}catch (IllegalArgumentException e) {
			this.value = 0;
		}	
	}
	
	public FamilyMemberColor getColor(){
		return color;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	//Receive notification that dices have been rolled and update value
	@Override
	public void update(Observable o, Object value) {
		this.setValue((int) value);		
	}

	public Player getOwner() {
		return owner;
	}
	
}
