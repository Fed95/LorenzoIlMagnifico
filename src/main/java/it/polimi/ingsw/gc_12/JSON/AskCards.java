package it.polimi.ingsw.gc_12.JSON;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.SpaceMarket;
import it.polimi.ingsw.gc_12.card.*;
import it.polimi.ingsw.gc_12.effect.*;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.resource.*;


public class AskCards {
	Scanner scanner = new Scanner(System.in);

	public List<Card> buildCards(){
        int id=1;
		List<Card> cards = new ArrayList<>();
		File file = new File("card.json");

		if(file.exists()){
			//taking the last id from the existing file
			JsonCard jsonObj = new JsonCard("card");
			List<Card> lastId = jsonObj.getCards();
			for(Card card: lastId){
				id=card.getId();
			}
		}
        while(true){
			id++;

			CardType cardType = askCardType();
			String name = askName();
			int period = askPeriod();
			List<Resource> requirements = askResource();
			List<Effect> effects = askEffect();

			CardDevelopment card = CardBuilder.create(cardType, id, name, period, requirements, effects);

    		cards.add(card);

        	System.out.println("Do you want to exit from creating card tool?[Yes/No]");
    		String choice = scanner.nextLine();
    		if(choice.toLowerCase().equals("yes")) {
				break;
			}
        }
		scanner.close();
        return cards;
	}

	private CardType askCardType() {
		CardType cardType;
		System.out.println("Choose type card");
		List<CardType> cardTypes = Arrays.asList(CardType.values());
		for (int i = 0; i < cardTypes.size(); i++) {
			System.out.println(i + " - " + cardTypes.get(i).name());
		}

		cardType = cardTypes.get(scanner.nextInt());
		scanner.nextLine(); // Discard "\n" character
		return cardType;
	}

	private String askName() {
		String name;
		do {
			System.out.println("Insert the name of the Card");
			name = scanner.nextLine();
		}
		while (name.isEmpty());
		return name;
	}

	private int askPeriod() {
		int period;
		do {
			System.out.println("Choose the period of the card");
			period = scanner.nextInt();
		}
		while(period <= 0 || period > Match.DEFAULT_PERIODS_NUM);
		return period;
	}

	private List<Resource> askResource(){
		Resource resource = null;
		int value = 0;
		List<Resource> requirements = new ArrayList<>();
		while(true){
			System.out.println("Insert requirements");
			System.out.println(" 1 FaithPoint \n 2 MilitaryPoint \n 3 VictoryPoints \n 4 Servants \n 5 Money \n 6 Stone \n 7 Wood \n 8 Exit create resource tool");
			int resourcechoice = scanner.nextInt();
			scanner.nextLine();
			switch(resourcechoice){
				case 1:
					System.out.println("Insert value of Faith Points");
					value = scanner.nextInt();
					scanner.nextLine();
					resource = new FaithPoint(value);
					break;

				case 2:
					System.out.println("Insert value of Military Points");
					value = scanner.nextInt();
					scanner.nextLine();
					resource = new MilitaryPoint(value);
					break;

				case 3:
					System.out.println("Insert value of Victory Points");
					value = scanner.nextInt();
					scanner.nextLine();
					resource = new VictoryPoint(value);
					break;

				case 4:
					System.out.println("Insert value of Servants");
					value = scanner.nextInt();
					scanner.nextLine();
					resource = new Servant(value);
					break;

				case 5:
					System.out.println("Insert value of Money");
					value = scanner.nextInt();
					scanner.nextLine();
					resource = new Money(value);
					break;
				case 6:
					System.out.println("Insert value of Stone");
					value = scanner.nextInt();
					scanner.nextLine();
					resource = new Stone(value);
					break;
				case 7:
					System.out.println("Insert value of Wood");
					value = scanner.nextInt();
					scanner.nextLine();
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
		while(true){
			System.out.println("Insert Effect");
			System.out.println(" 1 Effectc change Family member Value \n 2 Effect change resource \n 8 Exit create resource tool");
			int effectchoice=scanner.nextInt();
			scanner.nextLine();
			switch(effectchoice){
				case 1:
					System.out.println("Insert the new value of the family member");
					amount = scanner.nextInt();
					scanner.nextLine();
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
		return effects;
	}
	private List<Occupiable> askOccupiable(){
		List<Occupiable> occupiables=new ArrayList<>();
		Occupiable occupiable=null;

		while(true){
			System.out.println("Insert the Occupiable");
			System.out.println(" 1 SpaceMarket(work only this) /n 2 SpaceWork /n 3 CouncilPalace /n 4 TowerFloor /n 5 Exit");
			int occupiableChoice = scanner.nextInt();
			scanner.nextLine();
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
		return occupiables;
	}
}
