package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.EventActionUnavailable;
import it.polimi.ingsw.gc_12.event.EventFamilyMemberChosen;


public class ActionChooseFamilyMember extends Action{

	protected FamilyMember familyMember;

	public ActionChooseFamilyMember(Player player, FamilyMember familyMember) {
		super(player);
		this.familyMember = familyMember;
	}

	public FamilyMember getFamilyMember() {
		return familyMember;
	}

	@Override
	public boolean isValid(Match match){
		return false;
	}

	@Override
	public void start(Match match) {
		if (familyMember.isBusy()) {
			match.notifyObserver(new EventActionUnavailable(false)); // TODO: check if isFMPlaced in this event is useful
		}
		EventFamilyMemberChosen event = new EventFamilyMemberChosen(player, familyMember);
		match.getActionHandler().update(event);
		//Notifies the ServerRMIView
		match.notifyObserver(event);
	}

	@Override
	public String toString() {
		return "Place "+familyMember;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ActionChooseFamilyMember)) return false;

		ActionChooseFamilyMember that = (ActionChooseFamilyMember) o;

		return familyMember != null ? familyMember.equals(that.familyMember) : that.familyMember == null;
	}

	@Override
	public int hashCode() {
		return familyMember != null ? familyMember.hashCode() : 0;
	}
}
