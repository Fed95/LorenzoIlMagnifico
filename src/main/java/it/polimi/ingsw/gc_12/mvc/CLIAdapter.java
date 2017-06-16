package it.polimi.ingsw.gc_12.mvc;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Zone;
import it.polimi.ingsw.gc_12.action.ActionChooseFamilyMember;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.action.ActionPassTurn;
import it.polimi.ingsw.gc_12.action.ActionPlace;
import it.polimi.ingsw.gc_12.client.ClientSender;
import it.polimi.ingsw.gc_12.action.FreeAction;
import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.client.rmi.ClientRMI;
import it.polimi.ingsw.gc_12.client.rmi.ClientRMIView;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.resource.Servant;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class CLIAdapter {

	private ViewCLI view;
	private ClientSender client;
	//private ControllerPlayer controller;
	//private Match match;


	public CLIAdapter(ViewCLI view, ClientSender client) {
		this.view = view;
		this.client = client;
	}

	public void sendAction(int input) throws IOException {
		client.sendAction(input);
	}

	/*public void setAction(int actionNum, boolean isFmPlaced) throws IOException {

		switch (actionNum) { // must keep 'case 0' last
			case 2:
				ActionPassTurn action = new ActionPassTurn(view.getCurrentPlayer());
				client.sendAction(action);
				break;
			case 1: // View statistics
				//controller.viewStatistics();
				break;
			case 0: // Place Family Member
				System.out.println("CLIAdapter: Action PlaceFamilyMember recognised.");
				if(!isFmPlaced) {
					view.askFamilyMember();
					break;
				}
			default:
				System.out.println("The specified input is not listed above");
				view.askAction(isFmPlaced);
				break;
		}
	}

	public void setFamilyMember(int input, List<FamilyMember> usableFMs) throws IOException {
		if(input < 0 || input > usableFMs.size()){
			System.out.println("The specified input is not listed above");
			view.askFamilyMember();
		}else if(input == usableFMs.size()) {
			System.out.println("Action discarded");
			view.askAction(false);
		}else {
			FamilyMember familyMember = usableFMs.get(input);
			System.out.println("familyMember " + familyMember.getColor() + " chosen.");
			try {
				ActionChooseFamilyMember action = new ActionChooseFamilyMember(view.getCurrentPlayer(), familyMember);
				System.out.println("ActionPlace created. Sending it to ClientRMI");
				client.sendAction(action);
			}catch(RuntimeException e){
				System.out.println(e.getMessage());
				view.askFamilyMember();
			}
		}
	}

	public void setZone(List<Zones> zones, FamilyMember familyMember, int index) throws IOException {
		if(index < 0 || index > zones.size()) {
			System.out.println("The specified input is not listed above");
			view.askZone(familyMember);
		}
		else if(index == zones.size()) {
			System.out.println("Action Discarded");
			view.askAction(false);
		}
		else {
			Zones zone = zones.get(index);
			if(zone instanceof Occupiable) {
				ActionPlace action = view.createActionPlace(familyMember, (Occupiable) zone);
				client.sendAction(action);
			}
			else {
				view.askOccupiableByZone(familyMember, zone);
			}
		}
	}

	public void setOccupiable(Zones zone, FamilyMember familyMember, int occupiableIndex, List<Occupiable> occupiables) throws IOException {
		if(occupiableIndex < 0 || occupiableIndex > occupiables.size()) {
			System.out.println("The specified input is not listed above");
			view.askOccupiableByZone(familyMember, zone);
		}else if(occupiableIndex == occupiables.size()) {
			System.out.println("Action discarded.");
			view.askOccupiable(familyMember);
		}else{
			Occupiable occupiable = occupiables.get(occupiableIndex);
			ActionPlace action = view.createActionPlace(familyMember, occupiable);
			client.sendAction(action);
		}
	}

	public void placeWithServants(Occupiable occupiable, FamilyMember familyMember, Servant servant) throws IOException {
		ActionPlace action = view.createActionPlace(familyMember, servant, occupiable);
		client.sendAction(action);
	}

	public void setFreeAction(int occupiableIndex, List<Occupiable> occupiables, FamilyMember familyMember) throws IOException {
		if(occupiableIndex < 0 || occupiableIndex > occupiables.size()) {
			System.out.println("The specified input is not listed above");
			view.freeAction(occupiables, familyMember);
		}else if(occupiableIndex == occupiables.size()) {
			System.out.println("Action discarded.");
		}else{
			Occupiable occupiable = occupiables.get(occupiableIndex);
			ActionPlace action = new FreeAction(view.getCurrentPlayer(), familyMember, occupiable);
			client.sendAction(action);
		}
	}


	/*

	public void placeWithServants(Occupiable occupiable, FamilyMember familyMember) throws IOException {
		ActionPlace action = view.createActionPlace(familyMember, occupiable);
		client.sendAction(action);
	}

	/*@Override
	public boolean supportChurch() {
		return view.supportChurch();
	}

	@Override
	public void excommunicationMessage() {
		view.excommunicationMessage();
	}

	@Override
	public int askServants(int requiredServants) {
		return view.askServants(requiredServants);
	}

	@Override
	public void viewStatistics() {
		int choice = view.viewStatistics();
		view.viewStatistics(match.getPlayers().get(choice));
		this.askAction();
	}

	@Override
	public void askFreeAction(CardType type, int value) {
		List<TowerFloor> floors = new ArrayList<>();
		List<Card> cards = new ArrayList<>();
		for (TowerFloor floor : match.getBoard().getTowerSet().getTower(type).getFloors()){
			if(!(floor.getRequiredValue() > value)) {
				cards.add(floor.getCard());
				floors.add(floor);
			}
		}
		int choice = view.askFreeAction(cards, value);
		controller.pickCard(cards.get(choice));
		floors.get(choice).removeCard();
	}

	@Override
	public int chooseResourceExchange(List<ResourceExchange> exchanges) {
		int choice = view.chooseResourceExchange(exchanges);
		return  choice;
	}*/

}
