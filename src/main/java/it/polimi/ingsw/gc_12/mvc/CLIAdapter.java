package it.polimi.ingsw.gc_12.mvc;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.FamilyMemberColor;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.MatchRemote;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.Zone;
import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.client.rmi.ClientRMI;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class CLIAdapter implements View{

	private ViewCLI view;
	private ClientRMI client;
	//private ControllerPlayer controller;
	//private Match match;


	public CLIAdapter(MatchRemote match, ClientRMI client) {
		view = new ViewCLI(this, match);
		this.client = client;
		
	}

	@Override
	public void startTurn() {
		view.startTurn();
	}

	@Override
	public void askAction(boolean isFMPlaced) throws RemoteException {
		view.askAction(isFMPlaced);
	}

	public void setAction(int actionNum, boolean isFmPlaced) throws RemoteException {

		switch (actionNum) { // must keep 'case 0' last
			case 2:
				break;
			case 1: // View statistics
				//controller.viewStatistics();
				break;
			case 0: // Place Family Member
				System.out.println("dentro case 0");
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

	public void setFamilyMember(FamilyMember familyMember) throws RemoteException {
		client.setFamilyMember(familyMember);
	}
/*
	@Override
	public void askOccupiable() {
		view.askZone();
	}

	public void setZone(int index) {
		List<Zone> zones = match.getBoard().getZones();
		Zone zone = zones.get(index);
		if(index < 0 || index > zones.size()) {
			System.out.println("The specified input is not listed above");
			view.askZone();
		}
		else if(index == zones.size()) {
			askAction();
		}
		else {
			if(zone instanceof Occupiable) {
				controller.setOccupiable((Occupiable) zone);
			}
			else {
				view.askOccupiable(index);
			}
		}
	}

	public void setOccupiable(int zoneIndex, int occupiableIndex) {
		Zone zone = match.getBoard().getZones().get(zoneIndex);
		List<Occupiable> occupiables = zone.getOccupiables();
		if(occupiableIndex < 0 || occupiableIndex > occupiables.size()) {
			System.out.println("The specified input is not listed above");
			view.askOccupiable(zoneIndex);
		}else if(occupiableIndex == occupiables.size()) {
			askAction();
		}else{
			Occupiable occupiable = occupiables.get(occupiableIndex);
			controller.setOccupiable(occupiable);
		}
	}

	@Override
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
