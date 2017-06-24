package it.polimi.ingsw.gc_12;


import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.java_fx.CardFloorRepresentation;
import it.polimi.ingsw.gc_12.java_fx.FamilyMemberRepresentation;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

	private MatchInstanceGUI() {}

	public static MatchInstanceGUI instance() {
		if(instance == null) instance = new MatchInstanceGUI();
		return instance;
	}

	@Override
	public void init(Match match) {
		super.init(match);
		createFamilyMemberRepresentation(match);
		createCardTowerFloorRepresentation(match);
		setChanged();
		notifyObservers();
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
		//prendere le carte divise nei 4 piani
		for(CardType cardType : CardType.values()){
			List<TowerFloor> towerFloors = match.getBoard().getTowerSet().getTower(cardType).getFloors();
			for (TowerFloor towerFloor : towerFloors){
				//prendi id carta e metterlo nel path creando una nuova card floor aggiungerl poi alla mappa
				int floor = towerFloor.getFloorNum();
				CardDevelopment cardonthefloor = towerFloor.getCard();
				int cardid = cardonthefloor.getId();
				String path = "img/Card/card_"+cardid+".png";
				CardFloorRepresentation cardFloorRepresentation = new CardFloorRepresentation(path, floor);
				mapTypeCardFloorRepresentation.get(cardType).add(cardFloorRepresentation);
			}
		}
	}

	public Map<PlayerColor, ObservableList<FamilyMemberRepresentation>> getMapPlayerColorObservableLiseFMRepr() {
		return mapFamilyMember;
	}
	public Map<CardType, ObservableList<CardFloorRepresentation>> getMapTypeCardFloorRepresentation() {
		return mapTypeCardFloorRepresentation;
	}
}
