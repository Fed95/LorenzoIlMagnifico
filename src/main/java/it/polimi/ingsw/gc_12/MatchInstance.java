package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.card.CardDevelopment;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.java_fx.CardFloorRepresentation;
import it.polimi.ingsw.gc_12.java_fx.FamilyMemberRepresentation;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.stream.Collectors;

public class MatchInstance extends Observable implements Serializable, Cloneable {
	private Board board;
	private int roundNum;
	public transient final static int DEFAULT_ROUND_NUM = 6;
	public transient final static int DEFAULT_PERIODS_LEN = 2;
	public transient final static int DEFAULT_TOTAL_PERIODS_NUM = DEFAULT_ROUND_NUM/DEFAULT_PERIODS_LEN;
	private static MatchInstance instance;
	//Observable and Map for family member
	private ObservableList<FamilyMemberRepresentation> familyMemberBlueRepresentationObservableList = FXCollections.observableArrayList();
	private ObservableList<FamilyMemberRepresentation> familyMemberGreenRepresentationObservableList = FXCollections.observableArrayList();
	private ObservableList<FamilyMemberRepresentation> familyMemberRedRepresentationObservableList = FXCollections.observableArrayList();
	private ObservableList<FamilyMemberRepresentation> familyMemberYellowRepresentationObservableList = FXCollections.observableArrayList();
	private Map<PlayerColor, ObservableList<FamilyMemberRepresentation>> mapFamilyMember = new HashMap<>();

    private Map<PlayerColor, Player> players;

	//Observable and Map cardFloor
    private ObservableList<CardFloorRepresentation> cardFloorTerritoryRepresentation = FXCollections.observableArrayList();
    private ObservableList<CardFloorRepresentation> cardFloorCharactherRepresentation = FXCollections.observableArrayList();
    private ObservableList<CardFloorRepresentation> cardFloorVentureRepresentation = FXCollections.observableArrayList();
    private ObservableList<CardFloorRepresentation> cardFloorBuildingRepresentation = FXCollections.observableArrayList();

    private Map<CardType,ObservableList<CardFloorRepresentation>> mapTypeCardFloorRepresentation = new HashMap<>();


	private MatchInstance() {}
	private FamilyMemberRepresentation familyMemberRepresentation;
	public static MatchInstance instance() {
		if(instance == null) instance = new MatchInstance();
		return instance;

	}

	public void init(Match match) {
		this.board = match.getBoard();
		this.roundNum = 1;
		this.players = match.getPlayers();

		createFamilyMemberRepresentation(match);
        createCardTowerFloorRepresentation(match);
		setChanged();
		notifyObservers();
	}



    public Board getBoard() {
		return board;
	}

    public Map<PlayerColor, Player> getPlayers() {
        return players;
    }

    public void newTurn() {
		board.getTrackTurnOrder().newTurn();
	}

	public void placeFamilyMember(Occupiable occupiable, FamilyMember familyMember) {
		getOccupiable(occupiable).placeFamilyMember(familyMember);
		if(getFamilyMembers(familyMember).size() > 0) {
			getFamilyMembers(familyMember).get(0).setBusy(true);
		}

	}

	private Occupiable getOccupiable(Occupiable occupiable) {
		//System.out.println("MatchInstance: retrieving the occupiable");
		List<Occupiable> occupiables =  board.getOccupiables().stream().filter(myOccupiable -> myOccupiable.equals(occupiable)).collect(Collectors.toList());
		//System.out.println("MatchInstance: occupiable found: " + occupiables);
		return occupiables.get(0);
	}

	private List<FamilyMember> getFamilyMembers(FamilyMember familyMember) {
		return board.getTrackTurnOrder().getCurrentPlayer().getFamilyMembers().values().stream()
				.filter(myFamilyMember -> myFamilyMember.equals(familyMember)).collect(Collectors.toList());
	}

	public int getRoundNum() {
		return roundNum;
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
