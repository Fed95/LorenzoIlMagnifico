package it.polimi.ingsw.gc_12.mvc;

import java.util.*;

import it.polimi.ingsw.gc_12.Board;
import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.FamilyMemberColor;
import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.Player;

public class ViewCLI extends Observable{

	private Scanner in = new Scanner(System.in);
	private Player player;
	private Board board;

	public ViewCLI(Player player, Board board) {
		this.player = player;
		this.board = board;		
	}

	public void askAction() { 

		System.out.println("Write the number of the action you want to perform");
		System.out.println("0 - Place family member");
		System.out.println("1 - Place leader card");
		System.out.println("2 - Activate leader card");
		System.out.println("3 - Discard leader card");

		while (true) {
			if(in.hasNextInt()) {
				switch (in.nextInt()) {
				case 0:
					System.out.println("Action 'Place Family Member' chosen");
					askFamilyMember();
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
				}else 				if(input == i) {
					askAction();
					return;
				}else {
					FamilyMember familyMember = new FamilyMember(player, familyMemberColors.get(input));
					System.out.println(familyMember + " chosen ");
					setChanged();
					notifyObservers(familyMember);
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
		List<Occupiable> occupiables = board.getOccupiables();
		System.out.println();
		System.out.println("Write the number of the space you want to place the family member in.");
		int i = 0;
		for(Occupiable occupiable : occupiables) {
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
					setChanged();
					notifyObservers(occupiable);
					break;
				}
			}else {
				System.out.println("The input must be a number. Try again");
				in.next();
			}
		}
	}

	public void printError(String error) {
		System.out.println(error);
	}
}
