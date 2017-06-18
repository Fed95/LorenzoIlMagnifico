package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.occupiables.*;
import it.polimi.ingsw.gc_12.resource.ResourceType;
import it.polimi.ingsw.gc_12.resource.Servant;

import java.util.ArrayList;
import java.util.List;

public class ActionHandler /*implements Observer<Event> */{
	private List<Action> actions = new ArrayList<>();
	private Match match;

	public ActionHandler(Match match) {
		this.match = match;
		//match.registerObserver(this);
	}

	/*public List<Action> updateAvailableActions(Match match, Player player, FamilyMember familyMember, Servant servant) {
		actions = new ArrayList<>();
		for(Occupiable occupiable: match.getBoard().getOccupiables()) {
			ActionPlace action = ActionFactory.createActionPlace(player, familyMember, occupiable, servant);
			if(action.isValid(match)) {
				actions.add(action);
			}
		}
		return actions;
	}

	public List<Action> updateAvailableActions(Match match, Player player, FamilyMember familyMember) {
		return updateAvailableActions(match, player, familyMember, new Servant(0));
	}*/

	public List<Action> getAvailableActions() {
		return actions;
	}


	public List<Action> update(Event event) {
		if(event instanceof EventFamilyMemberChosen) {
			actions = getActionsFamilyMemberChoosen((EventFamilyMemberChosen) event);
		}
		else if(event instanceof EventRequiredValueNotSatisfied) {
			actions = getActionsRequiredValue((EventRequiredValueNotSatisfied) event);
		}
		else if(event instanceof EventStartTurn || event instanceof EventPlaceFamilyMember || event instanceof EventDiscardAction || event instanceof EventDiscardPlacement) {
			actions = getActionsStartTurn(event);
		}
		else if(event instanceof EventRequestStatistics){
			actions = getActionsRequestStatistics(event);
		}
		else if(event instanceof EventViewStatistics){
			actions = getActionsViewStatistics(event);
		}
		else if(event instanceof EventTowerChosen){
			actions = getActionsTowerChosen((EventTowerChosen) event);
		}
		else if(event instanceof EventWorkplaceChosen){
			actions = getActionsWorkplaceChosen((EventWorkplaceChosen) event);
		}
		else if(event instanceof EventMarketChosen){
			actions = getActionsMarketChosen((EventMarketChosen) event);
		}

		event.setActions(actions);
		return actions;
	}



	public List<Action> getActionsStartTurn(Event event) {
		Player player = event.getPlayer();
		List<Action> actions = new ArrayList<>();
		if(event instanceof EventStartTurn || event instanceof EventDiscardPlacement) {
			for(FamilyMember familyMember: player.getAvailableFamilyMembers()) {
				actions.add(new ActionChooseFamilyMember(player, familyMember));
			}
		}
		actions.add(new ActionPassTurn(player));
		actions.add(new ActionRequestStatistics(player));
		return actions;
	}

	public List<Action> getActionsFamilyMemberChoosen(EventFamilyMemberChosen event) {
		Player player = event.getPlayer();
		List<Action> actions = new ArrayList<>();
		//Adds the towers with at least one valid floor TODO: ADD CHECK FOR MARKET AND WORK
		for(Tower tower : match.getBoard().getTowerSet().getTowers().values()){
			for(TowerFloor towerFloor : tower.getFloors()){
				ActionPlace action = ActionFactory.createActionPlace(player, event.getFamilyMember(), towerFloor);
				if(action.isValid(match)) {
					actions.add(new ActionChooseTower(player, event.getFamilyMember(), tower));
					break;
				}
			}
		}
		/*
		for(Occupiable occupiable: match.getBoard().getOccupiables()) {
			if(!(occupiable instanceof TowerFloor)){
				ActionPlace action = ActionFactory.createActionPlace(player, event.getFamilyMember(), occupiable);
				if(action.isValid(match))
					actions.add(action);
			}
		}
		*/
		actions.add(new ActionChooseMarket(player, event.getFamilyMember()));
		actions.add(new ActionChooseWorkplace(player, event.getFamilyMember()));
		actions.add(new ActionPlaceOnCouncil(player, event.getFamilyMember(), match.getBoard().getCouncilPalace()));
		actions.add(new DiscardPlacement(player));
		return actions;
	}

	public List<Action> getActionsRequestStatistics(Event event) {
		Player player = event.getPlayer();
		List<Action> actions = new ArrayList<>();
		for(Player p : match.getPlayers())
			actions.add(new ActionViewStatistics(player, p));
		actions.add(new DiscardAction(player));
		return actions;
	}

	private List<Action> getActionsViewStatistics(Event event) {
		Player player = event.getPlayer();
		List<Action> actions = new ArrayList<>();
		actions.add(new DiscardPlacement(player));
		return actions;
	}

	public List<Action> getActionsRequiredValue(EventRequiredValueNotSatisfied event) {
		Player player = event.getPlayer();
		List<Action> actions = new ArrayList<>();
		int i = event.getOccupiable().getRequiredValue()-event.getFamilyMember().getValue();
		for (; i <= player.getResourceValue(ResourceType.SERVANT); i++) {
			Action action = ActionFactory.createActionPlace(player, event.getFamilyMember(), event.getOccupiable(), new Servant(i));
			if(action.isValid(match))
				actions.add(action);
		}
		actions.add(new DiscardPlacement(player));
		return actions;
	}

	private List<Action> getActionsTowerChosen(EventTowerChosen event) {
		Player player = event.getPlayer();
		List<Action> actions = new ArrayList<>();

		for(TowerFloor towerFloor : event.getTower().getFloors()){
			ActionPlace action = ActionFactory.createActionPlace(event.getPlayer(), event.getFamilyMember(), towerFloor);
			if(action.isValid(match))
				actions.add(new ActionPlaceOnTower(player, event.getFamilyMember(), towerFloor));

		}
		actions.add(new DiscardPlacement(player));
		return actions;
	}

	private List<Action> getActionsWorkplaceChosen(EventWorkplaceChosen event) {
		Player player = event.getPlayer();
		List<Action> actions = new ArrayList<>();
		for(SpaceWorkZone spaceWorkZone : match.getBoard().getSpaceWorkZones().values()){
			for(SpaceWork spaceWork : spaceWorkZone.getSpaceWorks()){
				ActionPlace action = ActionFactory.createActionPlace(event.getPlayer(), event.getFamilyMember(), spaceWork);
				if(action.isValid(match))
					actions.add(new ActionPlaceOnSpaceWork(player, event.getFamilyMember(), spaceWork));
			}
		}
		actions.add(new DiscardPlacement(player));
		return actions;
	}

	private List<Action> getActionsMarketChosen(EventMarketChosen event) {
		Player player = event.getPlayer();
		List<Action> actions = new ArrayList<>();
		for(SpaceMarket spaceMarket : match.getBoard().getMarket().getSpaceMarkets()){
			ActionPlace action = ActionFactory.createActionPlace(event.getPlayer(), event.getFamilyMember(), spaceMarket);
			if(action.isValid(match))
				actions.add(new ActionPlaceOnMarket(player, event.getFamilyMember(), spaceMarket));
		}
		actions.add(new DiscardPlacement(player));
		return actions;
	}
}
