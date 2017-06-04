package it.polimi.ingsw.gc_12.effect;

import java.util.Map;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.FamilyMemberColor;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;

public class EffectChangeFamilyMemberValue extends Effect{
	
	private int amount;
	
	public EffectChangeFamilyMemberValue(Event event, int amount) {
		super(event);
		this.amount = amount;
	}
	
	public void execute(Event event) {
		if(event instanceof EventPlaceFamilyMember) {
			EventPlaceFamilyMember eventSpecific = (EventPlaceFamilyMember) event;
			//System.out.println("inside first if");
			FamilyMember familyMember = eventSpecific.getFamilyMember();
			//System.out.println(familyMember.hashCode());
			changeFamilyMemberValue(familyMember, amount);
		}
		else {
			Map<FamilyMemberColor, FamilyMember> familyMembers = event.getPlayer().getFamilymembers();
			//System.out.println("inside first else");
			for(FamilyMember familyMember: familyMembers.values()) {
				changeFamilyMemberValue(familyMember, amount);
			}
		}
	}

	//TODO: souldn't the amount be subtracted?
	public void discard(Event event) {
		if(event instanceof EventPlaceFamilyMember) {
			EventPlaceFamilyMember eventSpecific = (EventPlaceFamilyMember) event;
			FamilyMember familyMember = eventSpecific.getFamilyMember();
			changeFamilyMemberValue(familyMember, amount);
		}
		else {
			Map<FamilyMemberColor, FamilyMember> familyMembers = event.getPlayer().getFamilymembers();
			for(FamilyMember familyMember: familyMembers.values()) {
				changeFamilyMemberValue(familyMember, amount);
			}
		}
	}
	
	private void changeFamilyMemberValue(FamilyMember familyMember, int amount) {
		int oldValue = familyMember.getValue();
		//System.out.println(oldValue);
		familyMember.setValue(oldValue + amount);
		//System.out.println(familyMember.getValue());
	}
}
