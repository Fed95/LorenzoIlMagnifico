package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventActionUnavailable;
import it.polimi.ingsw.gc_12.event.EventChooseFamilyMember;
import it.polimi.ingsw.gc_12.resource.ResourceType;
import it.polimi.ingsw.gc_12.resource.Servant;

import java.io.IOException;

/**
 * Created by marco on 14/06/2017.
 */
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
	public boolean isValid(Match match) throws IOException {
		return false;
	}

	@Override
	public void start(Match match) throws IOException {
		if (familyMember.isBusy()) {
			match.notifyObserver(new EventActionUnavailable(false)); // TODO: check if isFMPlaced in this event is useful
		}
		Event event = new EventChooseFamilyMember(familyMember);
		//Notifies the RMIView
		match.notifyObserver(event);
	}


}
