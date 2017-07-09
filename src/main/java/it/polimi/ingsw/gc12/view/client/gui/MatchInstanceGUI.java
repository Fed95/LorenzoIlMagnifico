package it.polimi.ingsw.gc12.view.client.gui;


import it.polimi.ingsw.gc12.misc.json.loader.LoaderConfigPlayers;
import it.polimi.ingsw.gc12.misc.observer.Observable;
import it.polimi.ingsw.gc12.model.board.occupiable.*;
import it.polimi.ingsw.gc12.model.board.track.TrackTurnOrder;
import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.card.CardType;
import it.polimi.ingsw.gc12.model.card.LeaderCard;
import it.polimi.ingsw.gc12.model.board.dice.Die;
import it.polimi.ingsw.gc12.model.board.dice.DieColor;
import it.polimi.ingsw.gc12.model.board.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc12.model.match.ConfigPlayers;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc12.view.client.MatchInstance;
import it.polimi.ingsw.gc12.view.client.gui.representation.*;
import it.polimi.ingsw.gc12.model.player.resource.Resource;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchInstanceGUI extends MatchInstance {

	private static MatchInstanceGUI instance;

	//Observable and Map for family member
	private ObservableList<FamilyMemberRepresentation> familyMembersBluePlayer = FXCollections.observableArrayList();
	private ObservableList<FamilyMemberRepresentation> familyMembersGreenPlayer = FXCollections.observableArrayList();
	private ObservableList<FamilyMemberRepresentation> familyMembersPlayerRed = FXCollections.observableArrayList();
	private ObservableList<FamilyMemberRepresentation> familyMembersPlayerYellow = FXCollections.observableArrayList();
	private Map<PlayerColor, ObservableList<FamilyMemberRepresentation>> familyMembers = new HashMap<>();

	//Observable and Map cardFloor
	private ObservableList<CardFloorRepresentation> cardsFloorsTerritory = FXCollections.observableArrayList();
	private ObservableList<CardFloorRepresentation> cardsFloorsCharacther = FXCollections.observableArrayList();
	private ObservableList<CardFloorRepresentation> cardsFloorsVenture = FXCollections.observableArrayList();
	private ObservableList<CardFloorRepresentation> cardsFloorsBuilding = FXCollections.observableArrayList();
	private Map<CardType,ObservableList<CardFloorRepresentation>> cardsFloors = new HashMap<>();

    //Observable and map for resources

    private ObservableMap<ResourceType, ResourceRepresentation> resourcesPlayer1 = FXCollections.observableHashMap();
    private ObservableMap<ResourceType, ResourceRepresentation> resourcesPlayer2 = FXCollections.observableHashMap();
    private ObservableMap<ResourceType, ResourceRepresentation> resourcesPlayer3 = FXCollections.observableHashMap();
    private ObservableMap<ResourceType, ResourceRepresentation> resourcesPlayer4 = FXCollections.observableHashMap();
    private Map<PlayerColor, ObservableMap<ResourceType, ResourceRepresentation>> resourcesPlayers = new HashMap<>();
    //necessary for listView java fx
    private ObservableList<ResourceRepresentation> militaryResources = FXCollections.observableArrayList();
    private ObservableList<ResourceRepresentation> victoryResources = FXCollections.observableArrayList();
    private ObservableList<ResourceRepresentation> faithResources = FXCollections.observableArrayList();

    private ObservableList<TurnOrderTrackPositionRepresentation> turnOrderTracks = FXCollections.observableArrayList();

    //excommmunication tile representation
    private ObservableList<ExcommunicationTileRepresentation> excommunicationTiles = FXCollections.observableArrayList();
    //card player1
    private ObservableList<CardPlayerRepresentation> territoryCardsPlayer1 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> buildingCardsPlayer1 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> characterCardsPlayer1 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> ventureCardsPlayer1 = FXCollections.observableArrayList();
    private Map<CardType, ObservableList<CardPlayerRepresentation>> cardsPlayer1 = new HashMap<>();
    //card player2
    private ObservableList<CardPlayerRepresentation> territoryCardsPlayer2 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> buildingCardsPlayer2 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> characterCardsPlayer2 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> ventureCardsPlayer2 = FXCollections.observableArrayList();
    private Map<CardType, ObservableList<CardPlayerRepresentation>> cardsPlayer2 = new HashMap<>();
    //card player3
    private ObservableList<CardPlayerRepresentation> territoryCardsPlayer3 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> buildingCardsPlayer3 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> characterCardsPlayer3 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> ventureCardsPlayer3 = FXCollections.observableArrayList();
    private Map<CardType, ObservableList<CardPlayerRepresentation>> cardsPlayer3 = new HashMap<>();
    //card player4
    private ObservableList<CardPlayerRepresentation> territoryCardsPlayer4 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> buildingCardsPlayer4 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> characterCardsPlayer4 = FXCollections.observableArrayList();
    private ObservableList<CardPlayerRepresentation> ventureCardsPlayer4 = FXCollections.observableArrayList();
    private Map<CardType, ObservableList<CardPlayerRepresentation>> cardsPlayer4 = new HashMap<>();

    private Map<PlayerColor,Map<CardType, ObservableList<CardPlayerRepresentation> > > cardsPlayers = new HashMap<>();

    //card Leader Player
    private ObservableList<CardLeaderRepresentation> cardsLeaderPl1 = FXCollections.observableArrayList();
    private ObservableList<CardLeaderRepresentation> cardsLeaderPl2 = FXCollections.observableArrayList();
    private ObservableList<CardLeaderRepresentation> cardsLeaderPl3 = FXCollections.observableArrayList();
    private ObservableList<CardLeaderRepresentation> cardsLeaderPl4 = FXCollections.observableArrayList();
    private Map<PlayerColor, ObservableList<CardLeaderRepresentation>> cardsLeaderPlayers = new HashMap<>();
    //council pawn family
    private ObservableList<CouncilPawnFamily> councilPawns = FXCollections.observableArrayList();

    //workspaces
    private ObservableList<WorkSpacePawn> productionPawn = FXCollections.observableArrayList();
    private ObservableList<WorkSpacePawn> harvestPawn = FXCollections.observableArrayList();

    private Map<WorkType, ObservableList<WorkSpacePawn>> workSpacesPawn = new HashMap<>();

    //markets
    private  ObservableList<MarketRepresentation> markets = FXCollections.observableArrayList();

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
        createPawnCouncil();
        createWorkSpacesPawn();
        createMarketRepresentation();
        setChanged();
		notifyObservers();
		initialized = true;
	}

	@Override
	public void pickCard(CardDevelopment card, PlayerColor playerColor) {
		for(Tower tower: board.getTowerSet().getTowers().values()) {
			List<TowerFloor> floors = tower.getFloors();
			for (int i = 0; i < floors.size(); i++) {
				if(floors.get(i).getCard().equals(card)) {
					cardsFloors.get(card.getType()).get(i).removeCard();
				}
			}
		}
		for(CardPlayerRepresentation cardRepr: cardsPlayers.get(playerColor).get(card.getType())) {
			if(!cardRepr.isOccupied()) {
				cardRepr.placeCard(card);
				break;
			}
		}

	}

	@Override
	public void placeFamilyMember(FamilyMember familyMember, Occupiable occupiable, PlayerColor playerColor) {
		for(FamilyMemberRepresentation FMRepr: familyMembers.get(playerColor)) {
			if(FMRepr.getColor().equals(familyMember.getColor())) {
				FMRepr.setVisible(false);
				if(occupiable instanceof TowerFloor) {
					TowerFloor floor = (TowerFloor) occupiable;
					cardsFloors.get(floor.getType()).get(floor.getFloorNum()).setFamilyMember(familyMember, playerColor);
					break;
				}
				else if(occupiable instanceof CouncilPalace) {
					for(CouncilPawnFamily councilPawn: councilPawns) {
						if(!councilPawn.isOccupied()) {
							councilPawn.setFamilyMember(familyMember, playerColor);
							break;
						}
					}
				}
				else if(occupiable instanceof SpaceWork) {
					for(WorkSpacePawn workSpacePawn: workSpacesPawn.get(((SpaceWork) occupiable).getWorkType())) {
						if(!workSpacePawn.isOccupied()) {
							workSpacePawn.setFamilyMember(familyMember, playerColor);
							break;
						}
					}
				}
				else if(occupiable instanceof SpaceMarket) {
					SpaceMarket spaceMarket = (SpaceMarket) occupiable;
					markets.get(spaceMarket.getMarketNum()).setFamilyMember(familyMember, playerColor);
				}
			}
		}
	}

	@Override
	public void resetFamilyMembers() {
		for(ObservableList<FamilyMemberRepresentation> FMRepresentations: familyMembers.values()) {
			for(FamilyMemberRepresentation familyMember: FMRepresentations) {
				familyMember.setVisible(true);
			}
		}
	}

	@Override
	public void resetFloors() {
		for(ObservableList<CardFloorRepresentation> floors: cardsFloors.values()) {
			for(CardFloorRepresentation floor: floors) {
				floor.setPathFloor(new Image("img/Floor/floor"+floor.getFloorCount()+".png"));
			}
		}
	}

	@Override
	public void updateResources(List<Player> players) {
		Platform.runLater(() -> {
			for(Player player: players) {
				for(Resource resource: player.getResources().values()) {
					resourcesPlayers.get(player.getColor()).get(resource.getType()).setResourceValue(resource.getValue());
				}
			}
		});
	}

	@Override
	public void playerExcommunication(Player player, ExcommunicationTile tile) {
		for(ExcommunicationTileRepresentation excomTile: excommunicationTiles) {
			if(excomTile.getPeriod() == tile.getPeriod())
				excomTile.showExcommunication(player.getColor());
		}
	}

	@Override
	public void activateLeaderCard(PlayerColor playerColor, int id) {
		for(CardLeaderRepresentation card: cardsLeaderPlayers.get(playerColor)) {
			if(card.getId() == id) {
				card.activate();
			}
		}
	}

	@Override
	public void discardLeaderCard(PlayerColor playerColor, int id) {
		for(CardLeaderRepresentation card: cardsLeaderPlayers.get(playerColor)) {
			if(card.getId() == id) {
				card.discard();
			}
		}
	}

	@Override
	public void setTurnOrder(TrackTurnOrder turnOrder) {
    	PlayerColorReal playerColorReal = new PlayerColorReal(1);
    	List<Player> orderedPlayers = turnOrder.getOrderedPlayers();
		for (int i = 0; i < orderedPlayers.size(); i++) {
			turnOrderTracks.get(i).setPlayerProperty(orderedPlayers.get(i).getColor(), playerColorReal);
		}
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
                cardsFloors.get(cardType).get(floor).setPathCard(image);
            }
        }
    }

	@Override
	protected void setFamilyMemberObservers() {
		for(Player player: players.values()) {
			for(FamilyMemberRepresentation familyMember: familyMembers.get(player.getColor())) {
				try{
					DieColor dieColor = DieColor.valueOf(familyMember.getColor().toString());
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
				familyMembers.put(PlayerColor.BLUE, familyMembersBluePlayer);
			}
			if(player.getColor().equals(PlayerColor.GREEN)){
				familyMembers.put(PlayerColor.GREEN, familyMembersGreenPlayer);
			}
			if(player.getColor().equals(PlayerColor.RED)){
				familyMembers.put(PlayerColor.RED, familyMembersPlayerRed);
			}
			if(player.getColor().equals(PlayerColor.YELLOW)){
				familyMembers.put(PlayerColor.YELLOW, familyMembersPlayerYellow);
			}
		}

		for(Player player : players.values()) {
			List<FamilyMember> familyMembers = player.getAvailableFamilyMembers();
			for (FamilyMember familyMember : familyMembers) {
			    String pathFamily = "img/players/"+familyMember.getOwner().getColor().toString()+"/"+familyMember.getOwner().getColor().toString()+"_"+familyMember.getColor().toString()+".png";
				FamilyMemberRepresentation familyMemberRepresentation = new FamilyMemberRepresentation(pathFamily,familyMember.getValue(), familyMember.getColor(), familyMember.getOwner().getColor());
				this.familyMembers.get( familyMember.getOwner().getColor()).add(familyMemberRepresentation);
			}
		}
	}

	private void createCardTowerFloorRepresentation(Match match) {
		cardsFloors.put(CardType.TERRITORY, cardsFloorsTerritory);
		cardsFloors.put(CardType.BUILDING, cardsFloorsBuilding);
		cardsFloors.put(CardType.CHARACTER, cardsFloorsCharacther);
		cardsFloors.put(CardType.VENTURE, cardsFloorsVenture);
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
				CardFloorRepresentation cardFloorRepresentation = new CardFloorRepresentation(pathCard,pathFloor, floor, taken, realFloor);
				cardsFloors.get(cardType).add(cardFloorRepresentation);
				realFloor= realFloor+1;
			}
		}
	}

    private void createResourceRepresentation(Match match){
        resourcesPlayers.put(PlayerColor.BLUE, resourcesPlayer1);
        resourcesPlayers.put(PlayerColor.GREEN, resourcesPlayer2);
        resourcesPlayers.put(PlayerColor.RED, resourcesPlayer3);
        resourcesPlayers.put(PlayerColor.YELLOW, resourcesPlayer4);
        Map<PlayerColor, Player> players = match.getPlayers();
	    for(Player player : players.values()){
            PlayerColor playerColor = player.getColor();
            Map<ResourceType, Resource> resources = player.getResources();
            for(ResourceType resourceType : ResourceType.values()){
                ResourceRepresentation resourceRepresentation = new ResourceRepresentation(playerColor, resources.get(resourceType).getValue(), resourceType);
                resourcesPlayers.get(playerColor).put(resourceType, resourceRepresentation);
            }
        }
    }

    private void createOrderedTruckRepresentation(Match match){
        List<Player> orderedPlayer = match.getBoard().getTrackTurnOrder().getOrderedPlayers();
        PlayerColorReal playerColorReal = new PlayerColorReal(1);
        TurnOrderTrackPositionRepresentation turnOrderTrackPositionRepresentation = null;
        for(Player player : orderedPlayer){
            PlayerColor playerColor = player.getColor();
            turnOrderTrackPositionRepresentation = new TurnOrderTrackPositionRepresentation(playerColor, playerColorReal);
            turnOrderTracks.add(turnOrderTrackPositionRepresentation);
        }

    }

    private void createObservableListMilitaryVictoryFaith(Match match){
        List<Player> players = new ArrayList<>(match.getPlayers().values());
        List<Player> orderedByMilitary = match.getBoard().getTrackMilitaryPoints().getPlayersOrder(players);
        List<Player> orderedByVictory = match.getBoard().getVictroyPointsTrack().getPlayerOrdered(players);
        match.getBoard().getTrackFaithPoints();
        for(Player player : orderedByMilitary){
            militaryResources.add(resourcesPlayers.get(player.getColor()).get(ResourceType.MILITARY_POINT));
        }
        for(Player player : orderedByVictory){
            victoryResources.add(resourcesPlayers.get(player.getColor()).get(ResourceType.VICTORY_POINT));
        }
        for(Player player : match.getPlayers().values()){
            faithResources.add(resourcesPlayers.get(player.getColor()).get(ResourceType.FAITH_POINT));
        }
    }

    private void createExcomTileRepresentation(Match match){
        Map<Integer, ExcommunicationTile> tiles = match.getBoard().getExcommunicationSpace().getTiles();
        for(ExcommunicationTile excommunicationTile : tiles.values()){
            int period = excommunicationTile.getPeriod();
            String path = "img/excomunicationTile/excomm_"+excommunicationTile.getId()+".png";
            ExcommunicationTileRepresentation excomTileR = new ExcommunicationTileRepresentation(period, path);
            excommunicationTiles.add(excomTileR);
        }
    }

    public void createCardPlayerRepresentation(Match match){
        cardsPlayer1.put(CardType.TERRITORY, territoryCardsPlayer1);
        cardsPlayer1.put(CardType.BUILDING, buildingCardsPlayer1);
        cardsPlayer1.put(CardType.CHARACTER, characterCardsPlayer1);
        cardsPlayer1.put(CardType.VENTURE, ventureCardsPlayer1);

        cardsPlayer2.put(CardType.TERRITORY, territoryCardsPlayer2);
        cardsPlayer2.put(CardType.BUILDING, buildingCardsPlayer2);
        cardsPlayer2.put(CardType.CHARACTER, characterCardsPlayer2);
        cardsPlayer2.put(CardType.VENTURE, ventureCardsPlayer2);

        cardsPlayer3.put(CardType.TERRITORY, territoryCardsPlayer3);
        cardsPlayer3.put(CardType.BUILDING, buildingCardsPlayer3);
        cardsPlayer3.put(CardType.CHARACTER, characterCardsPlayer3);
        cardsPlayer3.put(CardType.VENTURE, ventureCardsPlayer3);

        cardsPlayer4.put(CardType.TERRITORY, territoryCardsPlayer4);
        cardsPlayer4.put(CardType.BUILDING, buildingCardsPlayer4);
        cardsPlayer4.put(CardType.CHARACTER, characterCardsPlayer4);
        cardsPlayer4.put(CardType.VENTURE, ventureCardsPlayer4);

        cardsPlayers.put(PlayerColor.BLUE, cardsPlayer1);
        cardsPlayers.put(PlayerColor.GREEN, cardsPlayer2);
        cardsPlayers.put(PlayerColor.RED, cardsPlayer3);
        cardsPlayers.put(PlayerColor.YELLOW, cardsPlayer4);
        for(Player player : players.values()) {

            for (CardType cardType : CardType.values()) {
                for (int i = 0; i < 6; i++) {
                    CardPlayerRepresentation cardPlayerRepresentation = new CardPlayerRepresentation("img/Card/transparentCard.png");
                    cardsPlayers.get(player.getColor()).get(cardType).add(cardPlayerRepresentation);
                }
            }
        }
    }

    public void createCardLeaderRepresentation(Match match){
        cardsLeaderPlayers.put(PlayerColor.BLUE, cardsLeaderPl1);
        cardsLeaderPlayers.put(PlayerColor.GREEN, cardsLeaderPl2);
        cardsLeaderPlayers.put(PlayerColor.RED, cardsLeaderPl3);
        cardsLeaderPlayers.put(PlayerColor.YELLOW, cardsLeaderPl4);

        for(Player player : match.getPlayers().values()){
            for(LeaderCard leaderCard : player.getPersonalBoard().getLeaderCardsSpace().getCards()){
                String url = "img/CardLeader/leader_"+leaderCard.getId()+".jpg";
                CardLeaderRepresentation cardLeaderRepresentation = new CardLeaderRepresentation(url, "img/CardLeader/baseLeader.jpg", leaderCard.getId());
                cardsLeaderPlayers.get(player.getColor()).add(cardLeaderRepresentation);
            }
        }

    }
    public void createPawnCouncil(){
        for(FamilyMemberColor familyMemberColor : FamilyMemberColor.values()){
            CouncilPawnFamily councilPawnFamily = new CouncilPawnFamily(null, null, "img/players/transparentPlayer.png");
            councilPawns.add(councilPawnFamily);
        }
    }
    public void createWorkSpacesPawn(){
		ConfigPlayers config = new LoaderConfigPlayers().get(null).get(players.size());
        workSpacesPawn.put(WorkType.PRODUCTION, productionPawn);
        workSpacesPawn.put(WorkType.HARVEST, harvestPawn);

        for (WorkType workType : WorkType.values()){
            String path = "img/workspace/"+workType+"_SMALL.png";
            WorkSpacePawn workSpacePawnSmall = new WorkSpacePawn(workType,null, null, path);
            workSpacesPawn.get(workType).add(workSpacePawnSmall);
        }
        if(config.isSpaceWorkMultiple()) {

			for (WorkType workType : WorkType.values()){
				for(int i = 0; i < 4; i++) {
					WorkSpacePawn workSpacePawn = new WorkSpacePawn(workType,null,null,"img/players/transparentPlayer.png");
					workSpacesPawn.get(workType).add(workSpacePawn);
				}
			}
		}
    }
    public void createMarketRepresentation(){
        ConfigPlayers config = new LoaderConfigPlayers().get(null).get(players.size());

        for(int i = 0; i < config.getSpaceMarketNum(); i++){
            MarketRepresentation marketRepresentation = new MarketRepresentation("img/markets/market"+i+".png");
            markets.add(marketRepresentation);
        }
    }
    public Map<PlayerColor, ObservableList<FamilyMemberRepresentation>> getMapPlayerColorObservableLiseFMRepr() {
		return familyMembers;
	}

	public Map<CardType, ObservableList<CardFloorRepresentation>> getCardsFloors() {
		return cardsFloors;
	}

    public Map<PlayerColor, ObservableMap<ResourceType, ResourceRepresentation>> getResourcesPlayers() {
        return resourcesPlayers;
    }

    public ObservableList<TurnOrderTrackPositionRepresentation> getTurnOrderTracks() {
        return turnOrderTracks;
    }

    public ObservableList<ResourceRepresentation> getMilitaryResources() {
        return militaryResources;
    }

    public ObservableList<ResourceRepresentation> getVictoryResources() {
        return victoryResources;
    }

    public ObservableList<ExcommunicationTileRepresentation> getExcommunicationTiles() {
        return excommunicationTiles;
    }

    public Map<PlayerColor, Map<CardType, ObservableList<CardPlayerRepresentation>>> getCardsPlayers() {
        return cardsPlayers;
    }

    public Map<PlayerColor, ObservableList<CardLeaderRepresentation>> getCardsLeaderPlayers() {
        return cardsLeaderPlayers;
    }

    public ObservableList<ResourceRepresentation> getFaithResources() {
        return faithResources;
    }

    public ObservableList<CouncilPawnFamily> getCouncilPawns() {
        return councilPawns;
    }

    public Map<WorkType, ObservableList<WorkSpacePawn>> getWorkSpacesPawn() {
        return workSpacesPawn;
    }

    public ObservableList<MarketRepresentation> getMarkets() {
        return markets;
    }

    public void notifyInit() {
    	setChanged();
    	notifyObservers();
	}
}
