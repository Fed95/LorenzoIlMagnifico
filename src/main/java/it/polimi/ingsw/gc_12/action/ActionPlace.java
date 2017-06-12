package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import java.io.IOException;
import java.io.Serializable;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventChooseFamilyMember;
import it.polimi.ingsw.gc_12.exceptions.RequiredValueNotSatisfiedException;
import it.polimi.ingsw.gc_12.resource.Servant;

public class ActionPlace extends Action{

	protected FamilyMember familyMember;

	public ActionPlace(FamilyMember familyMember) {
		super();
		this.familyMember = familyMember;
	}

	public FamilyMember getFamilyMember() {
		return familyMember;
	}

	@Override
	public void start(Match match) throws RuntimeException, RequiredValueNotSatisfiedException, IOException {
		FamilyMember realFamilyMember = match.getBoard().getTrackTurnOrder().getCurrentPlayer().getFamilyMember(familyMember.getColor());
		if (realFamilyMember.isBusy())
			throw new RuntimeException("This FamilyMember is already busy!");
		Event event = new EventChooseFamilyMember(familyMember);
		//Notifies the RMIView
		match.notifyObserver(event);
	}
	
	protected FamilyMember getRealFamilyMember(Match match){
    	return match.getBoard().getTrackTurnOrder().getCurrentPlayer().getFamilyMember(familyMember.getColor());
    }
}
