package it.polimi.ingsw.gc_12.java_fx;


import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.ClientFactory;
import it.polimi.ingsw.gc_12.client.ClientSender;
import it.polimi.ingsw.gc_12.mvc.GUIAdapter;
import it.polimi.ingsw.gc_12.resource.ResourceType;
import javafx.application.Platform;
import javafx.beans.binding.StringBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.Observable;
import java.util.Observer;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;


public class MainBoardController implements Initializable, Observer {
    //Card on floors
    @FXML private ImageView showCards;
    @FXML private ImageView cardFloor0;
    @FXML private ImageView cardFloor1;
    @FXML private ImageView cardFloor2;
    @FXML private ImageView cardFloor3;
    @FXML private ImageView cardFloor4;
    @FXML private ImageView cardFloor5;
    @FXML private ImageView cardFloor6;
    @FXML private ImageView cardFloor7;
    @FXML private ImageView cardFloor8;
    @FXML private ImageView cardFloor9;
    @FXML private ImageView cardFloor10;
    @FXML private ImageView cardFloor11;
    @FXML private ImageView cardFloor12;
    @FXML private ImageView cardFloor13;
    @FXML private ImageView cardFloor14;
    @FXML private ImageView cardFloor15;
    private Map<Integer, ImageView> mapCardFloorTerritory = new HashMap<>();
    private Map<Integer, ImageView> mapCardFloorBuildings = new HashMap<>();
    private Map<Integer, ImageView> mapCardFloorCharacter = new HashMap<>();
    private Map<Integer, ImageView> mapCardFloorVenture = new HashMap<>();
    private Map<CardType, Map<Integer, ImageView>> mapCardTypeMapIntegerCardFloors = new HashMap<>();

    @FXML private Label blackValuePl1;
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
    //pane tb that contains tab of the players
    @FXML private TabPane playersBoards;

    //tab players
    @FXML private Tab bluePlayer;
    @FXML private Tab greenPlayer;
    @FXML private Tab redPlayer;
    @FXML private Tab yellowPlayer;
    private Map<PlayerColor, Tab> mapPlayerColorTab = new HashMap<>();

    //familyMember
    private Map<FamilyMemberColor, Label> bluePlayerLabel = new HashMap<>();
    private Map<FamilyMemberColor, Label> greenPlayerLabel = new HashMap<>();
    private Map<FamilyMemberColor, Label> redPlayerLabel = new HashMap<>();
    private Map<FamilyMemberColor, Label> yellowPlayerLabel = new HashMap<>();
    private Map<PlayerColor , Map<FamilyMemberColor,Label>> mapPlayerColorFamilyColorLabel = new HashMap<>();

    //resource for player
    @FXML private Label moneyValuePl1;
    @FXML private Label stoneValuePl1;
    @FXML private Label servantValuePl1;
    @FXML private Label woodValuePl1;
    private Map<ResourceType,Label> resourcePlayer1 = new HashMap<>();

    @FXML private Label moneyValuePl2;
    @FXML private Label stoneValuePl2;
    @FXML private Label servantValuePl2;
    @FXML private Label woodValuePl2;
    private Map<ResourceType,Label> resourcePlayer2 = new HashMap<>();

    @FXML private Label moneyValuePl3;
    @FXML private Label stoneValuePl3;
    @FXML private Label servantValuePl3;
    @FXML private Label woodValuePl3;
    private Map<ResourceType,Label> resourcePlayer3 = new HashMap<>();

    @FXML private Label moneyValuePl4;
    @FXML private Label stoneValuePl4;
    @FXML private Label servantValuePl4;
    @FXML private Label woodValuePl4;
    private Map<ResourceType,Label> resourcePlayer4 = new HashMap<>();
    private Map<PlayerColor, Map<ResourceType, Label>> mapPlayerColorResourceLabel= new HashMap<>();

    @FXML private Circle floorToBeClicked0;
    @FXML private Circle floorToBeClicked1;
    @FXML private Circle floorToBeClicked2;
    @FXML private Circle floorToBeClicked3;
    @FXML private Circle floorToBeClicked4;
    @FXML private Circle floorToBeClicked5;
    @FXML private Circle floorToBeClicked6;
    @FXML private Circle floorToBeClicked7;
    @FXML private Circle floorToBeClicked8;
    @FXML private Circle floorToBeClicked9;
    @FXML private Circle floorToBeClicked10;
    @FXML private Circle floorToBeClicked11;
    @FXML private Circle floorToBeClicked12;
    @FXML private Circle floorToBeClicked13;
    @FXML private Circle floorToBeClicked14;
    @FXML private Circle floorToBeClicked15;
    private Map<Integer, Circle> mapfloorToBeClickedTerritory = new HashMap<>();
    private Map<Integer, Circle> mapfloorToBeClickedBuildings = new HashMap<>();
    private Map<Integer, Circle> mapfloorToBeClickedCharacter = new HashMap<>();
    private Map<Integer, Circle> mapfloorToBeClickedVenture = new HashMap<>();
    private Map<CardType, Map<Integer, Circle>> mapCardTypeFloorCircle = new HashMap<>();

    @FXML private Circle firstPlayerTurn;
    @FXML private Circle secondPlayerTurn;
    @FXML private Circle thirdPlayerTurn;
    @FXML private Circle fourthPlayerTurn;
    List<Circle> turnOrderTrack = new ArrayList<>();

    @FXML private TableView<ResourceRepresentation> tableVictoryPoints;
    @FXML private TableColumn<ResourceRepresentation, String> nameVictoryPoints;
    @FXML private TableColumn<ResourceRepresentation, String> pointsVictoryPoints;

    @FXML private TableView<ResourceRepresentation> tableMilitaryPoints;
    @FXML private TableColumn<ResourceRepresentation, String> nameMilitaryPoints;
    @FXML private TableColumn<ResourceRepresentation, String> pointsMilitaryPoints;

    @FXML private TextArea chatTextArea;
    private ImageView lastFamClicked = null;


    private GUIAdapter adapter;
    private MatchInstanceGUI match;
    private ClientHandler clientHandler;

    @FXML void familyClicked(MouseEvent event) {
        ImageView familyMemberClicked = (ImageView) event.getTarget();
        List<Action> actions = clientHandler.getActions();
        for(Action action: actions)
            System.out.println(action);
        Image image = new Image("img/Card/card_92.png");
        match.getMapTypeCardFloorRepresentation().get(CardType.TERRITORY).get(0).setPath(image);


        if(familyMemberClicked.equals(lastFamClicked)){
            System.out.println("uguali");
            double selection = familyMemberClicked.getOpacity();
            if(selection != 1){
                familyMemberClicked.setOpacity(1);
            }else{
                familyMemberClicked.setOpacity(0.5);
            }
        }else{
            System.out.println("diversi");
            if(lastFamClicked!=null)
                lastFamClicked.setOpacity(1);
            familyMemberClicked.setOpacity(0.5);
        }
        System.out.println(event.getPickResult().getIntersectedNode().getId());
        lastFamClicked = familyMemberClicked;
       //match.getFamilyMemberBlueRepresentationObservableList().get(0).setValueProperty(10);
    }

    @FXML void showCard(MouseEvent event){
        ImageView imageView = (ImageView) event.getSource();
        Image card = imageView.getImage();
        showCards.setImage(card);
        showCards.setOpacity(1);
    }

    @FXML void floorClicked(MouseEvent event){
        System.out.println("click");
        Circle floorClicked = (Circle) event.getTarget();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        match = MatchInstanceGUI.instance();
        match.addObserver(this);

        //creating List of label for each player
        bluePlayerLabel.put(FamilyMemberColor.BLACK, blackValuePl1);
        bluePlayerLabel.put(FamilyMemberColor.WHITE, whiteValuePl1);
        bluePlayerLabel.put(FamilyMemberColor.ORANGE, orangeValuePl1);
        bluePlayerLabel.put(FamilyMemberColor.NEUTRAL, neutralValuePl1);

        greenPlayerLabel.put(FamilyMemberColor.BLACK, blackValuePl2);
        greenPlayerLabel.put(FamilyMemberColor.WHITE, whiteValuePl2);
        greenPlayerLabel.put(FamilyMemberColor.ORANGE, orangeValuePl2);
        greenPlayerLabel.put(FamilyMemberColor.NEUTRAL, neutralValuePl2);

        redPlayerLabel.put(FamilyMemberColor.BLACK, blackValuePl3);
        redPlayerLabel.put(FamilyMemberColor.WHITE, whiteValuePl3);
        redPlayerLabel.put(FamilyMemberColor.ORANGE, orangeValuePl3);
        redPlayerLabel.put(FamilyMemberColor.NEUTRAL, neutralValuePl3);

        yellowPlayerLabel.put(FamilyMemberColor.BLACK, blackValuePl4);
        yellowPlayerLabel.put(FamilyMemberColor.WHITE, whiteValuePl4);
        yellowPlayerLabel.put(FamilyMemberColor.ORANGE, orangeValuePl4);
        yellowPlayerLabel.put(FamilyMemberColor.NEUTRAL, neutralValuePl4);

        //creating map player list of label player
        mapPlayerColorFamilyColorLabel.put(PlayerColor.BLUE, bluePlayerLabel);
        mapPlayerColorFamilyColorLabel.put(PlayerColor.GREEN, greenPlayerLabel);
        mapPlayerColorFamilyColorLabel.put(PlayerColor.RED, redPlayerLabel);
        mapPlayerColorFamilyColorLabel.put(PlayerColor.YELLOW, yellowPlayerLabel);

        //associating tab with colo player
        mapPlayerColorTab.put(PlayerColor.BLUE, bluePlayer);
        mapPlayerColorTab.put(PlayerColor.GREEN, greenPlayer);
        mapPlayerColorTab.put(PlayerColor.RED, redPlayer);
        mapPlayerColorTab.put(PlayerColor.YELLOW, yellowPlayer);

        //associating floor number to id
        mapCardFloorTerritory.put(3, cardFloor0);
        mapCardFloorTerritory.put(2, cardFloor1);
        mapCardFloorTerritory.put(1, cardFloor2);
        mapCardFloorTerritory.put(0, cardFloor3);

        mapCardFloorCharacter.put(3, cardFloor4);
        mapCardFloorCharacter.put(2, cardFloor5);
        mapCardFloorCharacter.put(1, cardFloor6);
        mapCardFloorCharacter.put(0, cardFloor7);

        mapCardFloorBuildings.put(3, cardFloor8);
        mapCardFloorBuildings.put(2, cardFloor9);
        mapCardFloorBuildings.put(1, cardFloor10);
        mapCardFloorBuildings.put(0, cardFloor11);

        mapCardFloorVenture.put(3, cardFloor12);
        mapCardFloorVenture.put(2, cardFloor13);
        mapCardFloorVenture.put(1, cardFloor14);
        mapCardFloorVenture.put(0, cardFloor15);
        mapCardTypeMapIntegerCardFloors.put(CardType.TERRITORY, mapCardFloorTerritory);
        mapCardTypeMapIntegerCardFloors.put(CardType.BUILDING, mapCardFloorBuildings);
        mapCardTypeMapIntegerCardFloors.put(CardType.CHARACTER, mapCardFloorCharacter);
        mapCardTypeMapIntegerCardFloors.put(CardType.VENTURE, mapCardFloorVenture);
        //creating the list and map resources
        resourcePlayer1.put(ResourceType.MONEY, moneyValuePl1);
        resourcePlayer1.put(ResourceType.STONE, stoneValuePl1);
        resourcePlayer1.put(ResourceType.WOOD, woodValuePl1);
        resourcePlayer1.put(ResourceType.SERVANT, servantValuePl1);

        resourcePlayer2.put(ResourceType.MONEY, moneyValuePl2);
        resourcePlayer2.put(ResourceType.STONE, stoneValuePl2);
        resourcePlayer2.put(ResourceType.WOOD, woodValuePl2);
        resourcePlayer2.put(ResourceType.SERVANT, servantValuePl2);

        resourcePlayer3.put(ResourceType.MONEY, moneyValuePl3);
        resourcePlayer3.put(ResourceType.STONE, stoneValuePl3);
        resourcePlayer3.put(ResourceType.WOOD, woodValuePl3);
        resourcePlayer3.put(ResourceType.SERVANT, servantValuePl3);

        resourcePlayer4.put(ResourceType.MONEY, moneyValuePl4);
        resourcePlayer4.put(ResourceType.STONE, stoneValuePl4);
        resourcePlayer4.put(ResourceType.WOOD, woodValuePl4);
        resourcePlayer4.put(ResourceType.SERVANT, servantValuePl4);
        mapPlayerColorResourceLabel.put(PlayerColor.BLUE, resourcePlayer1);
        mapPlayerColorResourceLabel.put(PlayerColor.GREEN, resourcePlayer2);
        mapPlayerColorResourceLabel.put(PlayerColor.RED, resourcePlayer3);
        mapPlayerColorResourceLabel.put(PlayerColor.YELLOW, resourcePlayer4);

        //disabling all tab player
        bluePlayer.setDisable(true);
        greenPlayer.setDisable(true);
        redPlayer.setDisable(true);
        yellowPlayer.setDisable(true);

        //associating floor number to id
        mapfloorToBeClickedTerritory.put(3, floorToBeClicked0);
        mapfloorToBeClickedTerritory.put(2, floorToBeClicked1);
        mapfloorToBeClickedTerritory.put(1, floorToBeClicked2);
        mapfloorToBeClickedTerritory.put(0, floorToBeClicked3);

        mapfloorToBeClickedCharacter.put(3, floorToBeClicked4);
        mapfloorToBeClickedCharacter.put(2, floorToBeClicked5);
        mapfloorToBeClickedCharacter.put(1, floorToBeClicked6);
        mapfloorToBeClickedCharacter.put(0, floorToBeClicked7);

        mapfloorToBeClickedBuildings.put(3, floorToBeClicked8);
        mapfloorToBeClickedBuildings.put(2, floorToBeClicked9);
        mapfloorToBeClickedBuildings.put(1, floorToBeClicked10);
        mapfloorToBeClickedBuildings.put(0, floorToBeClicked11);

        mapfloorToBeClickedVenture.put(3, floorToBeClicked12);
        mapfloorToBeClickedVenture.put(2, floorToBeClicked13);
        mapfloorToBeClickedVenture.put(1, floorToBeClicked14);
        mapfloorToBeClickedVenture.put(0, floorToBeClicked15);

        mapCardTypeFloorCircle.put(CardType.TERRITORY, mapfloorToBeClickedTerritory);
        mapCardTypeFloorCircle.put(CardType.BUILDING, mapfloorToBeClickedBuildings);
        mapCardTypeFloorCircle.put(CardType.CHARACTER, mapfloorToBeClickedCharacter);
        mapCardTypeFloorCircle.put(CardType.VENTURE, mapfloorToBeClickedVenture);

        turnOrderTrack.add(firstPlayerTurn);
        turnOrderTrack.add(secondPlayerTurn);
        turnOrderTrack.add(thirdPlayerTurn);
        turnOrderTrack.add(fourthPlayerTurn);

        nameMilitaryPoints.setCellValueFactory(cellData -> cellData.getValue().getPlayerColorProperty());
        pointsMilitaryPoints.setCellValueFactory(cellData -> cellData.getValue().getMilitaryPointProperty().asString());

        nameVictoryPoints.setCellValueFactory(cellData -> cellData.getValue().getPlayerColorProperty());
        pointsVictoryPoints.setCellValueFactory(cellData -> cellData.getValue().getVictoryPointProperty().asString());

        showCards.setOpacity(0);
        this.clientHandler = ClientFactory.getClientHandler();
        this.adapter = new GUIAdapter(ClientFactory.getClientSender());

        try {
            if(clientHandler.isStarted())
                adapter.sendAction(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> {
            bindAllFamilyMember();
            disableTab();
            bindCardsToFloor();
            bindResources();
            bindTrackOrder();
            bindTrackMilitaryVictory();
        });
    }

    private void bindAllFamilyMember(){
        Map<PlayerColor, ObservableList<FamilyMemberRepresentation>> mapColorFamilyRepresentation = match.getMapPlayerColorObservableLiseFMRepr();
        for(Player player : match.getPlayers().values()){
            PlayerColor playerColor = player.getColor();
            for(FamilyMemberColor familyMemberColor: FamilyMemberColor.values()){
                //prendo la rappresentazione giusta tramite il famili member e ne prendo la property valore, lo setto poi alla label
                ObservableList<FamilyMemberRepresentation> famMembtakingvalue = mapColorFamilyRepresentation.get(playerColor).stream().filter(FM -> (FM.getColorsFamilyMemberPropertyString()).equals(familyMemberColor.toString())).collect(collectingAndThen(toList(), l -> FXCollections.observableArrayList(l)));
                StringBinding value = famMembtakingvalue.get(0).getValueProperty().asString();//valore da assegnare alla label
                mapPlayerColorFamilyColorLabel.get(playerColor).get(familyMemberColor).textProperty().bind((value));//bindo la property alla label
            }
        }

    }
    private void disableTab(){
        for(Player player : match.getPlayers().values()) {
            mapPlayerColorTab.get(player.getColor()).setDisable(false);
        }
    }
    private void bindCardsToFloor(){
        for(CardType cardType : CardType.values()){
                ObservableList<CardFloorRepresentation> cardFloorRepresentations = match.getMapTypeCardFloorRepresentation().get(cardType);
                for(CardFloorRepresentation cardFloorRepresentation : cardFloorRepresentations){
                    int floor = cardFloorRepresentation.getFloorNumber();
                    mapCardTypeMapIntegerCardFloors.get(cardType).get(floor).imageProperty().bind(match.getMapTypeCardFloorRepresentation().get(cardType).get(floor).getPathProperty());
                    mapCardTypeFloorCircle.get(cardType).get(floor).fillProperty().bind(match.getMapTypeCardFloorRepresentation().get(cardType).get(floor).pathWhenTakenProperty());
                }
        }
    }
    private void bindResources(){
        for(Player player : match.getPlayers().values()) {
            ObservableList<ResourceRepresentation> resourceRepresentation = match.getMapPlayerColorResourceRepresentation().get(player.getColor());
            mapPlayerColorResourceLabel.get(player.getColor()).get(ResourceType.MONEY).textProperty().bind(resourceRepresentation.get(0).getMoneyProperty().asString());
            mapPlayerColorResourceLabel.get(player.getColor()).get(ResourceType.WOOD).textProperty().bind(resourceRepresentation.get(0).getWoodProperty().asString());
            mapPlayerColorResourceLabel.get(player.getColor()).get(ResourceType.STONE).textProperty().bind(resourceRepresentation.get(0).getStoneProperty().asString());
            mapPlayerColorResourceLabel.get(player.getColor()).get(ResourceType.SERVANT).textProperty().bind(resourceRepresentation.get(0).getServantProperty().asString());
        }
    }
    private void bindTrackOrder(){
        List<Player> players = match.getBoard().getTrackTurnOrder().getOrderedPlayers();
        for(int i = 0; i < players.size(); i++){
            turnOrderTrack.get(i).fillProperty().bind(match.getTurnOrderTrackFirstPositionRepresentationObservableList().get(i).getPlayerProperty());
            turnOrderTrack.get(i).setOpacity(1);
        }
    }
    private void bindTrackMilitaryVictory(){
        tableMilitaryPoints.setItems(match.getAllResourcerepresentationMilitary());
        tableVictoryPoints.setItems(match.getAllResourcerepresentationVictory());

    }

   public TextArea getChat(){
        return chatTextArea;
   }
}
