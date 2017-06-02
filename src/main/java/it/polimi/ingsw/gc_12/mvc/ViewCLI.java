package it.polimi.ingsw.gc_12.mvc;

import java.util.*;

import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.resource.ResourceType;

public class ViewCLI extends Observable{

	private Scanner in = new Scanner(System.in);
	private CLIAdapter controller;
	private Player player;
	private Match match;

	public ViewCLI(Player player, CLIAdapter controller, Match match) {
		this.player = player;
		this.controller = controller;
		this.match = match;
	}

	public void startTurn() {
		System.out.println();
		System.out.println("ROUND " + match.getRoundNUm() + "  ||  " + player.getName());
	}

	public void askAction(boolean isFMPlaced) {
		System.out.println();
		System.out.println("Write the number of the action you want to perform");

		if(!isFMPlaced)
			System.out.println("0 - Place family member");
		//System.out.println("1 - Place leader card");
		//System.out.println("2 - Activate leader card");
		//System.out.println("3 - Discard leader card");
		System.out.println("1 - Skip Action");

		while (true) {
			if(in.hasNextInt()) {
				controller.setAction(in.nextInt(), isFMPlaced);
				break;
			}
			else {
				System.out.println("The input must be a number. Try again");
				in.next();
			}	
		}
	}

	public void askFamilyMember() {
		System.out.println("Write the number of the family member you want to use");

		int i = 0;
		List<FamilyMemberColor> familyMemberColors = Arrays.asList(FamilyMemberColor.values());
		Map<FamilyMemberColor, FamilyMember> familyMembers = player.getFamilymembers();
		for(FamilyMemberColor familyMemberColor : familyMemberColors) {
			if(!familyMembers.get(familyMemberColor).isBusy()) {
				System.out.println(i + " - " + familyMembers.get(familyMemberColor));
				i++;
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
					System.out.println(i + "Action discarded");
					askAction(false);
					return;
				}else {
					FamilyMemberColor familyMemberColor = familyMemberColors.get(input);
					System.out.println("familyMember " + familyMemberColor + " chosen ");
					try {
						controller.setFamilyMember(familyMemberColor);
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

	public void askZone() {
		List<Zone> zones = match.getBoard().getZones();
		System.out.println();
		System.out.println("Write the number of the zone you want to place the family member in.");

		int i = 0;
		for(Zone zone : zones) {
			System.out.println(i + " - " + zones.get(i));
			i++;
		}
		System.out.println(i + " - Discard action.");

		while (true) {
			if(in.hasNextInt()) {
				controller.setZone(in.nextInt());
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
			System.out.println("occupiable " + occupiable.getOccupiers());
			System.out.println(i + " - " + occupiable.toString());
			i++;
		}
		System.out.println(i + " - Discard action.");

		while (true) {
			if(in.hasNextInt()) {
				int input = in.nextInt();
				controller.setOccupiable(zoneIndex, input);
				break;
			}else {
				System.out.println("The input must be a number. Try again");
				in.next();
			}
		}
	}

	public boolean supportChurch(){
		while(true) {
			System.out.println("Will you show your sustain to the church? [YES / NO]");
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
		System.out.println("YOU HAVE BEEN EXCOMMUNICATED");
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
}
