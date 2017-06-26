package it.polimi.ingsw.gc_12;


import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.java_fx.CardFloorRepresentation;
import it.polimi.ingsw.gc_12.java_fx.FamilyMemberRepresentation;
import it.polimi.ingsw.gc_12.java_fx.ResourceRepresentation;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;
import it.polimi.ingsw.gc_12.occupiables.TowerSet;
import it.polimi.ingsw.gc_12.resource.Resource;
import it.polimi.ingsw.gc_12.resource.ResourceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchInstanceGUI extends MatchInstance {

	private static MatchInstanceGUI instance;

	//Observable and Map for family member
	private ObservableList<FamilyMemberRepresentation> familyMemberBlueRepresentationObservableList = FXCollections.observableArrayList();
	private ObservableList<FamilyMemberRepresentation> familyMemberGreenRepresentationObservableList = FXCollections.observableArrayList();
	private ObservableList<FamilyMemberRepresentation> familyMemberRedRepresentationObservableList = FXCollections.observableArrayList();
	private ObservableList<FamilyMemberRepresentation> familyMemberYellowRepresentationObservableList = FXCollections.observableArrayList();
	private Map<PlayerColor, ObservableList<FamilyMemberRepresentation>> mapFamilyMember = new HashMap<>();

	//Observable and Map cardFloor
	private ObservableList<CardFloorRepresentation> cardFloorTerritoryRepresentation = FXCollections.observableArrayList();
	private ObservableList<CardFloorRepresentation> cardFloorCharactherRepresentation = FXCollections.observableArrayList();
	private ObservableList<CardFloorRepresentation> cardFloorVentureRepresentation = FXCollections.observableArrayList();
	private ObservableList<CardFloorRepresentation> cardFloorBuildingRepresentation = FXCollections.observableArrayList();
	private Map<CardType,ObservableList<CardFloorRepresentation>> mapTypeCardFloorRepresentation = new HashMap<>();

	private FamilyMemberRepresentation familyMemberRepresentation;

    //Observable and map for resources
    private ObservableList<ResourceRepresentation> resourceBlueRepresentationObservableList = FXCollections.observableArrayList();
    private ObservableList<ResourceRepresentation> resourceGreenRepresentationObservableList = FXCollections.observableArrayList();
    private ObservableList<ResourceRepresentation> resourceRedRepresentationObservableList = FXCollections.observableArrayList();
    private ObservableList<ResourceRepresentation> resourceYellowRepresentationObservableList = FXCollections.observableArrayList();
    private Map<PlayerColor, ObservableList<ResourceRepresentation>> mapPlayerColorResourceRepresentation = new HashMap<>();

    private MatchInstanceGUI() {}

	public static MatchInstanceGUI instance() {
		if(instance == null) instance = new MatchInstanceGUI();
		return instance;
	}

	@Override
	public void init(Match match) {
		super.init(match);
		createFamilyMemberRepresentation(match);
		createResourceRepresentation(match);
        createCardTowerFloorRepresentation(match);
        setChanged();
		notifyObservers();
	}

    @Override
    public void setCards(TowerSet towers) {
        for(CardType cardType : CardType.values()){
            List<TowerFloor> towerFloors = towers.getTower(cardType).getFloors();
            for (TowerFloor towerFloor : towerFloors){
                CardDevelopment cardOnTheFloor = towerFloor.getCard();
                int cardId = cardOnTheFloor.getId();
                int floor = towerFloor.getFloorNum();
                String path = "img/Card/card_"+cardId+".png";//setting trasparent card because the cards arrive with eventStarTurn
                Image image = new Image(path);
                mapTypeCardFloorRepresentation.get(cardType).get(floor).setPath(image);
            }
        }
    }

    private void createFamilyMemberRepresentation(Match match){
		Map<PlayerColor, Player> players = match.getPlayers();
		for(Player player : players.values()){
			if(player.getColor().equals(PlayerColor.BLUE)){
				mapFamilyMember.put(PlayerColor.BLUE, familyMemberBlueRepresentationObservableList);
			}
			if(player.getColor().equals(PlayerColor.GREEN)){
				mapFamilyMember.put(PlayerColor.GREEN, familyMemberGreenRepresentationObservableList);
			}
			if(player.getColor().equals(PlayerColor.RED)){
				mapFamilyMember.put(PlayerColor.RED, familyMemberRedRepresentationObservableList);
			}
			if(player.getColor().equals(PlayerColor.YELLOW)){
				mapFamilyMember.put(PlayerColor.YELLOW, familyMemberYellowRepresentationObservableList);
			}
		}

		for(Player player : players.values()) {
			List<FamilyMember> familyMembers = player.getAvailableFamilyMembers();
			for (FamilyMember familyMember : familyMembers) {
				familyMemberRepresentation = new FamilyMemberRepresentation(familyMember.getValue(), familyMember.getColor().toString(), familyMember.getOwner().getColor().toString(), familyMember.isBusy());
				mapFamilyMember.get( familyMember.getOwner().getColor()).add(familyMemberRepresentation);
			}
		}
	}

	private void createCardTowerFloorRepresentation(Match match) {
		mapTypeCardFloorRepresentation.put(CardType.TERRITORY, cardFloorTerritoryRepresentation);
		mapTypeCardFloorRepresentation.put(CardType.BUILDING, cardFloorBuildingRepresentation);
		mapTypeCardFloorRepresentation.put(CardType.CHARACTER, cardFloorCharactherRepresentation);
		mapTypeCardFloorRepresentation.put(CardType.VENTURE, cardFloorVentureRepresentation);
		for(CardType cardType : CardType.values()){
			List<TowerFloor> towerFloors = match.getBoard().getTowerSet().getTower(cardType).getFloors();
			for (TowerFloor towerFloor : towerFloors){
				int floor = towerFloor.getFloorNum();
                List<FamilyMember> occupiers = towerFloor.getOccupiers();
                Boolean taken = true;
                if(occupiers == null){
                    taken = false;
                }
				String path = "img/Card/transparentCard.png";//setting trasparent card because the cards arrive with eventStarTurn
				CardFloorRepresentation cardFloorRepresentation = new CardFloorRepresentation(path, floor, taken);
				mapTypeCardFloorRepresentation.get(cardType).add(cardFloorRepresentation);
			}
		}
	}
    private void createResourceRepresentation(Match match){
        mapPlayerColorResourceRepresentation.put(PlayerColor.BLUE, resourceBlueRepresentationObservableList);
        mapPlayerColorResourceRepresentation.put(PlayerColor.GREEN, resourceGreenRepresentationObservableList);
        mapPlayerColorResourceRepresentation.put(PlayerColor.RED, resourceRedRepresentationObservableList);
        mapPlayerColorResourceRepresentation.put(PlayerColor.YELLOW, resourceYellowRepresentationObservableList);
        Map<PlayerColor, Player> players = match.getPlayers();
	    for(Player player : players.values()){
            PlayerColor playerColor = player.getColor();
            Map<ResourceType, Resource> resources = player.getResources();
            ResourceRepresentation resourceRepresentation = new ResourceRepresentation(playerColor.toString(), resources.get(ResourceType.STONE).getValue(), resources.get(ResourceType.MONEY).getValue(), resources.get(ResourceType.SERVANT).getValue(), resources.get(ResourceType.WOOD).getValue(), resources.get(ResourceType.VICTORY_POINT).getValue(), resources.get(ResourceType.MILITARY_POINT).getValue(), resources.get(ResourceType.FAITH_POINT).getValue(), resources.get(ResourceType.COUNCIL_PRIVILEGE).getValue());
            mapPlayerColorResourceRepresentation.get(playerColor).add(resourceRepresentation);
        }
    }
	public Map<PlayerColor, ObservableList<FamilyMemberRepresentation>> getMapPlayerColorObservableLiseFMRepr() {
		return mapFamilyMember;
	}
	public Map<CardType, ObservableList<CardFloorRepresentation>> getMapTypeCardFloorRepresentation() {
		return mapTypeCardFloorRepresentation;
	}

    public Map<PlayerColor, ObservableList<ResourceRepresentation>> getMapPlayerColorResourceRepresentation() {
        return mapPlayerColorResourceRepresentation;
    }
}
