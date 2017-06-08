package it.polimi.ingsw.gc_12.mvc;

import java.rmi.RemoteException;
import java.util.*;

import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;
import it.polimi.ingsw.gc_12.resource.ResourceType;

public class ViewCLI extends Observable{

	private Scanner in = new Scanner(System.in);
	private CLIAdapter adapter;
	private Player player;
	private MatchRemote match;

	public ViewCLI(/*Player player, */CLIAdapter adapter, MatchRemote match) {
		//this.player = player;
		this.adapter = adapter;
		this.match = match;
	}

	public void startTurn() {
		System.out.println();
		//System.out.println("ROUND " + match.getRoundNUm() + "  ||  " + player.getName());
	}

	public void askAction(boolean isFMPlaced) throws RemoteException {
		System.out.println();
		System.out.println("Write the number of the action you want to perform");

		if(!isFMPlaced)
			System.out.println("0 - Place family member");
		//System.out.println("2 - Place leader card");
		//System.out.println("3 - Activate leader card");
		//System.out.println("4 - Discard leader card");
		System.out.println("1 - View Statistics");
		System.out.println("2 - Pass Turn");

		while (true) {
			if(in.hasNextInt()) {
				adapter.setAction(in.nextInt(), isFMPlaced);
				break;
			}
			else {
				System.out.println("The input must be a number. Try again");
				in.next();
			}	
		}
	}

	public void askFamilyMember() throws RemoteException {
		System.out.println("Write the number of the family member you want to use");

		List<FamilyMember> usableFMs = new ArrayList<>();
		List<FamilyMember> familyMembers = match.getCurrentPlayer().getFamilyMembersList();
		
		int i;
		for (i = 0; i < familyMembers.size(); i++) {
			FamilyMember familyMember = familyMembers.get(i);
			if(!familyMember.isBusy()) {
				System.out.println(i + " - " + familyMember.getColor());
				usableFMs.add(familyMember);
			}	
		}

		System.out.println(i + " - Discard action.");

		while (true) {
			if(in.hasNextInt()) {
				int input = in.nextInt();
				if(input < 0 || input > i){
					System.out.println("The specified input is not listed above");
					askFamilyMember();
				}else if(input == i) {
					System.out.println("Action discarded");
					askAction(false);
					return;
				}else {
					FamilyMember familyMember = usableFMs.get(input);
					System.out.println("familyMember " + familyMember.getColor() + " chosen ");
					try {
						adapter.setFamilyMember(familyMember);
					}catch(RuntimeException e){
						System.out.println(e.getMessage());
						askFamilyMember();
					}
					break;
				}
			}
			else {
				System.out.println("The input must be a number. Try again");
				in.next();
			}	
		}
	}

	/*public void askZone() {
		List<Zone> zones = match.getBoard().getZones();
		System.out.println();
		System.out.println("Write the number of the zone you want to place the family member in.");

		int i = 0;
		for(Zone zone : zones) {
			System.out.println(i + " - " + zone);
			i++;
		}
		System.out.println(i + " - Go back.");

		while (true) {
			if(in.hasNextInt()) {
				adapter.setZone(in.nextInt());
				break;
			}
			else {
				System.out.println("The input must be a number. Try again");
				in.next();
			}
		}
	}

	public void askOccupiable(int zoneIndex) {
		Zone zone = match.getBoard().getZones().get(zoneIndex);
		List<Occupiable> occupiables = zone.getOccupiables();
		System.out.println();
		System.out.println("Write the number of the space you want to place the family member in.");
		int i = 0;
		for(Occupiable occupiable : occupiables) {
			System.out.println("occupiers: " + occupiable.getOccupiers());
			System.out.println(i + " - " + occupiable.toString());
			i++;
		}
		System.out.println(i + " - Go back.");

		while (true) {
			if(in.hasNextInt()) {
				int input = in.nextInt();
				adapter.setOccupiable(zoneIndex, input);
				break;
			}else {
				System.out.println("The input must be a number. Try again");
				in.next();
			}
		}
	}

	public boolean supportChurch(){
		System.out.println("Will you show your sustain to the church? [YES / NO]");
		while(true) {
			String choice = in.next();
			switch (choice) {
				case "yes":
					return true;
				case "no":
					return false;
				default:
					System.out.println("'" + choice + "' is not a valid answer.");
			}
		}
	}


	public void excommunicationMessage(){
		System.out.println("--- YOU HAVE BEEN EXCOMMUNICATED ---");
	}

	public int askServants(int requiredServants) {
		System.out.println("You have " + player.getResourceValue(ResourceType.SERVANT) + " servants.");
		System.out.println("How many servants would you like to use? (min: " + requiredServants + ")");
		while(true) {
			int choice = in.nextInt();
			if (choice >= requiredServants && choice <= player.getResourceValue(ResourceType.SERVANT))
				return choice;
			else
				System.out.println("That won't do... Please try again.");
		}
	}

	public int viewStatistics() {
		int i = 0;
		System.out.println("Who's statistics would you like to view?");
		for(Player player : match.getPlayers()){
			System.out.println(i + " - " + player.getName());
			i++;
		}
		System.out.println(i + " - Go back.");
		int choice = in.nextInt();
		if(choice == i)
			adapter.askAction();
		return choice;
	}

	public void viewStatistics(Player player) {
		System.out.println("Viewing statistics of: " + player.getName());
		for(Resource resource : player.getResources().values()){
			System.out.println(resource);
		}
		for (FamilyMember familyMember : player.getFamilymembers().values()){
			System.out.println(familyMember);
		}
		System.out.println();
		System.out.println();
	}

	public int askFreeAction(List<Card> cards, int value){
		System.out.println("You received a free action of value" + value + "!" +
				"Which card would you like to pick?");
		int i = 0;
		for(Card card : cards){
			System.out.println(i + " - " + card);
		}
		System.out.println(i + " - No thanks.");
		int choice = in.nextInt();
		if(choice == i)
			adapter.askAction();
		return choice;
	}

	public int chooseResourceExchange(List<ResourceExchange> exchanges) {
		System.out.println("Choose the exchange you would like to execute.");
		int i = 0;
		for(ResourceExchange exchange : exchanges){
			System.out.println(i + exchange.toString());
			i++;
		}
		//TODO: implement discard action
		System.out.println(i + "Discard action");
		int choice = in.nextInt();
		if(choice == i)
			adapter.askAction();
		return choice;
	}*/
}
