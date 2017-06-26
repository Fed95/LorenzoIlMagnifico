package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.*;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.occupiables.SpaceMarket;
import it.polimi.ingsw.gc_12.occupiables.SpaceWork;
import it.polimi.ingsw.gc_12.occupiables.Tower;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;

import java.util.ArrayList;
import java.util.List;

public class EventFamilyMemberChosen extends Event {

	private FamilyMember familyMember;
	
	public EventFamilyMemberChosen(Player player, FamilyMember familyMember) {
		super(player);
		this.familyMember = familyMember;

		effectProviders.addAll(player.getCards());
		effectProviders.addAll(player.getExcommunications());
	}
	
	public EventFamilyMemberChosen(FamilyMember familyMember) {
		super();
		this.familyMember = familyMember;
	}

	public EventFamilyMemberChosen() {
		super();
	}

	@Override
	public void setActions(ActionHandler actionHandler, Match match) {
		actions = new ArrayList<>();
		//Adds the towers with at least one valid floor TODO: ADD CHECK FOR MARKET AND WORK (or remove for tower)
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

	@Override
	public List<Object> getAttributes() {
		List<Object> attributes = new ArrayList<>();
		attributes.add(familyMember);
		return attributes;
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
