package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.occupiables.*;
import it.polimi.ingsw.gc_12.resource.Servant;

public class ActionFactory {

	public static ActionPlace createActionPlace(Player player, FamilyMember familyMember, Occupiable occupiable, Servant servant, boolean complete) {
		if(occupiable instanceof TowerFloor) {
			return new ActionPlaceOnTower(player, familyMember,(TowerFloor) occupiable, servant, complete);
		}else if(occupiable instanceof SpaceWork){
			return new ActionPlaceOnSpaceWork(player, familyMember, (SpaceWork) occupiable, servant, complete);
		}else if(occupiable instanceof SpaceMarket){
			return new ActionPlaceOnMarket(player, familyMember, (SpaceMarket) occupiable, servant, complete);
		}else if(occupiable instanceof CouncilPalace){
			return new ActionPlaceOnCouncil(player, familyMember, (CouncilPalace) occupiable, servant, complete);
		}
		else
			return null;
	}

	public static ActionPlace createActionPlace(Player player, FamilyMember familyMember, Occupiable occupiable) {
		return createActionPlace(player, familyMember, occupiable, new Servant(0), false);
	}
}
