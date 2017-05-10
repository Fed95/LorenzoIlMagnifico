package it.polimi.ingsw.gc_12;
import java.util.Observable;
import java.util.Observer;

public class FamilyMember extends ResourceGeneric implements Observer {
	private DieColor color;
	
	public FamilyMember(Die die) {
		super(die.getValue());
		color = die.getColor();
		die.addObserver(this);
	}
	
	public FamilyMember() {
		super(0);
		this.color = null;
	}
	
	public DieColor getColor(){
		return color;
	}

	
	@Override
	public void update(Observable o, Object value) {
		this.setValue((int) value);		
	}
	
}
