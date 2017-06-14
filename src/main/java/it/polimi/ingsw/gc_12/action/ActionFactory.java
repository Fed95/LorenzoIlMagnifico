package it.polimi.ingsw.gc_12.action;

import java.rmi.RemoteException;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.MatchInstance;
import it.polimi.ingsw.gc_12.MatchRemote;
import it.polimi.ingsw.gc_12.occupiables.*;
import it.polimi.ingsw.gc_12.resource.Servant;

public class ActionFactory {

	public static ActionPlace createActionPlace(Occupiable occupiable, FamilyMember familyMember, Servant servant) {
		if(occupiable instanceof TowerFloor) {
			return new ActionPlaceOnTower(familyMember, servant, (TowerFloor) occupiable);
		}else if(occupiable instanceof SpaceWork){
			return new ActionPlaceOnSpaceWork(familyMember, servant, (SpaceWork) occupiable);
		}else if(occupiable instanceof SpaceMarket){
			return new ActionPlaceOnMarket(familyMember, servant, (SpaceMarket) occupiable);
		}else if(occupiable instanceof CouncilPalace){
			return new ActionPlaceOnCouncil(familyMember, servant,  (CouncilPalace) occupiable);
		}
		else
			return null;
	}

	public static ActionPlace createActionPlace(Occupiable occupiable, FamilyMember familyMember) {
		return createActionPlace(occupiable, familyMember, new Servant(0));
	}
}
