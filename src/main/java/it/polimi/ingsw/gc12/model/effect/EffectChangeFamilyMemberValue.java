package it.polimi.ingsw.gc12.model.effect;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.event.EventPlaceFamilyMember;

import java.util.List;
import java.util.Map;

public class EffectChangeFamilyMemberValue extends Effect{
	
	private int amount;
	private List<FamilyMemberColor> colors;
	private boolean setValue;
	private Map<FamilyMemberColor, FamilyMember> familyMembers;
	
	public EffectChangeFamilyMemberValue(Event event, int amount) {
		super(event);
		this.amount = amount;
		this.setValue = false;
	}

	//Used in Json
	public EffectChangeFamilyMemberValue(Event event, int amount, List<FamilyMemberColor> colors, boolean setValue) {
		super(event);
		this.amount = amount;
		this.colors = colors;
		this.setValue = setValue;
	}

	public void execute(Match match, Event event, boolean validation) {
		applyChange(event, amount);
	}

	public void discard(Match match, Event event) {
		applyChange(event, -amount);
	}

	private void applyChange(Event event, int amount) {
		if(event instanceof EventPlaceFamilyMember) {
			EventPlaceFamilyMember eventSpecific = (EventPlaceFamilyMember) event;
			FamilyMember familyMember = eventSpecific.getFamilyMember();
			if(setValue)
				familyMember.setValue(amount);
			else
				changeFamilyMemberValue(familyMember, amount);
		}
		else {
			familyMembers = event.getPlayer().getFamilyMembers();
			for(FamilyMemberColor color : familyMembers.keySet()){
				if (colors.contains(color))
					if(setValue) {
						familyMembers.get(color).setValue(amount);
					}
					else
						changeFamilyMemberValue(familyMembers.get(color), amount);
			}
		}
	}
	
	private void changeFamilyMemberValue(FamilyMember familyMember, int amount) {
		int oldValue = familyMember.getValue();
		familyMember.setValue(oldValue + amount);
	}

	public int getAmount() {
		return amount;
	}

	public List<FamilyMemberColor> getColors() {
		return colors;
	}

	public boolean isSetValue() {
		return setValue;
	}

	@Override
	public String toString() {
		return event + ": Adds " + amount + " to the family member's value";
	}
}
