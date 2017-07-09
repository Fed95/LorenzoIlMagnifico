package it.polimi.ingsw.gc12.model.action;

import it.polimi.ingsw.gc12.model.board.occupiable.*;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.Servant;

import java.util.List;

/**
 * This class generates the correct ActionPlace based on the instance of the Occupiable it receives
 */
public class ActionFactory {

	public static ActionPlace createActionPlace(Player player, FamilyMember familyMember, Occupiable occupiable, Servant servant, boolean complete, List<Resource> discounts) {
		if(occupiable instanceof TowerFloor) {
			return new ActionPlaceOnTower(player, familyMember,(TowerFloor) occupiable, servant, complete, discounts);
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
		return createActionPlace(player, familyMember, occupiable, new Servant(0), false, null);
	}

	public static ActionPlace createActionPlace(Player player, FamilyMember familyMember, Occupiable occupiable, List<Resource> discounts) {
		return createActionPlace(player, familyMember, occupiable, new Servant(0), false, discounts);
	}
}
