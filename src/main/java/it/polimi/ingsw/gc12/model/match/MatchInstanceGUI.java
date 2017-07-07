package it.polimi.ingsw.gc12.model.match;


import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.card.CardType;
import it.polimi.ingsw.gc12.model.card.LeaderCard;
import it.polimi.ingsw.gc12.model.board.dice.Die;
import it.polimi.ingsw.gc12.model.board.dice.DieColor;
import it.polimi.ingsw.gc12.model.board.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.view.client.gui.representation.*;
import it.polimi.ingsw.gc12.model.board.occupiable.TowerFloor;
import it.polimi.ingsw.gc12.model.board.occupiable.TowerSet;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
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
    //the table view must have all the representation in a single observable list not in 4 different for each player
    private ObservableList<ResourceRepresentation> allResourceRepresentationMilitary = FXCollections.observableArrayList();
    private ObservableList<ResourceRepresentation> allResourceRepresentationVictory = FXCollections.observableArrayList();
    private ObservableList<ResourceRepresentation> allResourceRepresentationFaith = FXCollections.observableArrayList();

    private ObservableList<TurnOrderTrackPositionRepresentation> turnOrderTrackFirstPositionRepresentationObservableList = FXCollections.observableArrayList();

    //excommmunication tile representation
    private ObservableList<ExcommunicationTileRepresentation> excommunicationTileRepresentationObservableList = FXCollections.observableArrayList();
    //card player1
    private ObservableList<CardPlayerRepresentation> territoryCardPlayerRepresentationPl1 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> buildingCardPlayerRepresentationPl1 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> characterCardPlayerRepresentationPl1 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> ventureCardPlayerRepresentationPl1 = FXCollections.observableArrayList();
    private Map<CardType, ObservableList<CardPlayerRepresentation>> mapCardTypeCardPlayerRepresentationPl1 = new HashMap<>();
    //card player2
    private ObservableList<CardPlayerRepresentation> territoryCardPlayerRepresentationPl2 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> buildingCardPlayerRepresentationPl2 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> characterCardPlayerRepresentationPl2 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> ventureCardPlayerRepresentationPl2 = FXCollections.observableArrayList();
    private Map<CardType, ObservableList<CardPlayerRepresentation>> mapCardTypeCardPlayerRepresentationPl2 = new HashMap<>();
    //card player3
    private ObservableList<CardPlayerRepresentation> territoryCardPlayerRepresentationPl3 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> buildingCardPlayerRepresentationPl3 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> characterCardPlayerRepresentationPl3 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> ventureCardPlayerRepresentationPl3 = FXCollections.observableArrayList();
    private Map<CardType, ObservableList<CardPlayerRepresentation>> mapCardTypeCardPlayerRepresentationPl3 = new HashMap<>();
    //card player4
    private ObservableList<CardPlayerRepresentation> territoryCardPlayerRepresentationPl4 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> buildingCardPlayerRepresentationPl4 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> characterCardPlayerRepresentationPl4 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> ventureCardPlayerRepresentationPl4 = FXCollections.observableArrayList();
    private Map<CardType, ObservableList<CardPlayerRepresentation>> mapCardTypeCardPlayerRepresentationPl4 = new HashMap<>();

    private Map<PlayerColor,Map<CardType, ObservableList<CardPlayerRepresentation> > > mapPlayerColorCardTypePlayerCard = new HashMap<>();

    //card Leader Player
    private ObservableList<CardLeaderRepresentation> cardLeaderRepresentationPl1 = FXCollections.observableArrayList();
    private ObservableList<CardLeaderRepresentation> cardLeaderRepresentationPl2 = FXCollections.observableArrayList();
    private ObservableList<CardLeaderRepresentation> cardLeaderRepresentationPl3 = FXCollections.observableArrayList();
    private ObservableList<CardLeaderRepresentation> cardLeaderRepresentationPl4 = FXCollections.observableArrayList();
    private Map<PlayerColor, ObservableList<CardLeaderRepresentation>> mapPlayerColorCardLeaders = new HashMap<>();

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
		createOrderedTruckRepresentation(match);
        createCardTowerFloorRepresentation(match);
		setFamilyMemberObservers();
        createObservableListMilitaryVictoryFaith(match);
        createExcomTileRepresentation(match);
        createCardPlayerRepresentation(match);
        createCardLeaderRepresentation(match);
        setChanged();
		notifyObservers();
		initialized = true;
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
                mapTypeCardFloorRepresentation.get(cardType).get(floor).setPathCard(image);
            }
        }
    }

	@Override
	protected void setFamilyMemberObservers() {
		for(Player player: players.values()) {
			for(FamilyMemberRepresentation familyMember: mapFamilyMember.get(player.getColor())) {
				try{
					DieColor dieColor = DieColor.valueOf(familyMember.getColorsFamilyMemberPropertyString());
					Die die = board.getSpaceDie().getDie(dieColor);
					die.addObserver(familyMember);
				}
				catch (IllegalArgumentException ignored) {}
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
			    String pathFamily = "img/players/"+familyMember.getOwner().getColor().toString()+"/"+familyMember.getOwner().getColor().toString()+"_"+familyMember.getColor().toString()+".png";
				familyMemberRepresentation = new FamilyMemberRepresentation(pathFamily,familyMember.getValue(), familyMember.getColor().toString(), familyMember.getOwner().getColor().toString(), familyMember.isBusy());
				mapFamilyMember.get( familyMember.getOwner().getColor()).add(familyMemberRepresentation);
			}
		}
	}

	private void createCardTowerFloorRepresentation(Match match) {
		mapTypeCardFloorRepresentation.put(CardType.TERRITORY, cardFloorTerritoryRepresentation);
		mapTypeCardFloorRepresentation.put(CardType.BUILDING, cardFloorBuildingRepresentation);
		mapTypeCardFloorRepresentation.put(CardType.CHARACTER, cardFloorCharactherRepresentation);
		mapTypeCardFloorRepresentation.put(CardType.VENTURE, cardFloorVentureRepresentation);
		int realFloor = 0;
		for(CardType cardType : CardType.values()){
			List<TowerFloor> towerFloors = match.getBoard().getTowerSet().getTower(cardType).getFloors();
			for (TowerFloor towerFloor : towerFloors){
				int floor = towerFloor.getFloorNum();
                List<FamilyMember> occupiers = towerFloor.getOccupiers();
                Boolean taken = true;
                if(occupiers == null){
                    taken = false;
                }
				String pathCard = "img/Card/transparentCard.png";//setting trasparent card because the cards arrive with eventStarTurn
                String pathFloor = "img/Floor/floor"+realFloor+".png";
				CardFloorRepresentation cardFloorRepresentation = new CardFloorRepresentation(pathCard,pathFloor, floor, taken);
				mapTypeCardFloorRepresentation.get(cardType).add(cardFloorRepresentation);
				realFloor= realFloor+1;
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
    private void createOrderedTruckRepresentation(Match match){
        List<Player> orderedPlayer = match.getBoard().getTrackTurnOrder().getOrderedPlayers();
        PlayerColorReal playerColorReal = new PlayerColorReal(1);
        TurnOrderTrackPositionRepresentation turnOrderTrackPositionRepresentation = null;
        for(Player player : orderedPlayer){
            PlayerColor playerColor = player.getColor();
            if(playerColor == PlayerColor.BLUE){
                 turnOrderTrackPositionRepresentation = new TurnOrderTrackPositionRepresentation(playerColorReal.getBlue());
            }else if(playerColor == PlayerColor.GREEN){
                 turnOrderTrackPositionRepresentation = new TurnOrderTrackPositionRepresentation(playerColorReal.getGreen());
            }else if(playerColor == PlayerColor.RED){
                 turnOrderTrackPositionRepresentation = new TurnOrderTrackPositionRepresentation(playerColorReal.getRed());
            }else if(playerColor == PlayerColor.YELLOW){
                 turnOrderTrackPositionRepresentation = new TurnOrderTrackPositionRepresentation(playerColorReal.getYellow());
            }
            turnOrderTrackFirstPositionRepresentationObservableList.add(turnOrderTrackPositionRepresentation);
        }

    }
    private void createObservableListMilitaryVictoryFaith(Match match){
        List<Player> players = new ArrayList<>(match.getPlayers().values());
        List<Player> orderedByMilitary = match.getBoard().getTrackMilitaryPoints().getPlayersOrder(players);
        List<Player> orderedByVictory = match.getBoard().getVictroyPointsTrack().getPlayerOrdered(players);
        match.getBoard().getTrackFaithPoints();
        for(Player player : orderedByMilitary){
            allResourceRepresentationMilitary.add(mapPlayerColorResourceRepresentation.get(player.getColor()).get(0));
        }
        for(Player player : orderedByVictory){
            allResourceRepresentationVictory.add(mapPlayerColorResourceRepresentation.get(player.getColor()).get(0));
        }
        for(Player player : match.getPlayers().values()){
            allResourceRepresentationFaith.add(mapPlayerColorResourceRepresentation.get(player.getColor()).get(0));
        }
    }
    private void createExcomTileRepresentation(Match match){
        Map<Integer, ExcommunicationTile> tiles = match.getBoard().getExcommunicationSpace().getTiles();
        for(ExcommunicationTile excommunicationTile : tiles.values()){
            int period = excommunicationTile.getPeriod();
            String path = "img/excomunicationTile/excomm_"+excommunicationTile.getId()+".png";
            ExcommunicationTileRepresentation excomTileR = new ExcommunicationTileRepresentation(period, path);
            excommunicationTileRepresentationObservableList.add(excomTileR);
        }
    }
    public void createCardPlayerRepresentation(Match match){
        mapCardTypeCardPlayerRepresentationPl1.put(CardType.TERRITORY, territoryCardPlayerRepresentationPl1);
        mapCardTypeCardPlayerRepresentationPl1.put(CardType.BUILDING, buildingCardPlayerRepresentationPl1);
        mapCardTypeCardPlayerRepresentationPl1.put(CardType.CHARACTER, characterCardPlayerRepresentationPl1);
        mapCardTypeCardPlayerRepresentationPl1.put(CardType.VENTURE, ventureCardPlayerRepresentationPl1);

        mapCardTypeCardPlayerRepresentationPl2.put(CardType.TERRITORY, territoryCardPlayerRepresentationPl2);
        mapCardTypeCardPlayerRepresentationPl2.put(CardType.BUILDING, buildingCardPlayerRepresentationPl2);
        mapCardTypeCardPlayerRepresentationPl2.put(CardType.CHARACTER, characterCardPlayerRepresentationPl2);
        mapCardTypeCardPlayerRepresentationPl2.put(CardType.VENTURE, ventureCardPlayerRepresentationPl2);

        mapCardTypeCardPlayerRepresentationPl3.put(CardType.TERRITORY, territoryCardPlayerRepresentationPl3);
        mapCardTypeCardPlayerRepresentationPl3.put(CardType.BUILDING, buildingCardPlayerRepresentationPl3);
        mapCardTypeCardPlayerRepresentationPl3.put(CardType.CHARACTER, characterCardPlayerRepresentationPl3);
        mapCardTypeCardPlayerRepresentationPl3.put(CardType.VENTURE, ventureCardPlayerRepresentationPl3);

        mapCardTypeCardPlayerRepresentationPl4.put(CardType.TERRITORY, territoryCardPlayerRepresentationPl4);
        mapCardTypeCardPlayerRepresentationPl4.put(CardType.BUILDING, buildingCardPlayerRepresentationPl4);
        mapCardTypeCardPlayerRepresentationPl4.put(CardType.CHARACTER, characterCardPlayerRepresentationPl4);
        mapCardTypeCardPlayerRepresentationPl4.put(CardType.VENTURE, ventureCardPlayerRepresentationPl4);

        mapPlayerColorCardTypePlayerCard.put(PlayerColor.BLUE, mapCardTypeCardPlayerRepresentationPl1);
        mapPlayerColorCardTypePlayerCard.put(PlayerColor.GREEN, mapCardTypeCardPlayerRepresentationPl2);
        mapPlayerColorCardTypePlayerCard.put(PlayerColor.RED, mapCardTypeCardPlayerRepresentationPl3);
        mapPlayerColorCardTypePlayerCard.put(PlayerColor.YELLOW, mapCardTypeCardPlayerRepresentationPl4);
        for(Player player : players.values()) {

            for (CardType cardType : CardType.values()) {
                for (int i = 0; i < 6; i++) {
                    CardPlayerRepresentation cardPlayerRepresentation = new CardPlayerRepresentation("img/Card/transparentCard.png");
                    mapPlayerColorCardTypePlayerCard.get(player.getColor()).get(cardType).add(cardPlayerRepresentation);
                }
            }
        }
    }
    public void createCardLeaderRepresentation(Match match){
        mapPlayerColorCardLeaders.put(PlayerColor.BLUE, cardLeaderRepresentationPl1);
        mapPlayerColorCardLeaders.put(PlayerColor.GREEN, cardLeaderRepresentationPl2);
        mapPlayerColorCardLeaders.put(PlayerColor.RED, cardLeaderRepresentationPl3);
        mapPlayerColorCardLeaders.put(PlayerColor.YELLOW, cardLeaderRepresentationPl4);

        for(Player player : match.getPlayers().values()){
            for(LeaderCard leaderCard : player.getPersonalBoard().getLeaderCards()){
                String url = "img/CardLeader/leader_"+leaderCard.getId()+".jpg";
                CardLeaderRepresentation cardLeaderRepresentation = new CardLeaderRepresentation(url, "img/CardLeader/baseLeader.jpg");
                mapPlayerColorCardLeaders.get(player.getColor()).add(cardLeaderRepresentation);
            }
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

    public ObservableList<TurnOrderTrackPositionRepresentation> getTurnOrderTrackFirstPositionRepresentationObservableList() {
        return turnOrderTrackFirstPositionRepresentationObservableList;
    }

    public ObservableList<ResourceRepresentation> getAllResourceRepresentationMilitary() {
        return allResourceRepresentationMilitary;
    }

    public ObservableList<ResourceRepresentation> getAllResourceRepresentationVictory() {
        return allResourceRepresentationVictory;
    }

    public ObservableList<ExcommunicationTileRepresentation> getExcommunicationTileRepresentationObservableList() {
        return excommunicationTileRepresentationObservableList;
    }

    public Map<PlayerColor, Map<CardType, ObservableList<CardPlayerRepresentation>>> getMapPlayerColorCardTypePlayerCard() {
        return mapPlayerColorCardTypePlayerCard;
    }

    public Map<PlayerColor, ObservableList<CardLeaderRepresentation>> getMapPlayerColorCardLeaders() {
        return mapPlayerColorCardLeaders;
    }

    public ObservableList<ResourceRepresentation> getAllResourceRepresentationFaith() {
        return allResourceRepresentationFaith;
    }

    public void notifyInit() {
    	setChanged();
    	notifyObservers();
	}
}
