package it.polimi.ingsw.gc_12;
import java.util.Observable;
import java.util.Observer;

public class FamilyMember implements Observer {
	private FamilyMemberColor color;
	private int value = 0;
	
	public FamilyMember(FamilyMemberColor color) {
		DieColor dieColor = DieColor.valueOf(color.name());
		
		// Check if there is a die with the same color of the family member's one 
		// (exclude neutral family member)
		Die die = SpaceDie.instance().getDie(dieColor);
		if(die != null) {
			this.value = die.getValue();
			
			// To update the family member's value when the dice have been rolled
			die.addObserver(this);
		}
		
		this.color = color;
		
		
	}
	
	public FamilyMember() {
		super();
		this.color = null;
	}
	
	public FamilyMemberColor getColor(){
		return color;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public void update(Observable o, Object value) {
		this.setValue((int) value);		
	}
	
}
