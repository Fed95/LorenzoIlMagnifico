package it.polimi.ingsw.gc_12.mvc;

import java.util.*;

import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;

public class ViewCLI extends Observable{

	private Scanner in = new Scanner(System.in);
	private ControllerPlayer controllerPlayer;
	private Player player;
	private Match match;

	public ViewCLI(Player player, ControllerPlayer controllerPlayer, Match match) {
		this.player = player;
		this.controllerPlayer = controllerPlayer;
		this.match = match;
	}

	public void askAction() {
		System.out.println();
		System.out.println("ROUND " + match.getRoundNUm() + "  ||  " + player.getName());
		System.out.println("Write the number of the action you want to perform");
		System.out.println("0 - Place family member");
		System.out.println("1 - Place leader card");
		System.out.println("2 - Activate leader card");
		System.out.println("3 - Discard leader card");
		System.out.println("4 - Skip Action");

		while (true) {
			if(in.hasNextInt()) {
				switch (in.nextInt()) {
					case 0:
						System.out.println("Action 'Place Family Member' chosen");
						askFamilyMember();
						break;
					case 4:
						break;
					default:
					System.out.println("The specified input is not listed above");
					in.next();
				}

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
			System.out.println(i + " - " + familyMembers.get(familyMemberColor));
			i++;
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
					askAction();
					return;
				}else {
					FamilyMemberColor familyMemberColor = familyMemberColors.get(input);
					System.out.println("familyMember " + familyMemberColor + " chosen ");
					try {
						controllerPlayer.setFamilyMember(familyMemberColor);
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

	public void askOccupiable() {
		List<Occupiable> occupiables = match.getBoard().getOccupiables();
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
				if(input < 0 || input > i) {
					System.out.println("The specified input is not listed above");
					askFamilyMember();
				}else if(input == i) {
					askAction();
					return;				
				}else{
					Occupiable occupiable = occupiables.get(input);
					controllerPlayer.setOccupiable(occupiable);
					break;
				}
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

	public void printError(String error) {
		System.out.println(error);
	}
}
