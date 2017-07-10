package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.action.*;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.board.occupiable.SpaceMarket;
import it.polimi.ingsw.gc12.model.board.occupiable.SpaceWork;
import it.polimi.ingsw.gc12.model.board.occupiable.Tower;
import it.polimi.ingsw.gc12.model.board.occupiable.TowerFloor;

import java.util.ArrayList;


/**
 * Contains ChooseTower, ChooseMarket, ChooseWorkplace and PlaceOnCouncil (+ DiscardAction) actions.
 * When no floor of the tower is available, or no SpaceMarket, or no Workplace, the related Choose action is not created
 */
public class EventFamilyMemberChosen extends Event {

	private FamilyMember familyMember;
	
	public EventFamilyMemberChosen(Player player, FamilyMember familyMember) {
		super(player);
		this.familyMember = familyMember;
	}
	
	public EventFamilyMemberChosen(FamilyMember familyMember) {
		super();
		this.familyMember = familyMember;
	}

	public EventFamilyMemberChosen() {
		super();
	}

	@Override
	public void setActions(Match match) {
		actions = new ArrayList<>();
		for(Tower tower : match.getBoard().getTowerSet().getTowers().values()){
			for(TowerFloor towerFloor : tower.getFloors()){
				ActionPlace action = ActionFactory.createActionPlace(player, familyMember, towerFloor);
				if(action.isValid(match)) {
					actions.add(new ActionChooseTower(player, familyMember, tower));
					break;
				}
			}
		}
		for(SpaceMarket spaceMarket : match.getBoard().getMarket().getSpaceMarkets()){
			ActionPlace action = ActionFactory.createActionPlace(player, familyMember, spaceMarket);
			if(action.isValid(match)) {
				actions.add(new ActionChooseMarket(player, familyMember));
				break;
			}
		}
		for(SpaceWork spaceWork : match.getBoard().getSpaceWorks()){
			ActionPlace action = ActionFactory.createActionPlace(player, familyMember, spaceWork);
			if(action.isValid(match)) {
				actions.add(new ActionChooseWorkplace(player, familyMember));
				break;
			}
		}
		actions.add(new ActionPlaceOnCouncil(player, familyMember, match.getBoard().getCouncilPalace()));
		actions.add(new DiscardAction(player));
	}

	public FamilyMember getFamilyMember() {
		return familyMember;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(System.getProperty("line.separator"));
		sb.append(player.getName() + " has chosen the " + familyMember.getColor() + " Family Member (value " + familyMember.getValue() + ")");
		return sb.toString();
	}
}
