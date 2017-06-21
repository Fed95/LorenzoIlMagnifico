package it.polimi.ingsw.gc_12.json.saver;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.FamilyMemberColor;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.card.*;
import it.polimi.ingsw.gc_12.effect.*;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.json.loader.LoaderCard;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceBuilder;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;
import it.polimi.ingsw.gc_12.resource.ResourceType;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SaverCard extends Saver<List<Card>> {

	Scanner scanner = new Scanner(System.in);
	private Match match;
	public SaverCard(Match match){
		super("cards");
		this.match = match;
	}

	@Override
	public void create(){
		List<Player> players = new ArrayList<>();
		match.init(players);
		int id = -1;
		List<Card> cards = new ArrayList<>();
		File file = new File("cards.JSON");

		if(file.exists()){
			System.out.println("entro in file exist");
			//taking the last id from the existing file
			List<Card> cardsOld = new LoaderCard().get(match);
			for(Card card: cardsOld){
				id=card.getId();
				System.out.println("id: "+id);
			}
			System.out.println("incrementero questo id: "+ id);
		}
		while(true){
			id++;
			CardType cardType = askCardType();
			String name = askName();
			int period = askPeriod();
			List<Resource> requirements = askResource();
			List<Effect> effects = askEffect(id);

			CardDevelopment card;
			try {
				card = CardBuilder.create(cardType, id, name, period, requirements, effects);
			} catch (IllegalArgumentException e) {
				continue;
			}
			cards.add(card);
			System.out.println("dimensione carte che sto scrivendo: " + cards.size());
			System.out.println("id che sto scrivendo" + id);
			super.save(cards, new CardExclusionStrategy());
			cards = new ArrayList<>();
			System.out.println("carta scritta");
			System.out.println("nuova lista: " + cards.size());
			System.out.println("Do you want to exit from creating card tool?[Yes/No]");
			String choice = scanner.nextLine();
			if(choice.toLowerCase().equals("yes")) {
				break;
			}

		}
		scanner.close();
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
		while(period <= 0 || period > 3);

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
	private Resource askOneResource(){
		Resource requirements = null;
		while(true){
			System.out.println("Insert requirements");
			List<ResourceType> resourceTypes = Arrays.asList(ResourceType.values());
			int i;
			for (i = 0; i < resourceTypes.size(); i++) {
				System.out.println(i + " - " + resourceTypes.get(i).name());
			}

			int resourceInput = scanner.nextInt();

			if(resourceInput < resourceTypes.size()) {
				ResourceType resourceType = resourceTypes.get(resourceInput);

				System.out.println("Insert value for " + resourceType.name());
				requirements = ResourceBuilder.create(resourceType, scanner.nextInt());
			}
			if(requirements != null){
				break;
			}
		}
		scanner.nextLine();
		return requirements;
	}
	private List<Effect> askEffect(int id){
		Effect effect = null;
		Event event;
		int amount = 0;
		List<Effect> effects = new ArrayList<>();
		List<Resource> costs = new ArrayList<>();
		List<Resource> bonus = new ArrayList<>();
		List<ResourceExchange> resourceExchanges = new ArrayList<>();
		ResourceExchange resourceExchange;
		Resource resource;
		CardType cardType;
		Boolean choice = null;
		while(true){
			System.out.println("Insert Effect");
			System.out.println("1 - Effect change Family member Value \n2 - Effect change resource \n 3 - EffectFreeAction \n 4 - EffectResourceForCards \n 5 - EffectResourceForResource \n 8 - Exit create resource tool");
			int effectChoice = scanner.nextInt();
			scanner.nextLine();
			switch(effectChoice){
				case 1:
					System.out.println("insert the new value of the family member");
					amount = scanner.nextInt();
					event = askEvent(id);
					effect = new EffectChangeFamilyMemberValue(event,amount);
					break;
				case 2:
					//chiedo choice se true lista di resourche effects
					while(choice == null) {
						System.out.println("insert true or false for bonus user choice[T/F]");
						String trueorfalse = scanner.nextLine();
						System.out.println(trueorfalse);
						if (trueorfalse.toLowerCase().equals("t")) {
							choice = true;
						}
						if(trueorfalse.toLowerCase().equals("f")){
							choice = false;
						}
					}
					if(choice){
						while(true){
							System.out.println("insert the cost");
							costs = askResource();
							System.out.println("insert bonus");
							bonus = askResource();
							resourceExchange = new ResourceExchange(costs, bonus);
							resourceExchanges.add(resourceExchange);
							System.out.println("Do you want to exit from the choice loop?[Y]");
							String exit = scanner.nextLine();
							if(exit.toLowerCase().equals("y")){
								break;
							}
						}
					}else {
						System.out.println("insert the cost");
						costs = askResource();
						System.out.println("insert bonus");
						bonus = askResource();
						resourceExchange = new ResourceExchange(costs, bonus);
					}
					event = askEvent(id);
					if(choice){
						effect = new EffectChangeResource(event, resourceExchanges, choice);
					}else{
						effect = new EffectChangeResource(event, resourceExchange, choice);
					}
					choice = null;
					break;
				case 3:
					event = askEvent(id);
					cardType = askCardType();
					System.out.println("insert the value of the free action");
					int value = scanner.nextInt();
					//effect = new EffectFreeAction(event, cardType, value); effect constructor has changed
					break;
				case 4:
					event = askEvent(id);
					cardType = askCardType();
					resource = askOneResource();
					effect = new EffectResourceForCards(event, cardType, resource);
					break;
				case 5:
					event = askEvent(id);
					Resource ownedResource = askOneResource();
					resource = askOneResource();
					effect = new EffectResourceForResource(event, ownedResource, resource);
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
	private Event askEvent(int id){
		FamilyMember familyMember;
		List<Occupiable> occupiables;
		Event event = null;
		
		Resource resource;

			System.out.println("Choose the event");

			System.out.println("1 EventPickCard \n 2 EventPlaceFamilyMember \n 3 EventSpendResource \n 4 EventSupportChurch \n 5 EventChooseFamilyMember \n 6 EventEndMatch  \n 16 Redo");

			int eventInput = scanner.nextInt();
			
			switch(eventInput){
				case 1:
					event = new EventPickCard(id);
					break;
				case 2:
					familyMember = askFamilyMember();
					occupiables= askOccupiable();
					event = new EventPlaceFamilyMember(occupiables, familyMember);
					break;
				case 3:
					resource = askOneResource();
					event = new EventSpendResource(resource);
					break;
				case 4:
					event= new EventSupportChurch();
					break;
				case 5:
					familyMember = askFamilyMember();
					event = new EventFamilyMemberChosen(familyMember);
					break;
				case 6:
					event = new EventEndMatch();

			}
		return event;
	}
	
}
