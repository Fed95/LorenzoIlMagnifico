package it.polimi.ingsw.gc_12.mvc;

import java.util.List;
import java.util.Observable;
import java.util.Scanner;
import it.polimi.ingsw.gc_12.Board;
import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.FamilyMemberColor;
import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.Player;

public class ViewCLI extends Observable{
	
	private Scanner in = new Scanner(System.in);
	private Player player;
	private Board board = new Board();

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
		for(FamilyMemberColor familyMemberColor : FamilyMemberColor.values()) {
			System.out.println(i + " - " + familyMemberColor.name());
			i++;
		}
		
		System.out.println(i + " - Discard action.");
		
		while (true) {
			if(in.hasNextInt()) {
				int input = in.nextInt();
				if(input == i) {
					askAction();
					return;
				}
					
				FamilyMember familyMember = new FamilyMember(player, FamilyMemberColor.values()[input]);
				setChanged();
				notifyObservers(familyMember);
				break;
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
		System.out.println("Write the number of the family member you want to use");
		int i = 0;
		for(Occupiable occupiable : occupiables) {
			System.out.println(i + " - " + occupiable.toString());
			i++;
		}
		
		System.out.println(i + " - Discard action.");
		
		while (true) {
			if(in.hasNextInt()) {
				int input = in.nextInt();
				if(input == i) {
					askAction();
					return;
				}
				
				Occupiable occupiable = occupiables.get(input);
				setChanged();
				notifyObservers(occupiable);
				break;
			}
			else {
				System.out.println("The input must be a number. Try again");
				in.next();
			}	
		}
	}
	
	
	
	public void errorActionNotValid() {
		System.out.println("Azione non valida");
	}
}
