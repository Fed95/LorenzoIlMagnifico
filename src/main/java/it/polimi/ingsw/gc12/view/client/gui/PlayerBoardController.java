package it.polimi.ingsw.gc12.view.client.gui;

import it.polimi.ingsw.gc12.model.action.*;
import it.polimi.ingsw.gc12.model.card.CardLeaderGuiState;
import it.polimi.ingsw.gc12.model.card.CardType;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import it.polimi.ingsw.gc12.model.player.resource.ResourceType;
import it.polimi.ingsw.gc12.view.client.ClientFactory;
import it.polimi.ingsw.gc12.view.client.gui.GUIController;
import it.polimi.ingsw.gc12.view.client.gui.MatchInstanceGUI;
import it.polimi.ingsw.gc12.view.client.gui.representation.CardLeaderRepresentation;
import it.polimi.ingsw.gc12.view.client.gui.representation.CardPlayerRepresentation;
import it.polimi.ingsw.gc12.view.client.gui.representation.FamilyMemberRepresentation;
import it.polimi.ingsw.gc12.view.client.gui.representation.ResourceRepresentation;
import javafx.beans.binding.StringBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.*;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class PlayerBoardController extends GUIController implements Initializable, Observer {
	//players family member value
	@FXML
	private Label blackValuePl1;
	@FXML private Label orangeValuePl1;
	@FXML private Label whiteValuePl1;
	@FXML private Label neutralValuePl1;
	@FXML private Label blackValuePl2;
	@FXML private Label orangeValuePl2;
	@FXML private Label whiteValuePl2;
	@FXML private Label neutralValuePl2;
	@FXML private Label blackValuePl3;
	@FXML private Label orangeValuePl3;
	@FXML private Label whiteValuePl3;
	@FXML private Label neutralValuePl3;
	@FXML private Label blackValuePl4;
	@FXML private Label orangeValuePl4;
	@FXML private Label whiteValuePl4;
	@FXML private Label neutralValuePl4;
	//familyMember
	//player family member image
	@FXML private ImageView blueBlack;
	@FXML private ImageView blueWhite;
	@FXML private ImageView blueOrange;
	@FXML private ImageView blueNeutral;
	@FXML private ImageView greenBlack;
	@FXML private ImageView greenWhite;
	@FXML private ImageView greenOrange;
	@FXML private ImageView greenNeutral;
	@FXML private ImageView redBlack;
	@FXML private ImageView redWhite;
	@FXML private ImageView redOrange;
	@FXML private ImageView redNeutral;
	@FXML private ImageView yellowBlack;
	@FXML private ImageView yellowWhite;
	@FXML private ImageView yellowOrange;
	@FXML private ImageView yellowNeutral;
	//pane tb that contains tab of the players
	@FXML private TabPane playersBoard;

	//tab players
	@FXML private Tab bluePlayer;
	@FXML private Tab greenPlayer;
	@FXML private Tab redPlayer;
	@FXML private Tab yellowPlayer;
	private Map<PlayerColor, Tab> playerTabs = new HashMap<>();

	//resource for player
	@FXML private Label moneyValuePl1;
	@FXML private Label stoneValuePl1;
	@FXML private Label servantValuePl1;
	@FXML private Label woodValuePl1;

	@FXML private Label moneyValuePl2;
	@FXML private Label stoneValuePl2;
	@FXML private Label servantValuePl2;
	@FXML private Label woodValuePl2;

	@FXML private Label moneyValuePl3;
	@FXML private Label stoneValuePl3;
	@FXML private Label servantValuePl3;
	@FXML private Label woodValuePl3;

	@FXML private Label moneyValuePl4;
	@FXML private Label stoneValuePl4;
	@FXML private Label servantValuePl4;
	@FXML private Label woodValuePl4;
	private Map<PlayerColor, Map<ResourceType, Label>> resourceLabels = new HashMap<>();

	@FXML private ImageView territory0CardPlayer1;
	@FXML private ImageView territory1CardPlayer1;
	@FXML private ImageView territory2CardPlayer1;
	@FXML private ImageView territory3CardPlayer1;
	@FXML private ImageView territory4CardPlayer1;
	@FXML private ImageView territory5CardPlayer1;

	@FXML private ImageView building0CardPlayer1;
	@FXML private ImageView building1CardPlayer1;
	@FXML private ImageView building2CardPlayer1;
	@FXML private ImageView building3CardPlayer1;
	@FXML private ImageView building4CardPlayer1;
	@FXML private ImageView building5CardPlayer1;

	@FXML private ImageView character0CardPlayer1;
	@FXML private ImageView character1CardPlayer1;
	@FXML private ImageView character2CardPlayer1;
	@FXML private ImageView character3CardPlayer1;
	@FXML private ImageView character4CardPlayer1;
	@FXML private ImageView character5CardPlayer1;

	@FXML private ImageView venture0CardPlayer1;
	@FXML private ImageView venture1CardPlayer1;
	@FXML private ImageView venture2CardPlayer1;
	@FXML private ImageView venture3CardPlayer1;
	@FXML private ImageView venture4CardPlayer1;
	@FXML private ImageView venture5CardPlayer1;


	@FXML private ImageView territory0CardPlayer2;
	@FXML private ImageView territory1CardPlayer2;
	@FXML private ImageView territory2CardPlayer2;
	@FXML private ImageView territory3CardPlayer2;
	@FXML private ImageView territory4CardPlayer2;
	@FXML private ImageView territory5CardPlayer2;

	@FXML private ImageView building0CardPlayer2;
	@FXML private ImageView building1CardPlayer2;
	@FXML private ImageView building2CardPlayer2;
	@FXML private ImageView building3CardPlayer2;
	@FXML private ImageView building4CardPlayer2;
	@FXML private ImageView building5CardPlayer2;

	@FXML private ImageView character0CardPlayer2;
	@FXML private ImageView character1CardPlayer2;
	@FXML private ImageView character2CardPlayer2;
	@FXML private ImageView character3CardPlayer2;
	@FXML private ImageView character4CardPlayer2;
	@FXML private ImageView character5CardPlayer2;

	@FXML private ImageView venture0CardPlayer2;
	@FXML private ImageView venture1CardPlayer2;
	@FXML private ImageView venture2CardPlayer2;
	@FXML private ImageView venture3CardPlayer2;
	@FXML private ImageView venture4CardPlayer2;
	@FXML private ImageView venture5CardPlayer2;


	@FXML private ImageView territory0CardPlayer3;
	@FXML private ImageView territory1CardPlayer3;
	@FXML private ImageView territory2CardPlayer3;
	@FXML private ImageView territory3CardPlayer3;
	@FXML private ImageView territory4CardPlayer3;
	@FXML private ImageView territory5CardPlayer3;

	@FXML private ImageView building0CardPlayer3;
	@FXML private ImageView building1CardPlayer3;
	@FXML private ImageView building2CardPlayer3;
	@FXML private ImageView building3CardPlayer3;
	@FXML private ImageView building4CardPlayer3;
	@FXML private ImageView building5CardPlayer3;

	@FXML private ImageView character0CardPlayer3;
	@FXML private ImageView character1CardPlayer3;
	@FXML private ImageView character2CardPlayer3;
	@FXML private ImageView character3CardPlayer3;
	@FXML private ImageView character4CardPlayer3;
	@FXML private ImageView character5CardPlayer3;

	@FXML private ImageView venture0CardPlayer3;
	@FXML private ImageView venture1CardPlayer3;
	@FXML private ImageView venture2CardPlayer3;
	@FXML private ImageView venture3CardPlayer3;
	@FXML private ImageView venture4CardPlayer3;
	@FXML private ImageView venture5CardPlayer3;


	@FXML private ImageView territory0CardPlayer4;
	@FXML private ImageView territory1CardPlayer4;
	@FXML private ImageView territory2CardPlayer4;
	@FXML private ImageView territory3CardPlayer4;
	@FXML private ImageView territory4CardPlayer4;
	@FXML private ImageView territory5CardPlayer4;

	@FXML private ImageView building0CardPlayer4;
	@FXML private ImageView building1CardPlayer4;
	@FXML private ImageView building2CardPlayer4;
	@FXML private ImageView building3CardPlayer4;
	@FXML private ImageView building4CardPlayer4;
	@FXML private ImageView building5CardPlayer4;

	@FXML private ImageView character0CardPlayer4;
	@FXML private ImageView character1CardPlayer4;
	@FXML private ImageView character2CardPlayer4;
	@FXML private ImageView character3CardPlayer4;
	@FXML private ImageView character4CardPlayer4;
	@FXML private ImageView character5CardPlayer4;

	@FXML private ImageView venture0CardPlayer4;
	@FXML private ImageView venture1CardPlayer4;
	@FXML private ImageView venture2CardPlayer4;
	@FXML private ImageView venture3CardPlayer4;
	@FXML private ImageView venture4CardPlayer4;
	@FXML private ImageView venture5CardPlayer4;

	private Map<PlayerColor, Map<CardType, List<ImageView>>> playerCards = new HashMap<>();


	@FXML private ImageView cardLeader0Pl1;
	@FXML private ImageView cardLeader1Pl1;
	@FXML private ImageView cardLeader2Pl1;
	@FXML private ImageView cardLeader3Pl1;
	@FXML private ImageView cardLeaderPlayed0Pl1;
	@FXML private ImageView cardLeaderPlayed1Pl1;
	@FXML private ImageView cardLeaderPlayed2Pl1;
	@FXML private ImageView cardLeaderPlayed3Pl1;
	private Map<CardLeaderGuiState, List<ImageView>>  mapCardPlayerPl1 = new HashMap<>();

	@FXML private ImageView cardLeader0Pl2;
	@FXML private ImageView cardLeader1Pl2;
	@FXML private ImageView cardLeader2Pl2;
	@FXML private ImageView cardLeader3Pl2;
	@FXML private ImageView cardLeaderPlayed0Pl2;
	@FXML private ImageView cardLeaderPlayed1Pl2;
	@FXML private ImageView cardLeaderPlayed2Pl2;
	@FXML private ImageView cardLeaderPlayed3Pl2;
	private Map<CardLeaderGuiState, List<ImageView>>  mapCardPlayerPl2 = new HashMap<>();

	@FXML private ImageView cardLeader0Pl3;
	@FXML private ImageView cardLeader1Pl3;
	@FXML private ImageView cardLeader2Pl3;
	@FXML private ImageView cardLeader3Pl3;
	@FXML private ImageView cardLeaderPlayed0Pl3;
	@FXML private ImageView cardLeaderPlayed1Pl3;
	@FXML private ImageView cardLeaderPlayed2Pl3;
	@FXML private ImageView cardLeaderPlayed3Pl3;
	private Map<CardLeaderGuiState, List<ImageView>>  mapCardPlayerPl3 = new HashMap<>();

	@FXML private ImageView cardLeader0Pl4;
	@FXML private ImageView cardLeader1Pl4;
	@FXML private ImageView cardLeader2Pl4;
	@FXML private ImageView cardLeader3Pl4;
	@FXML private ImageView cardLeaderPlayed0Pl4;
	@FXML private ImageView cardLeaderPlayed1Pl4;
	@FXML private ImageView cardLeaderPlayed2Pl4;
	@FXML private ImageView cardLeaderPlayed3Pl4;
	private Map<CardLeaderGuiState, List<ImageView>>  mapCardPlayerPl4 = new HashMap<>();

	private Map<PlayerColor, Map<CardLeaderGuiState, List<ImageView>>> cardLeaders = new HashMap<>();

	private Map<PlayerColor, Map<FamilyMemberColor, ImageView>> familyMembers = new HashMap<>();
	private Map<PlayerColor, Map<FamilyMemberColor, Label>> familyMemberLabels = new HashMap<>();

	public PlayerBoardController(){
		super(ClientFactory.getClientHandler());
		match.addObserver(this);
	}

    /**
     * Some initializations, disable all the tabs
     * @param location
     * @param resources
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//disabling all tab player
		bluePlayer.setDisable(true);
		greenPlayer.setDisable(true);
		redPlayer.setDisable(true);
		yellowPlayer.setDisable(true);
	}

	public void showCardLeader(MouseEvent mouseEvent) {
		checkExclusion();
		ImageView leaderCard = (ImageView) mouseEvent.getSource();
		showCard(mouseEvent);
		if(mouseEvent.getClickCount()==2){
			int choice = askDiscardOrPlay();
			List<ImageView> cardsNotPlayed = cardLeaders.get(playerColor).get(CardLeaderGuiState.NOTPLAYED);
			for (int i = 0; i < cardsNotPlayed.size(); i++) {
				if(leaderCard.equals(cardsNotPlayed.get(i))) {
					Action action;
					if(choice == 0) {
						action = new ActionViewPlayableLeaderCards(match.getPlayers().get(playerColor));
						clientHandler.setActionPending(new ActionPlayLeaderCard(player, player.getNotPlayedLeaderCards().get(i)));
					}
					else {
						action = new ActionViewDiscardableLeaderCards(match.getPlayers().get(playerColor));
						clientHandler.setActionPending(new ActionDiscardLeaderCard(player, player.getNotPlayedLeaderCards().get(i)));
					}
					selectAction(action);
				}
			}
		}
	}

	public void familyClicked(MouseEvent mouseEvent) {
		checkExclusion();
		ImageView familyMemberClicked = (ImageView) mouseEvent.getTarget();
		if(isMyFam(playerColor, familyMemberClicked)) {
			if (isMyTurn()) {
				for (Map.Entry<FamilyMemberColor, ImageView> entry : familyMembers.get(playerColor).entrySet()) {
					if (entry.getValue().equals(familyMemberClicked)) {
						FamilyMember familyMember = new FamilyMember(playerColor, entry.getKey());
						Action action = new ActionChooseFamilyMember(match.getPlayers().get(playerColor), familyMember);
						if (isFMSelected(familyMemberClicked) || clientHandler.getActionPending() != null) {
							if (!isFMSelected(familyMemberClicked)) {
								clientHandler.setActionPending(action);
								clientHandler.setActionFM(action);
							}
							else
								clientHandler.setActionPending(null);
							action = new DiscardAction(match.getPlayers().get(playerColor));
						} else {
							clientHandler.setActionPending(action);
							clientHandler.setActionFM(action);
						}
						toggleFMHighlight(familyMemberClicked);
						selectAction(action);
						break;
					}
				}
			} else
				showTurnDenied();
		}
	}

	private void toggleFMHighlight(ImageView familyMemberClicked){
		Integer selection = (int)familyMemberClicked.getOpacity();
		if(selection != 1){
			familyMemberClicked.setOpacity(1);
		}
		else
			familyMemberClicked.setOpacity(0.5);
		for(ImageView familyMember: familyMembers.get(playerColor).values()) {
			if(!familyMember.equals(familyMemberClicked))
				familyMember.setOpacity(1);
		}
	}

	private int askDiscardOrPlay(){
		Dialog dialog = new Dialog<>();
		dialog.setTitle("Leader Card");
		dialog.setHeaderText("You have choose a Leader Card. \nDo you want to discard or play it?");
		ButtonType playButtonType = new ButtonType("Play", ButtonBar.ButtonData.YES);
		ButtonType discardButtonType = new ButtonType("Discard", ButtonBar.ButtonData.YES);

		dialog.getDialogPane().getButtonTypes().addAll(playButtonType, discardButtonType);

		Optional<ButtonType> result = dialog.showAndWait();
		if (result.get() == playButtonType){
			return 0;
		} else if(result.get() == discardButtonType) {
			return 1;
		}
		return askDiscardOrPlay();
	}

	private Boolean isMyFam(PlayerColor color, ImageView famMemb){
		Map<FamilyMemberColor,ImageView> allMyFamily = familyMembers.get(color);
		for(ImageView imageViewFam: allMyFamily.values()){
			if(imageViewFam.equals(famMemb)){
				return true;
			}
		}
		return false;
	}


	private boolean isFMSelected(ImageView familyMember) {
		return familyMember.getOpacity() != 1;

	}

	public void bindFamilyMembers(){
		Map<PlayerColor, ObservableList<FamilyMemberRepresentation>> mapColorFamilyRepresentation = match.getMapPlayerColorObservableLiseFMRepr();
		for(Player player : match.getPlayers().values()){
			PlayerColor playerColor = player.getColor();
			for(FamilyMemberColor familyMemberColor: FamilyMemberColor.values()){
				//prendo la rappresentazione giusta tramite il famili member e ne prendo la property valore, lo setto poi alla label
				FamilyMemberRepresentation FMRepresentation = mapColorFamilyRepresentation.get(playerColor).stream()
						.filter(FM -> (FM.getColor()).equals(familyMemberColor)).collect(collectingAndThen(toList(), FXCollections::observableArrayList)).get(0);
				StringBinding value = FMRepresentation.getValueProperty().asString();//valore da assegnare alla label
				familyMemberLabels.get(playerColor).get(familyMemberColor).textProperty().bind((value));//bindo la property alla label
				familyMemberLabels.get(playerColor).get(familyMemberColor).visibleProperty().bind(FMRepresentation.getVisibility());
				familyMembers.get(playerColor).get(familyMemberColor).imageProperty().bind(FMRepresentation.getpathFamilyMemberImageProperty());
				familyMembers.get(playerColor).get(familyMemberColor).visibleProperty().bind(FMRepresentation.getVisibility());
			}
		}
	}

	public void disableTab(){
		for(Player player : match.getPlayers().values()) {
			Tab tabToWorkWith = playerTabs.get(player.getColor());
			tabToWorkWith.setDisable(false);

			String name= player.getName();
			if(name.length()>25){
				name = player.getName().substring(0,24)+"...";
			}
			tabToWorkWith.setText(name);
		}
	}

	public void bindResources(){
		for(Player player : match.getPlayers().values()) {
			ObservableMap<ResourceType, ResourceRepresentation> resourcesRepresentation = match.getResourcesPlayers().get(player.getColor());
			resourceLabels.get(player.getColor()).get(ResourceType.MONEY).textProperty().bind(resourcesRepresentation.get(ResourceType.MONEY).getValue().asString());
			resourceLabels.get(player.getColor()).get(ResourceType.WOOD).textProperty().bind(resourcesRepresentation.get(ResourceType.WOOD).getValue().asString());
			resourceLabels.get(player.getColor()).get(ResourceType.STONE).textProperty().bind(resourcesRepresentation.get(ResourceType.STONE).getValue().asString());
			resourceLabels.get(player.getColor()).get(ResourceType.SERVANT).textProperty().bind(resourcesRepresentation.get(ResourceType.SERVANT).getValue().asString());
		}
	}

	public void bindPlayerCard(){
		for(Player player : match.getPlayers().values()) {
			for(CardType cardType : CardType.values()) {
				for(int i = 0; i < 6; i++) {
					CardPlayerRepresentation cardPlayerRepresentation = match.getCardsPlayers().get(player.getColor()).get(cardType).get(i);
					playerCards.get(player.getColor()).get(cardType).get(i).imageProperty().bind(cardPlayerRepresentation.getPathProperty());
				}
			}
		}
	}

	public void bindPlayerLeaderCard(){
		Map<PlayerColor, ObservableList<CardLeaderRepresentation>> mapPlayerColorCardLeaders = match.getCardsLeaderPlayers();
		for(Player player : match.getPlayers().values()){
			List<ImageView> listPlayed = cardLeaders.get(player.getColor()).get(CardLeaderGuiState.PLAYED);
			for (int i = 0; i < listPlayed.size(); i++){
				listPlayed.get(i).imageProperty().bind(mapPlayerColorCardLeaders.get(player.getColor()).get(i).getPathWhenPlayedProperty());
				if(playerColor.equals(player.getColor())) {
                    listPlayed.get(i).visibleProperty().bind(mapPlayerColorCardLeaders.get(player.getColor()).get(i).getVisibility());
                }
			}
			if(playersBoard.getUserData().equals(player.getColor())) {
				List<ImageView> listNotPlayed = cardLeaders.get(player.getColor()).get(CardLeaderGuiState.NOTPLAYED);
				for (int i = 0; i < listNotPlayed.size(); i++) {
					listNotPlayed.get(i).imageProperty().bind(mapPlayerColorCardLeaders.get(player.getColor()).get(i).getPathProperty());
				}
			}
		}
	}

	public void setPlayerToPane(){
		playersBoard.getSelectionModel().select(playerTabs.get(playerColor));
		playersBoard.setUserData(playerColor);
	}

	public void resetFamilyMembers() {
		for(PlayerColor color: PlayerColor.values()) {
			for(ImageView familyMember: familyMembers.get(color).values()) {
				familyMember.setOpacity(1);
			}
		}
	}

	public void setFamilyMembers() {
		familyMembers.put(PlayerColor.BLUE, new HashMap<FamilyMemberColor, ImageView>() {{
			put(FamilyMemberColor.BLACK, blueBlack);
			put(FamilyMemberColor.WHITE, blueWhite);
			put(FamilyMemberColor.ORANGE, blueOrange);
			put(FamilyMemberColor.NEUTRAL, blueNeutral);
		}});

		familyMembers.put(PlayerColor.GREEN, new HashMap<FamilyMemberColor, ImageView>() {{
			put(FamilyMemberColor.BLACK, greenBlack);
			put(FamilyMemberColor.WHITE, greenWhite);
			put(FamilyMemberColor.ORANGE, greenOrange);
			put(FamilyMemberColor.NEUTRAL, greenNeutral);
		}});

		familyMembers.put(PlayerColor.RED, new HashMap<FamilyMemberColor, ImageView>() {{
			put(FamilyMemberColor.BLACK, redBlack);
			put(FamilyMemberColor.WHITE, redWhite);
			put(FamilyMemberColor.ORANGE, redOrange);
			put(FamilyMemberColor.NEUTRAL, redNeutral);
		}});

		familyMembers.put(PlayerColor.YELLOW, new HashMap<FamilyMemberColor, ImageView>() {{
			put(FamilyMemberColor.BLACK, yellowBlack);
			put(FamilyMemberColor.WHITE, yellowWhite);
			put(FamilyMemberColor.ORANGE, yellowOrange);
			put(FamilyMemberColor.NEUTRAL, yellowNeutral);
		}});
	}

	public void setFamilyMemberLabels() {
		//creating map player list of label player
		familyMemberLabels.put(PlayerColor.BLUE, new HashMap<FamilyMemberColor, Label>() {{
			put(FamilyMemberColor.BLACK, blackValuePl1);
			put(FamilyMemberColor.WHITE, whiteValuePl1);
			put(FamilyMemberColor.ORANGE, orangeValuePl1);
			put(FamilyMemberColor.NEUTRAL, neutralValuePl1);
		}});

		familyMemberLabels.put(PlayerColor.GREEN, new HashMap<FamilyMemberColor, Label>() {{
			put(FamilyMemberColor.BLACK, blackValuePl2);
			put(FamilyMemberColor.WHITE, whiteValuePl2);
			put(FamilyMemberColor.ORANGE, orangeValuePl2);
			put(FamilyMemberColor.NEUTRAL, neutralValuePl2);
		}});

		familyMemberLabels.put(PlayerColor.RED, new HashMap<FamilyMemberColor, Label>() {{
			put(FamilyMemberColor.BLACK, blackValuePl3);
			put(FamilyMemberColor.WHITE, whiteValuePl3);
			put(FamilyMemberColor.ORANGE, orangeValuePl3);
			put(FamilyMemberColor.NEUTRAL, neutralValuePl3);
		}});

		familyMemberLabels.put(PlayerColor.YELLOW, new HashMap<FamilyMemberColor, Label>() {{
			put(FamilyMemberColor.BLACK, blackValuePl4);
			put(FamilyMemberColor.WHITE, whiteValuePl4);
			put(FamilyMemberColor.ORANGE, orangeValuePl4);
			put(FamilyMemberColor.NEUTRAL, neutralValuePl4);
		}});
	}

	public void setPlayerTabs() {
		//associating tab with colo player
		playerTabs.put(PlayerColor.BLUE, bluePlayer);
		playerTabs.put(PlayerColor.GREEN, greenPlayer);
		playerTabs.put(PlayerColor.RED, redPlayer);
		playerTabs.put(PlayerColor.YELLOW, yellowPlayer);
	}

	public void setResourceLabels() {
		//creating the list and map resources
		Map<ResourceType, Label> resourcePlayer1 = new HashMap<>();
		resourcePlayer1.put(ResourceType.MONEY, moneyValuePl1);
		resourcePlayer1.put(ResourceType.STONE, stoneValuePl1);
		resourcePlayer1.put(ResourceType.WOOD, woodValuePl1);
		resourcePlayer1.put(ResourceType.SERVANT, servantValuePl1);

		Map<ResourceType, Label> resourcePlayer2 = new HashMap<>();
		resourcePlayer2.put(ResourceType.MONEY, moneyValuePl2);
		resourcePlayer2.put(ResourceType.STONE, stoneValuePl2);
		resourcePlayer2.put(ResourceType.WOOD, woodValuePl2);
		resourcePlayer2.put(ResourceType.SERVANT, servantValuePl2);

		Map<ResourceType, Label> resourcePlayer3 = new HashMap<>();
		resourcePlayer3.put(ResourceType.MONEY, moneyValuePl3);
		resourcePlayer3.put(ResourceType.STONE, stoneValuePl3);
		resourcePlayer3.put(ResourceType.WOOD, woodValuePl3);
		resourcePlayer3.put(ResourceType.SERVANT, servantValuePl3);

		Map<ResourceType, Label> resourcePlayer4 = new HashMap<>();
		resourcePlayer4.put(ResourceType.MONEY, moneyValuePl4);
		resourcePlayer4.put(ResourceType.STONE, stoneValuePl4);
		resourcePlayer4.put(ResourceType.WOOD, woodValuePl4);
		resourcePlayer4.put(ResourceType.SERVANT, servantValuePl4);

		resourceLabels.put(PlayerColor.BLUE, resourcePlayer1);
		resourceLabels.put(PlayerColor.GREEN, resourcePlayer2);
		resourceLabels.put(PlayerColor.RED, resourcePlayer3);
		resourceLabels.put(PlayerColor.YELLOW, resourcePlayer4);
	}

	public void setPlayerCards() {
		playerCards.put(PlayerColor.BLUE, setFirstPlayerCards());
		playerCards.put(PlayerColor.GREEN, setSecondPlayerCards());
		playerCards.put(PlayerColor.RED, setThirdPlayerCards());
		playerCards.put(PlayerColor.YELLOW, setFourthPlayerCards());
	}

	public Map<CardType, List<ImageView>> setFirstPlayerCards() {
		Map<CardType, List<ImageView>> firstPlayerCards = new HashMap<>();
		//all card of player 1
		List<ImageView> territories = new ArrayList<>();
		territories.add(territory0CardPlayer1);
		territories.add(territory1CardPlayer1);
		territories.add(territory2CardPlayer1);
		territories.add(territory3CardPlayer1);
		territories.add(territory4CardPlayer1);
		territories.add(territory5CardPlayer1);

		List<ImageView> ventures = new ArrayList<>();
		ventures.add(venture0CardPlayer1);
		ventures.add(venture1CardPlayer1);
		ventures.add(venture2CardPlayer1);
		ventures.add(venture3CardPlayer1);
		ventures.add(venture4CardPlayer1);
		ventures.add(venture5CardPlayer1);

		List<ImageView> buildings = new ArrayList<>();
		buildings.add(building0CardPlayer1);
		buildings.add(building1CardPlayer1);
		buildings.add(building2CardPlayer1);
		buildings.add(building3CardPlayer1);
		buildings.add(building4CardPlayer1);
		buildings.add(building5CardPlayer1);

		List<ImageView> characters = new ArrayList<>();
		characters.add(character0CardPlayer1);
		characters.add(character1CardPlayer1);
		characters.add(character2CardPlayer1);
		characters.add(character3CardPlayer1);
		characters.add(character4CardPlayer1);
		characters.add(character5CardPlayer1);

		firstPlayerCards.put(CardType.TERRITORY, territories);
		firstPlayerCards.put(CardType.BUILDING, buildings);
		firstPlayerCards.put(CardType.VENTURE, ventures);
		firstPlayerCards.put(CardType.CHARACTER, characters);

		return firstPlayerCards;
	}

	public Map<CardType, List<ImageView>> setSecondPlayerCards() {
		Map<CardType, List<ImageView>> secondPlayerCards = new HashMap<>();
		//all card of player 1
		List<ImageView> territories = new ArrayList<>();
		territories.add(territory0CardPlayer2);
		territories.add(territory1CardPlayer2);
		territories.add(territory2CardPlayer2);
		territories.add(territory3CardPlayer2);
		territories.add(territory4CardPlayer2);
		territories.add(territory5CardPlayer2);

		List<ImageView> ventures = new ArrayList<>();
		ventures.add(venture0CardPlayer2);
		ventures.add(venture1CardPlayer2);
		ventures.add(venture2CardPlayer2);
		ventures.add(venture3CardPlayer2);
		ventures.add(venture4CardPlayer2);
		ventures.add(venture5CardPlayer2);

		List<ImageView> buildings = new ArrayList<>();
		buildings.add(building0CardPlayer2);
		buildings.add(building1CardPlayer2);
		buildings.add(building2CardPlayer2);
		buildings.add(building3CardPlayer2);
		buildings.add(building4CardPlayer2);
		buildings.add(building5CardPlayer2);

		List<ImageView> characters = new ArrayList<>();
		characters.add(character0CardPlayer2);
		characters.add(character1CardPlayer2);
		characters.add(character2CardPlayer2);
		characters.add(character3CardPlayer2);
		characters.add(character4CardPlayer2);
		characters.add(character5CardPlayer2);

		secondPlayerCards.put(CardType.TERRITORY, territories);
		secondPlayerCards.put(CardType.BUILDING, buildings);
		secondPlayerCards.put(CardType.VENTURE, ventures);
		secondPlayerCards.put(CardType.CHARACTER, characters);

		return secondPlayerCards;
	}

	public Map<CardType, List<ImageView>> setThirdPlayerCards() {
		Map<CardType, List<ImageView>> thirdPlayerCards = new HashMap<>();
		//all card of player 1
		List<ImageView> territories = new ArrayList<>();
		territories.add(territory0CardPlayer3);
		territories.add(territory1CardPlayer3);
		territories.add(territory2CardPlayer3);
		territories.add(territory3CardPlayer3);
		territories.add(territory4CardPlayer3);
		territories.add(territory5CardPlayer3);

		List<ImageView> ventures = new ArrayList<>();
		ventures.add(venture0CardPlayer3);
		ventures.add(venture1CardPlayer3);
		ventures.add(venture2CardPlayer3);
		ventures.add(venture3CardPlayer3);
		ventures.add(venture4CardPlayer3);
		ventures.add(venture5CardPlayer3);

		List<ImageView> buildings = new ArrayList<>();
		buildings.add(building0CardPlayer3);
		buildings.add(building1CardPlayer3);
		buildings.add(building2CardPlayer3);
		buildings.add(building3CardPlayer3);
		buildings.add(building4CardPlayer3);
		buildings.add(building5CardPlayer3);

		List<ImageView> characters = new ArrayList<>();
		characters.add(character0CardPlayer3);
		characters.add(character1CardPlayer3);
		characters.add(character2CardPlayer3);
		characters.add(character3CardPlayer3);
		characters.add(character4CardPlayer3);
		characters.add(character5CardPlayer3);

		thirdPlayerCards.put(CardType.TERRITORY, territories);
		thirdPlayerCards.put(CardType.BUILDING, buildings);
		thirdPlayerCards.put(CardType.VENTURE, ventures);
		thirdPlayerCards.put(CardType.CHARACTER, characters);

		return thirdPlayerCards;
	}

	public Map<CardType, List<ImageView>> setFourthPlayerCards() {
		Map<CardType, List<ImageView>> fourthPlayerCards = new HashMap<>();
		//all card of player 1
		List<ImageView> territories = new ArrayList<>();
		territories.add(territory0CardPlayer4);
		territories.add(territory1CardPlayer4);
		territories.add(territory2CardPlayer4);
		territories.add(territory3CardPlayer4);
		territories.add(territory4CardPlayer4);
		territories.add(territory5CardPlayer4);

		List<ImageView> ventures = new ArrayList<>();
		ventures.add(venture0CardPlayer4);
		ventures.add(venture1CardPlayer4);
		ventures.add(venture2CardPlayer4);
		ventures.add(venture3CardPlayer4);
		ventures.add(venture4CardPlayer4);
		ventures.add(venture5CardPlayer4);

		List<ImageView> buildings = new ArrayList<>();
		buildings.add(building0CardPlayer4);
		buildings.add(building1CardPlayer4);
		buildings.add(building2CardPlayer4);
		buildings.add(building3CardPlayer4);
		buildings.add(building4CardPlayer4);
		buildings.add(building5CardPlayer4);

		List<ImageView> characters = new ArrayList<>();
		characters.add(character0CardPlayer4);
		characters.add(character1CardPlayer4);
		characters.add(character2CardPlayer4);
		characters.add(character3CardPlayer4);
		characters.add(character4CardPlayer4);
		characters.add(character5CardPlayer4);

		fourthPlayerCards.put(CardType.TERRITORY, territories);
		fourthPlayerCards.put(CardType.BUILDING, buildings);
		fourthPlayerCards.put(CardType.VENTURE, ventures);
		fourthPlayerCards.put(CardType.CHARACTER, characters);

		return fourthPlayerCards;
	}

	public void setCardLeaders() {
		Map<CardLeaderGuiState, List<ImageView>> firstPlayerCardLeaders = new HashMap<>();
		firstPlayerCardLeaders.put(CardLeaderGuiState.NOTPLAYED, new ArrayList<>(Arrays.asList(cardLeader0Pl1, cardLeader1Pl1, cardLeader2Pl1, cardLeader3Pl1)));
		firstPlayerCardLeaders.put(CardLeaderGuiState.PLAYED, new ArrayList<>(Arrays.asList(cardLeaderPlayed0Pl1, cardLeaderPlayed1Pl1, cardLeaderPlayed2Pl1, cardLeaderPlayed3Pl1)));

		Map<CardLeaderGuiState, List<ImageView>> secondPlayerCardLeaders = new HashMap<>();
		secondPlayerCardLeaders.put(CardLeaderGuiState.NOTPLAYED, new ArrayList<>(Arrays.asList(cardLeader0Pl2, cardLeader1Pl2, cardLeader2Pl2, cardLeader3Pl2)));
		secondPlayerCardLeaders.put(CardLeaderGuiState.PLAYED, new ArrayList<>(Arrays.asList(cardLeaderPlayed0Pl2, cardLeaderPlayed1Pl2, cardLeaderPlayed2Pl2, cardLeaderPlayed3Pl2)));

		Map<CardLeaderGuiState, List<ImageView>> thirdPlayerCardLeaders = new HashMap<>();
		thirdPlayerCardLeaders.put(CardLeaderGuiState.NOTPLAYED, new ArrayList<>(Arrays.asList(cardLeader0Pl3, cardLeader1Pl3, cardLeader2Pl3, cardLeader3Pl3)));
		thirdPlayerCardLeaders.put(CardLeaderGuiState.PLAYED, new ArrayList<>(Arrays.asList(cardLeaderPlayed0Pl3, cardLeaderPlayed1Pl3, cardLeaderPlayed2Pl3, cardLeaderPlayed3Pl3)));

		Map<CardLeaderGuiState, List<ImageView>> fourthPlayerCardLeaders = new HashMap<>();
		fourthPlayerCardLeaders.put(CardLeaderGuiState.NOTPLAYED, new ArrayList<>(Arrays.asList(cardLeader0Pl4, cardLeader1Pl4, cardLeader2Pl4, cardLeader3Pl4)));
		fourthPlayerCardLeaders.put(CardLeaderGuiState.PLAYED, new ArrayList<>(Arrays.asList(cardLeaderPlayed0Pl4, cardLeaderPlayed1Pl4, cardLeaderPlayed2Pl4, cardLeaderPlayed3Pl4)));

		cardLeaders.put(PlayerColor.BLUE, firstPlayerCardLeaders);
		cardLeaders.put(PlayerColor.GREEN, secondPlayerCardLeaders);
		cardLeaders.put(PlayerColor.RED, thirdPlayerCardLeaders);
		cardLeaders.put(PlayerColor.YELLOW, fourthPlayerCardLeaders);
	}

	@Override
	public void update(Observable o, Object arg) {
		player = match.getPlayers().get(playerColor);
	}
}
