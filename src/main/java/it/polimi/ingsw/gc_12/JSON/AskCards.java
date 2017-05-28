package it.polimi.ingsw.gc_12.JSON;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.card.*;
import it.polimi.ingsw.gc_12.effect.*;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.resource.*;


public class AskCards {
	Scanner scanner = new Scanner(System.in);
	Match match = Match.instance();

	public List<Card> buildCards(){
		match.setup();
        int id = 1;
		List<Card> cards = new ArrayList<>();
		File file = new File("card.json");

		if(file.exists()){
			//taking the last id from the existing file
			LoaderCard jsonObj = new LoaderCard("card");
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

			CardDevelopment card;
			try {
				card = CardBuilder.create(cardType, id, name, period, requirements, effects);
			} catch (IllegalArgumentException e) {
				continue;
			}
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
		while(true) {
			System.out.println("Choose type card");
			List<CardType> cardTypes = Arrays.asList(CardType.values());
			for (int i = 0; i < cardTypes.size(); i++) {
				System.out.println(i + " - " + cardTypes.get(i).name());
			}

			int cardInput = scanner.nextInt();
			if(cardInput >= cardTypes.size())
				continue;

			cardType = cardTypes.get(cardInput);
			scanner.nextLine(); // Discard "\n" character
			return cardType;
		}
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

		scanner.nextLine();
		return period;
	}

	private List<Resource> askResource(){
		List<Resource> requirements = new ArrayList<>();
		while(true){
			System.out.println("Insert requirements");
			List<ResourceType> resourceTypes = Arrays.asList(ResourceType.values());
			int i;
			for (i = 0; i < resourceTypes.size(); i++) {
				System.out.println(i + " - " + resourceTypes.get(i).name());
			}
			System.out.println(i + " - Exit create resource tool");

			int resourceInput = scanner.nextInt();

			if(resourceInput == resourceTypes.size())
				break;
			else if(resourceInput > resourceTypes.size())
				continue;

			ResourceType resourceType = resourceTypes.get(resourceInput);

			System.out.println("Insert value for " + resourceType.name());
			requirements.add(ResourceBuilder.create(resourceType, scanner.nextInt()));

		}
		scanner.nextLine();
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

		while(true){
			System.out.println("Insert Effect");
			System.out.println("1 - Effect change Family member Value \n2 - Effect change resource \n8 - Exit create resource tool");
			int effectChoice = scanner.nextInt();
			scanner.nextLine();
			switch(effectChoice){
				case 1:
					familyMember = askFamilyMember();
					occupiables= askOccupiable();

					//chiedere cosa attiva questo effetto tipo piazzo family member e costa -3 per le carte viola
					event = new EventPlaceFamilyMember(occupiables, familyMember);
					effect = new EffectChangeFamilyMemberValue(event,amount);
					break;
			}
			if(effectChoice == 8){
				break;
			}
			effects.add(effect);

		}
		return effects;
	}

	private FamilyMember askFamilyMember() {
		while(true) {
			System.out.println("Insert the value of the family member (put 0 for a family member of any value).");
			int value = scanner.nextInt();

			System.out.println("Insert the color of the family member");
			List<FamilyMemberColor> familyMemberColors = Arrays.asList(FamilyMemberColor.values());
			int i;
			for (i = 0; i < familyMemberColors.size(); i++) {
				System.out.println(i + " - " + familyMemberColors.get(i).name());
			}
			System.out.println(i + " - ANY COLOR");
			int colorInput = scanner.nextInt();
			scanner.nextLine();
			if(colorInput == familyMemberColors.size())
				return new FamilyMember(value);
			else if(colorInput > familyMemberColors.size())
				continue;

			return new FamilyMember(familyMemberColors.get(colorInput), value);
		}


	}

	private List<Occupiable> askOccupiable(){
		List<Occupiable> occupiables = match.getBoard().getOccupiables();
		List<Occupiable> occupiablesChosen = new ArrayList<>();

		while(true){
			System.out.println("Insert the Occupiable");

			int i;
			for (i = 0; i < occupiables.size(); i++) {
				System.out.println(i + " - " + occupiables.get(i).toString());
			}

			System.out.println(i + " - Exit");

			int occupiableInput = scanner.nextInt();
			if(occupiableInput == occupiables.size())
				break;
			else if(occupiableInput > occupiables.size())
				continue;
			occupiablesChosen.add(occupiables.get(occupiableInput));
			occupiables.remove(occupiableInput);

		}
		return occupiablesChosen;
	}
}
