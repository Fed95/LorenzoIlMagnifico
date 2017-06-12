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
import it.polimi.ingsw.gc_12.resource.ResourceType;
import it.polimi.ingsw.gc_12.resource.Servant;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;

import java.rmi.RemoteException;

public class ActionPlace extends Action{

	protected FamilyMember familyMember;
	protected Servant servant;

	public ActionPlace(FamilyMember familyMember, Servant servant) {
		super();
		this.familyMember = familyMember;
		this.servant = servant;
	}

	public ActionPlace(FamilyMember familyMember) {
		this(familyMember, new Servant(0));
	}

	public FamilyMember getFamilyMember() {
		return familyMember;
	}

	@Override
	public void start(Match match) throws RuntimeException, RequiredValueNotSatisfiedException, IOException, RemoteException {
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

    protected Integer getServants(Match match) {
		Integer ownedServants =  match.getBoard().getTrackTurnOrder().getCurrentPlayer().getResourceValue(ResourceType.SERVANT);
		if(servant.getValue() > ownedServants)
			return ownedServants;
		return servant.getValue();
	}
}
