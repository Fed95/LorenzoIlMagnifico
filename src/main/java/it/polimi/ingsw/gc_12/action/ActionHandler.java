package it.polimi.ingsw.gc_12.action;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.occupiables.*;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;
import it.polimi.ingsw.gc_12.resource.ResourceType;
import it.polimi.ingsw.gc_12.resource.Servant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ActionHandler {
	private List<Action> actions = new ArrayList<>();
	private LinkedList<Event> events = new LinkedList<>();
	private Match match;
	private int offset;
	private int multiplier;
	private boolean hasPlaced = false;

	public ActionHandler(Match match) {
		this.match = match;
		this.actions = new ArrayList<>(Collections.singletonList(new ActionReady(null)));
		this.multiplier = 1;
	}

	public Action getAvailableAction(int input) {
		int inputReal = (input - offset * multiplier > 0 ? input - offset*multiplier : 0);
		Action action = actions.get(inputReal);
		if(events.size() > 0) {
			events.removeFirst();
			if(events.size() > 0)
				actions = events.getFirst().getActions();
		}
		offset = 0;
		multiplier = 1;
		return action;
	}


	public void update(Event event) {

		events.addLast(event);

		if(event instanceof EventFamilyMemberChosen) {
			event.setActions(getActionsFamilyMemberChoosen((EventFamilyMemberChosen) event));
		}
		else if(event instanceof EventServantsRequested) {
			event.setActions(getActionsRequiredValue((EventServantsRequested) event));
		}
		else if(event instanceof EventStartTurn || event instanceof EventPlacementEnded || event instanceof EventDiscardAction) {
			event.setActions(getActionsStartTurn(event));
		}
		else if(event instanceof EventRequestStatistics){
			event.setActions(getActionsRequestStatistics(event));
		}
		else if(event instanceof EventViewStatistics){
			event.setActions(getActionsViewStatistics(event));
		}
		else if(event instanceof EventTowerChosen){
			event.setActions(getActionsTowerChosen((EventTowerChosen) event));
		}
		else if(event instanceof EventWorkplaceChosen){
			event.setActions(getActionsWorkplaceChosen((EventWorkplaceChosen) event));
		}
		else if(event instanceof EventMarketChosen){
			event.setActions(getActionsMarketChosen((EventMarketChosen) event));
		}
		else if(event instanceof EventFreeAction){
			event.setActions(getFreeActions((EventFreeAction) event));
		}
		else if(event instanceof EventChooseExchange){
			event.setActions(getActionsChooseExchange((EventChooseExchange) event));
		}
		else
			events.removeLast();

		saveActions(event);
	}

	private List<Action> getActionsChooseExchange(EventChooseExchange event) {
		Player player = event.getPlayer();
		List<Action> actions = new ArrayList<>();
		for (ResourceExchange exchange : event.getExchanges())
			actions.add(new ActionChooseExchange(player, exchange));
		return actions;
	}

	private void saveActions(Event event) {
		if(events.size() > 0 && events.getFirst() == event) {
			actions = event.getActions();
		}
	}

	private List<Action> getFreeActions(EventFreeAction event)  {
		Player player = event.getPlayer();
		List<Action> actions = new ArrayList<>();
		for(Occupiable occupiable: event.getOccupiables()) {
				ActionPlace action = ActionFactory.createActionPlace(player, event.getFamilyMember(), occupiable);
				if(action.isValid(match))
					actions.add(action);
		}
		actions.add(new DiscardAction(player));
		return actions;
	}


	public List<Action> getActionsStartTurn(Event event) {
		Player player = event.getPlayer();
		List<Action> actions = new ArrayList<>();
		if(event instanceof EventStartTurn)
			hasPlaced = false;
		if(event instanceof EventPlacementEnded)
			hasPlaced = true;
		if(!hasPlaced) {
			for (FamilyMember familyMember : player.getAvailableFamilyMembers()) {
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
		//Adds the towers with at least one valid floor TODO: ADD CHECK FOR MARKET AND WORK (or remove for tower)
		for(Tower tower : match.getBoard().getTowerSet().getTowers().values()){
			for(TowerFloor towerFloor : tower.getFloors()){
				ActionPlace action = ActionFactory.createActionPlace(player, event.getFamilyMember(), towerFloor);
				if(action.isValid(match)) {
					actions.add(new ActionChooseTower(player, event.getFamilyMember(), tower));
					break;
				}
			}
		}
		for(SpaceMarket spaceMarket : match.getBoard().getMarket().getSpaceMarkets()){
			ActionPlace action = ActionFactory.createActionPlace(player, event.getFamilyMember(), spaceMarket);
			if(action.isValid(match)) {
				actions.add(new ActionChooseMarket(player, event.getFamilyMember()));
				break;
			}
		}
		for(SpaceWork spaceWork : match.getBoard().getSpaceWorks()){
				ActionPlace action = ActionFactory.createActionPlace(player, event.getFamilyMember(), spaceWork);
				if(action.isValid(match)) {
					actions.add(new ActionChooseWorkplace(player, event.getFamilyMember()));
					break;
				}
		}
		actions.add(new ActionPlaceOnCouncil(player, event.getFamilyMember(), match.getBoard().getCouncilPalace()));
		actions.add(new DiscardAction(player));
		return actions;
	}

	public List<Action> getActionsRequestStatistics(Event event) {
		Player player = event.getPlayer();
		List<Action> actions = new ArrayList<>();
		for(Player p : match.getPlayers().values())
			actions.add(new ActionViewStatistics(player, p));
		actions.add(new DiscardAction(player));
		return actions;
	}

	private List<Action> getActionsViewStatistics(Event event) {
		Player player = event.getPlayer();
		List<Action> actions = new ArrayList<>();
		actions.add(new DiscardAction(player));
		return actions;
	}

	public List<Action> getActionsRequiredValue(EventServantsRequested event){
		Player player = event.getPlayer();
		List<Action> actions = new ArrayList<>();
		int i = event.getOccupiable().getRequiredValue() - event.getFamilyMember().getValue();
		i = (i < 0 ? 0 : i);
		this.offset = i;
		this.multiplier = event.getMultiplier();
		for (; i <= player.getResourceValue(ResourceType.SERVANT); i++) {
			Action action = ActionFactory.createActionPlace(player, event.getFamilyMember(), event.getOccupiable(), new Servant(i), true);
			if(action.isValid(match))
				actions.add(action);
		}
		actions.add(new DiscardAction(player));
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
		Collections.reverse(actions);
		actions.add(new DiscardAction(player));
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
		actions.add(new DiscardAction(player));
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
		actions.add(new DiscardAction(player));
		return actions;
	}
}
