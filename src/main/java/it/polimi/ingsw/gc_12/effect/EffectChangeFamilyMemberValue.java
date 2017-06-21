package it.polimi.ingsw.gc_12.effect;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.FamilyMemberColor;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;

import java.util.Map;

public class EffectChangeFamilyMemberValue extends Effect{
	
	private int amount;
	
	public EffectChangeFamilyMemberValue(Event event, int amount) {
		super(event);
		this.amount = amount;
	}
	
	public void execute(Match match, Event event) {
		if(event instanceof EventPlaceFamilyMember) {
			EventPlaceFamilyMember eventSpecific = (EventPlaceFamilyMember) event;
			//System.out.println("inside first if");
			FamilyMember familyMember = eventSpecific.getFamilyMember();
			//System.out.println(familyMember.hashCode());
			changeFamilyMemberValue(familyMember, amount);
		}
		else {
			Map<FamilyMemberColor, FamilyMember> familyMembers = event.getPlayer().getFamilyMembers();
			//System.out.println("inside first else");
			for(FamilyMember familyMember: familyMembers.values()) {
				changeFamilyMemberValue(familyMember, amount);
			}
		}
	}

	public void discard(Event event) {
		if(event instanceof EventPlaceFamilyMember) {
			EventPlaceFamilyMember eventSpecific = (EventPlaceFamilyMember) event;
			FamilyMember familyMember = eventSpecific.getFamilyMember();
			changeFamilyMemberValue(familyMember, -amount);
		}
		else {
			Map<FamilyMemberColor, FamilyMember> familyMembers = event.getPlayer().getFamilyMembers();
			for(FamilyMember familyMember: familyMembers.values()) {
				changeFamilyMemberValue(familyMember, -amount);
			}
		}
	}
	
	private void changeFamilyMemberValue(FamilyMember familyMember, int amount) {
		int oldValue = familyMember.getValue();
		//System.out.println(oldValue);
		familyMember.setValue(oldValue + amount);
		//System.out.println(familyMember.getValue());
	}

	@Override
	public String toString() {
		return event.getClass().getSimpleName() + ": Adds " + amount + " to the family member's value";
	}
}
