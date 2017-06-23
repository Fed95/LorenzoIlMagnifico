package it.polimi.ingsw.gc_12.json.saver;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.FamilyMemberColor;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.effect.*;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc_12.json.loader.LoaderCard;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.json.loader.LoaderExcommmunications;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.card.*;
import it.polimi.ingsw.gc_12.resource.*;

import java.io.File;
import java.util.*;

public class SaverExcommunications extends Saver<List<ExcommunicationTile>> {

    Scanner scanner = new Scanner(System.in);
    private Match match;
    public SaverExcommunications(Match match){
        super("excommunications");
        this.match = match;
    }

    @Override
    public void create(){

        int id = -1;
        List<ExcommunicationTile> tiles = new ArrayList<>();
        File file = new File("excommunications.JSON");

        if(file.exists()){
            System.out.println("entro in file exist");
            //taking the last id from the existing file
            List<ExcommunicationTile> exOld = new LoaderExcommmunications().get(match);
            for(ExcommunicationTile excommunicationTile : exOld){
                id = excommunicationTile.getId();
                System.out.println("id: " + id);
            }
            System.out.println("incrementero questo id: " + id);
        }
        while(true){
            id++;
            int period = askPeriod();
            List<Effect> effects = askEffect(id);

            ExcommunicationTile tile = new ExcommunicationTile(id, period, effects);
            tiles.add(tile);

            System.out.println("dimensione tiles che sto scrivendo: " + tiles.size());
            System.out.println("id che sto scrivendo" + id);

            super.save(tiles, new CardExclusionStrategy());
            tiles = new ArrayList<>();
            System.out.println("tile scritta");
            System.out.println("nuova lista: " + tiles.size());

            System.out.println("Do you want to exit from creating card tool?[y/n]");

            String choice = scanner.nextLine();
            if(choice.toLowerCase().equals("y")) {
                break;
            }

        }
        scanner.close();
    }

    private int askPeriod() {
        int period;
        do {
            System.out.println("Choose the period of the tile");
            period = scanner.nextInt();
        }
        while(period <= 0 || period > 3); //do nothing

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

    private ResourceType askResourceType(){
        System.out.println("Resource type for the event connected?");
        List<ResourceType> types = new ArrayList<>();
        int i = 0;
        for(ResourceType rt : ResourceType.values()){
            System.out.println(i + " - " + rt);
            types.add(rt);
            i++;
        }
        int choice = scanner.nextInt();
        return types.get(choice);
    }

    private int askValue(){
        System.out.println("Value?");
        int choice = scanner.nextInt();
        return choice;
    }

    private EffectProvider askEffectProvider(){
        System.out.println("Choose the effect provider:");
        List<CardType> types = Arrays.asList(CardType.values());
        int i = 0;
        for(CardType cardType : CardType.values()) {
            System.out.println(i + " - " + cardType);
            i++;
        }
        int choice = scanner.nextInt();
        return CardBuilder.create(types.get(choice));
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
        EffectProvider effectProvider;
        Resource resource;
        CardType cardType;
        Boolean choice = null;
        while(true){
            System.out.println("Insert Effect");
            System.out.println("1 - Effect change Family member Value");
            System.out.println("2 - Effect ReducedReceivedResource");
            System.out.println("3 - EffectDenyEffect");
            System.out.println("4 - EffectResourceForResource");
            System.out.println("5 - EffectServantsMultiplier");
            System.out.println("6 - Deny Placement");
            System.out.println("7 - Exit");

            int effectChoice = scanner.nextInt();

            scanner.nextLine();
            switch(effectChoice){
                case 1:
                    System.out.println("insert the increment of the family member value");
                    amount = scanner.nextInt();
                    event = askEvent(id);
                    effects.add(new EffectChangeFamilyMemberValue(event,amount));
                    break;
                case 2:
                    ResourceType resourceType = askResourceType();
                    int value = askValue();

                    event = new EventReceiveResource(ResourceBuilder.create(resourceType, 0));
                    effects.add(new EffectReduceReceivedResource(event, value));
                    break;

                case 3:
                    effectProvider = askEffectProvider();
                    effects.add(new EffectDenyEffect(new EventEndMatch(), effectProvider));
                    break;

                case 4:
                    Resource ownedresource = askOneResource();
                    effects.add(new EffectResourceForResource(new EventEndMatch(), ownedresource, new VictoryPoint(-1)));
                    break;

                case 5:
                    value = askValue();
                    effects.add(new EffectServantsMultipier(new EventPlaceFamilyMember(), value));
                    //effects.add(new EffectServantsMultipier(new EventServantsRequested(), value));
                    break;

                case 6:
                    event = askEvent(id);
                    effects.add(new EffectPlacementDenied(event));

            }
            if(effectChoice == 7){
                break;
            }
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
