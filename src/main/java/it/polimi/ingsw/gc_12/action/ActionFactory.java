package it.polimi.ingsw.gc_12.action;

import java.rmi.RemoteException;

import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.occupiables.*;
import it.polimi.ingsw.gc_12.resource.Servant;

public class ActionFactory {

	public static ActionPlace createActionPlace(Player player, FamilyMember familyMember, Occupiable occupiable, Servant servant) {
		if(occupiable instanceof TowerFloor) {
			return new ActionPlaceOnTower(player, familyMember,(TowerFloor) occupiable, servant);
		}else if(occupiable instanceof SpaceWork){
			return new ActionPlaceOnSpaceWork(player, familyMember, (SpaceWork) occupiable, servant);
		}else if(occupiable instanceof SpaceMarket){
			return new ActionPlaceOnMarket(player, familyMember, (SpaceMarket) occupiable, servant);
		}else if(occupiable instanceof CouncilPalace){
			return new ActionPlaceOnCouncil(player, familyMember, (CouncilPalace) occupiable, servant);
		}
		else
			return null;
	}

	public static ActionPlace createActionPlace(Player player, FamilyMember familyMember, Occupiable occupiable) {
		return createActionPlace(player, familyMember, occupiable, new Servant(0));
	}
}
