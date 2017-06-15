package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.ActionHandler;
import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventActionUnavailable;
import it.polimi.ingsw.gc_12.event.EventChooseFamilyMember;

import java.util.List;

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
	public void start(Match match){
		if (familyMember.isBusy()) {
			match.notifyObserver(new EventActionUnavailable(false)); // TODO: check if isFMPlaced in this event is useful
		}
		EventChooseFamilyMember event = new EventChooseFamilyMember(player, familyMember);
		List<Action> actions = match.getActionHandler().update(event);
		event.setActions(actions);
		//Notifies the RMIView
		match.notifyObserver(event);
	}

	@Override
	public String toString() {
		return "Place family member "+familyMember;
	}
}
