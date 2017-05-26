package it.polimi.ingsw.gc_12.JSON;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.internal.requests.SortingRequest;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.SpaceMarket;
import it.polimi.ingsw.gc_12.card.*;
import it.polimi.ingsw.gc_12.effect.*;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.resource.*;


public class AskCards {
	public List<Card> buildCards(){
        Scanner scan = new Scanner(System.in);
        int id=1;
		List<Resource> requirements = new ArrayList<>();
		List<Card> cards = new ArrayList<>();
		List<Effect> effects = new ArrayList<>();
		File file = new File("card.json");
		String name="";
		CardDevelopment cardDevelopment=null;
		int period=0;
		if(file.exists()){
			//taking the last id from the existing file
			JsonCard jsonObj = new JsonCard("card");
			List<Card> lastId = jsonObj.getCards();
			for(Card card: lastId){
				id=card.getId();
			}
		}
        while(true){
        	System.out.println("Choose type card");
        	System.out.println(" 1 Building /n 2 Territory /n 3 Building /n 4 Venuture");
        	int cardTypeChoice = scan.nextInt();
        	scan.nextLine();
        	switch(cardTypeChoice){
        		case 1:
        			while(name=="" || name==" "){
	        			System.out.println("Insert the name of the Card");
	            		name = scan.nextLine();
	        		}
            		
        			while(period<=0 || period>3){
            			System.out.println("Choose the period of the card");
            			period = scan.nextInt();
                    	scan.nextLine();            			
        			}
        			requirements = askResource();
        			effects = askEffect();
            		cardDevelopment = new CardBuilding(id, name, period , requirements, effects);
        			break;
        	}   		
    		cards.add(cardDevelopment);
    		id=id+1;
        	System.out.println("Do you want to exit from creating card tool?[Yes/No]");
    		String choice = scan.nextLine();
    		if(choice.equals("Yes")) {
				break;
			}
        }
		scan.close();
        return cards;
	}
	private List<Resource> askResource(){
		Resource resource = null;
		int value = 0;
        Scanner scan1 = new Scanner(System.in);
		List<Resource> requirements = new ArrayList<>();
		while(true){
			System.out.println("Insert requirements");
			System.out.println(" 1 FaithPoint \n 2 MilitaryPoint \n 3 VictoryPoints \n 4 Servants \n 5 Money \n 6 Stone \n 7 Wood \n 8 Exit create resource tool");
			int resourcechoice = scan1.nextInt();
			scan1.nextLine();
			switch(resourcechoice){
				case 1:
					System.out.println("Insert value of Faith Points");
					value = scan1.nextInt();
					scan1.nextLine();
					resource = new FaithPoint(value);
					break;

				case 2:
					System.out.println("Insert value of Military Points");
					value = scan1.nextInt();
					scan1.nextLine();
					resource = new MilitaryPoint(value);
					break;

				case 3:
					System.out.println("Insert value of Victory Points");
					value = scan1.nextInt();
					scan1.nextLine();
					resource = new VictoryPoint(value);
					break;

				case 4:
					System.out.println("Insert value of Servants");
					value = scan1.nextInt();
					scan1.nextLine();
					resource = new Servant(value);
					break;

				case 5:
					System.out.println("Insert value of Money");
					value = scan1.nextInt();
					scan1.nextLine();
					resource = new Money(value);
					break;
				case 6:
					System.out.println("Insert value of Stone");
					value = scan1.nextInt();
					scan1.nextLine();
					resource = new Stone(value);
					break;
				case 7:
					System.out.println("Insert value of Wood");
					value = scan1.nextInt();
					scan1.nextLine();
					resource = new Wood(value);
					break;
				case 8:				
					break;
			}
			if(resourcechoice == 8){
				break;
			}
			requirements.add(resource);
			
		}
		//scan1.close();
		return requirements;
	}
	private List<Effect> askEffect(){
		Effect effect=null;
		Event event=null;
		List<Occupiable> occupiables=new ArrayList<>();
		FamilyMember familyMember=null;
		int amount=0;
		List<Effect> effects=new ArrayList<>();
		List<Resource> costs=new ArrayList<>();
		List<Resource> resources=new ArrayList<>();
		int i=5;
		Scanner scan2=new Scanner(System.in);
		while(true){
			System.out.println("Insert Effect");
			System.out.println(" 1 Effectc change Family member Value \n 2 Effect change resource \n 8 Exit create resource tool");
			int effectchoice=scan2.nextInt();
			scan2.nextLine();
			switch(effectchoice){
				case 1:
					System.out.println("Insert the new value of the family member");
					amount = scan2.nextInt();
					scan2.nextLine();
					occupiables= askOccupiable();					
					familyMember=new FamilyMember();
					//chiedere cosa attiva questo effetto tipo piazzo family member e costa -3 per le carte viola
					event =new EventPlaceFamilyMember(occupiables, familyMember);
					effect=  new EffectChangeFamilyMemberValue(event,amount);
					break;
			}
			if(effectchoice == 8){
				break;
			}
			effects.add(effect);

		}
		//scan2.close();
		return effects;
	}
	private List<Occupiable> askOccupiable(){
		List<Occupiable> occupiables=new ArrayList<>();
		Occupiable occupiable=null;
		Scanner scan4 = new Scanner(System.in);

		while(true){
			System.out.println("Insert the Occupiable");
			System.out.println(" 1 SpaceMarket(work only this) /n 2 SpaceWork /n 3 CouncilPalace /n 4 TowerFloor /n 5 Exit");
			int occupiableChoice = scan4.nextInt();
			scan4.nextLine();
			switch(occupiableChoice){
				case 1:
					occupiable=new SpaceMarket();
					break;
			}
			if(occupiableChoice==5){				
				break;
			}
			occupiables.add(occupiable);

		}
		//scan4.close();
		return occupiables;
	}
}
