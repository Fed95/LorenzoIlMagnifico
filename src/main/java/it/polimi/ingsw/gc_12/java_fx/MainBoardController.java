package it.polimi.ingsw.gc_12.java_fx;

import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.action.*;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.ClientFactory;

import it.polimi.ingsw.gc_12.json.loader.LoaderConfig;
import it.polimi.ingsw.gc_12.occupiables.CouncilPalace;
import it.polimi.ingsw.gc_12.occupiables.Tower;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;
import it.polimi.ingsw.gc_12.resource.*;
import javafx.application.Platform;
import javafx.beans.binding.StringBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.Observable;
import java.util.Observer;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;


public class MainBoardController extends Observable implements Initializable, Observer {
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
    //players family member value
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
    //familyMember
    private Map<FamilyMemberColor, Label> bluePlayerLabel = new HashMap<>();
    private Map<FamilyMemberColor, Label> greenPlayerLabel = new HashMap<>();
    private Map<FamilyMemberColor, Label> redPlayerLabel = new HashMap<>();
    private Map<FamilyMemberColor, Label> yellowPlayerLabel = new HashMap<>();
    private Map<PlayerColor , Map<FamilyMemberColor,Label>> mapPlayerColorFamilyColorLabel = new HashMap<>();
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
    private Map<FamilyMemberColor, ImageView> famPl1 = new HashMap<>();
    private Map<FamilyMemberColor, ImageView> famPl2 = new HashMap<>();
    private Map<FamilyMemberColor, ImageView> famPl3 = new HashMap<>();
    private Map<FamilyMemberColor, ImageView> famPl4 = new HashMap<>();

    private Map<PlayerColor,Map<FamilyMemberColor, ImageView>>  mapPlayerColorFamImageView = new HashMap<>();

    @FXML private Pane mainPane;

    //pane tb that contains tab of the players
    @FXML private TabPane playersBoards;

    //tab players
    @FXML private Tab bluePlayer;
    @FXML private Tab greenPlayer;
    @FXML private Tab redPlayer;
    @FXML private Tab yellowPlayer;
    private Map<PlayerColor, Tab> mapPlayerColorTab = new HashMap<>();



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

    @FXML private ImageView floorToBeClicked0;
    @FXML private ImageView floorToBeClicked1;
    @FXML private ImageView floorToBeClicked2;
    @FXML private ImageView floorToBeClicked3;
    @FXML private ImageView floorToBeClicked4;
    @FXML private ImageView floorToBeClicked5;
    @FXML private ImageView floorToBeClicked6;
    @FXML private ImageView floorToBeClicked7;
    @FXML private ImageView floorToBeClicked8;
    @FXML private ImageView floorToBeClicked9;
    @FXML private ImageView floorToBeClicked10;
    @FXML private ImageView floorToBeClicked11;
    @FXML private ImageView floorToBeClicked12;
    @FXML private ImageView floorToBeClicked13;
    @FXML private ImageView floorToBeClicked14;
    @FXML private ImageView floorToBeClicked15;
    private Map<Integer, ImageView> mapfloorToBeClickedTerritory = new HashMap<>();
    private Map<Integer, ImageView> mapfloorToBeClickedBuildings = new HashMap<>();
    private Map<Integer, ImageView> mapfloorToBeClickedCharacter = new HashMap<>();
    private Map<Integer, ImageView> mapfloorToBeClickedVenture = new HashMap<>();
    private Map<CardType, Map<Integer, ImageView>> mapCardTypeFloorImageView = new HashMap<>();

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

    @FXML private ImageView excomTile1;
    @FXML private ImageView excomTile2;
    @FXML private ImageView excomTile3;
    private Map<Integer, ImageView> mapPeriodImageViewTile = new HashMap<>();

    @FXML private TextArea chatTextArea;

    @FXML private Button passTurnPl1;
    @FXML private Button passTurnPl2;
    @FXML private Button passTurnPl3;
    @FXML private Button passTurnPl4;
    Map<PlayerColor, Button> mapPlayerColorButton = new HashMap<>();

    @FXML private Button viewStatPl1;
    @FXML private Button viewStatPl2;
    @FXML private Button viewStatPl3;
    @FXML private Button viewStatPl4;




    private ImageView lastFamClicked = null;
    private MatchInstanceGUI match;
    private ClientHandler clientHandler;
    private Action actionPending;
    private List<List<Resource>> councilPrivilegeResources =  new LoaderConfig().get(null).getCouncilPrivilegeResources();

    @FXML void familyClicked(MouseEvent event) {
        ImageView familyMemberClicked = (ImageView) event.getTarget();
        PlayerColor color = (PlayerColor)mainPane.getUserData();
        //System.out.println(askMeAValue("servant","inserisci i servitori","0"));
        //System.out.println(askMePrivilege());
        if(isMyFam(color, familyMemberClicked) && isMyTurn()){
            highlightFamilyMember(familyMemberClicked);

            for (Map.Entry<FamilyMemberColor, ImageView> entry : mapPlayerColorFamImageView.get(color).entrySet()) {
                if(entry.getValue().equals(familyMemberClicked)) {
                    FamilyMember familyMember = new FamilyMember(color, entry.getKey());
                    ActionChooseFamilyMember action = new ActionChooseFamilyMember(match.getPlayers().get(color), familyMember);
                    selectAction(action);
                    break;
                }
            }
        }
        else
            showTurnDenied();

        //match.getFamilyMemberBlueRepresentationObservableList().get(0).setValueProperty(10);
    }

    @FXML void showCard(MouseEvent event){
        ImageView imageView = (ImageView) event.getSource();
        Image card = imageView.getImage();
        showCards.setImage(card);
        showCards.setOpacity(1);
    }

    @FXML synchronized void floorClicked(MouseEvent event){
        PlayerColor color = (PlayerColor)mainPane.getUserData();
        ImageView floorClicked = (ImageView) event.getTarget();
        if(lastFamClicked!=null && isMyTurn()){
            loop: for(Map.Entry<CardType, Map<Integer, ImageView>> entryType : mapCardTypeFloorImageView.entrySet()) {
                for(Map.Entry<Integer, ImageView> entryFloor: entryType.getValue().entrySet()) {
                    if(entryFloor.getValue().equals(floorClicked)) {
                        Action action = new ActionChooseTower(match.getPlayers().get(color), null, new Tower(entryType.getKey()));
                        actionPending = new ActionPlaceOnTower(match.getPlayers().get(color), null, new TowerFloor(entryFloor.getKey(), entryType.getKey()));
                        selectAction(action);
                        break loop;
                    }
                }
            }
        }
        else
            showTurnDenied();
    }
    @FXML void passTurn(){

    }
    @FXML void viewStat(){

    }
    private void selectAction(Action action) {
        List<Action> actions = clientHandler.getActions();
        for (int i = 0; i < actions.size(); i++) {
            if(actions.get(i).equals(action)) {
                setChanged();
                notifyObservers(i);
                clientHandler.getEvents().removeFirst();
                clientHandler.handleEvent();
                break;
            }
        }
    }

    public void requestServants() {
        if(isMyTurn()) {
            String request = "Insert the number of servants\nmin value: "+clientHandler.getOffset()+" max value: "+clientHandler.getActions().size();
            int servants = askMeAValue("servant", request, String.valueOf(clientHandler.getOffset()));
            if(actionPending instanceof ActionPlace) {
                ((ActionPlace)actionPending).setServants(new Servant(servants));
                selectAction(actionPending);
                actionPending = null;
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        match = MatchInstanceGUI.instance();
        match.addObserver(this);
        initializeAllMapsAndLists();

        //disabling all tab player
        bluePlayer.setDisable(true);
        greenPlayer.setDisable(true);
        redPlayer.setDisable(true);
        yellowPlayer.setDisable(true);

        //saying to the table where to find the values in the representation class for Military points
        nameMilitaryPoints.setCellValueFactory(cellData -> cellData.getValue().getPlayerColorProperty());
        pointsMilitaryPoints.setCellValueFactory(cellData -> cellData.getValue().getMilitaryPointProperty().asString());
        //saying to the table where to find the values in the representation class for Victory points
        nameVictoryPoints.setCellValueFactory(cellData -> cellData.getValue().getPlayerColorProperty());
        pointsVictoryPoints.setCellValueFactory(cellData -> cellData.getValue().getVictoryPointProperty().asString());


        showCards.setOpacity(0);
        this.clientHandler = ClientFactory.getClientHandler();
        this.addObserver(ClientFactory.getClientSender());
        clientHandler.setMainBoardController(this);
        if(clientHandler.isStarted())
            setChanged();
        notifyObservers(0);
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
            bindExcomunocationTile();
            setPlayerToPane();
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
            Tab tabToWorkWith = mapPlayerColorTab.get(player.getColor());
            tabToWorkWith.setDisable(false);
            String name= player.getName();
            if(name.length()>25){
                name = player.getName().substring(0,24)+"...";
            }
            tabToWorkWith.setText(name);
        }
    }

    private void bindCardsToFloor(){
        for(CardType cardType : CardType.values()){
            ObservableList<CardFloorRepresentation> cardFloorRepresentations = match.getMapTypeCardFloorRepresentation().get(cardType);
            for(CardFloorRepresentation cardFloorRepresentation : cardFloorRepresentations){
                int floor = cardFloorRepresentation.getFloorNumber();
                mapCardTypeMapIntegerCardFloors.get(cardType).get(floor).imageProperty().bind(match.getMapTypeCardFloorRepresentation().get(cardType).get(floor).getPathProperty());
                mapCardTypeFloorImageView.get(cardType).get(floor).imageProperty().bind(match.getMapTypeCardFloorRepresentation().get(cardType).get(floor).pathWhenTakenProperty());
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

    private void bindExcomunocationTile(){
        for(int i = 0; i < mapPeriodImageViewTile.values().size(); i++){
            int j = i+1;
            ExcommunicationTileRepresentation excommunicationTileRepresentation = match.getExcommunicationTileRepresentationObservableList().stream().filter(period -> period.getPeriod() == j).collect(collectingAndThen(toList(), l -> FXCollections.observableArrayList(l))).get(0);
            mapPeriodImageViewTile.get(i).imageProperty().bind(excommunicationTileRepresentation.getpathProperty());
        }
    }

    private void setPlayerToPane(){
        PlayerColor actualColor = clientHandler.getColor();
        playersBoards.getSelectionModel().select(mapPlayerColorTab.get(actualColor));
        mainPane.setUserData(actualColor);
        for(Button button : mapPlayerColorButton.values()){
            if(!button.equals(mapPlayerColorButton.get(actualColor))){
                mainPane.getChildren().remove(button);
            }
        }
    }

    private Boolean isMyFam(PlayerColor color, ImageView famMemb){
        Map<FamilyMemberColor,ImageView> allMyFamily = mapPlayerColorFamImageView.get(color);
        for(ImageView imageViewFam: allMyFamily.values()){
            if(imageViewFam.equals(famMemb)){
                return true;
            }
        }
        return false;
    }

    private boolean isMyTurn(){
        return clientHandler.getMyTurn();
    }

    public void showTurnDenied() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Not Your Turn");
        alert.setContentText("Wait your turn bra!");
        alert.showAndWait();
    }

    private void highlightFamilyMember(ImageView familyMemberClicked){

        if(familyMemberClicked.equals(lastFamClicked)){
            double selection = familyMemberClicked.getOpacity();
            if(selection != 1){
                familyMemberClicked.setOpacity(1);
                lastFamClicked = null;
            }
        }else{
            if(lastFamClicked!=null)
                lastFamClicked.setOpacity(1);
            familyMemberClicked.setOpacity(0.5);
            lastFamClicked = familyMemberClicked;
        }

    }

    public TextArea getChat(){
        return chatTextArea;
    }

    private void initializeAllMapsAndLists(){
        famPl1.put(FamilyMemberColor.BLACK, blueBlack);
        famPl1.put(FamilyMemberColor.WHITE, blueWhite);
        famPl1.put(FamilyMemberColor.ORANGE, blueOrange);
        famPl1.put(FamilyMemberColor.NEUTRAL, blueNeutral);

        famPl2.put(FamilyMemberColor.BLACK, greenBlack);
        famPl2.put(FamilyMemberColor.WHITE, greenWhite);
        famPl2.put(FamilyMemberColor.ORANGE, greenOrange);
        famPl2.put(FamilyMemberColor.NEUTRAL, greenNeutral);

        famPl3.put(FamilyMemberColor.BLACK, redBlack);
        famPl3.put(FamilyMemberColor.WHITE, redWhite);
        famPl3.put(FamilyMemberColor.ORANGE, redOrange);
        famPl3.put(FamilyMemberColor.NEUTRAL, redNeutral);

        famPl4.put(FamilyMemberColor.BLACK, yellowBlack);
        famPl4.put(FamilyMemberColor.WHITE, yellowWhite);
        famPl4.put(FamilyMemberColor.ORANGE, yellowOrange);
        famPl4.put(FamilyMemberColor.NEUTRAL, yellowNeutral);

        mapPlayerColorFamImageView.put(PlayerColor.BLUE, famPl1);
        mapPlayerColorFamImageView.put(PlayerColor.GREEN, famPl2);
        mapPlayerColorFamImageView.put(PlayerColor.RED, famPl3);
        mapPlayerColorFamImageView.put(PlayerColor.YELLOW, famPl4);


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

        mapCardTypeFloorImageView.put(CardType.TERRITORY, mapfloorToBeClickedTerritory);
        mapCardTypeFloorImageView.put(CardType.BUILDING, mapfloorToBeClickedBuildings);
        mapCardTypeFloorImageView.put(CardType.CHARACTER, mapfloorToBeClickedCharacter);
        mapCardTypeFloorImageView.put(CardType.VENTURE, mapfloorToBeClickedVenture);

        turnOrderTrack.add(firstPlayerTurn);
        turnOrderTrack.add(secondPlayerTurn);
        turnOrderTrack.add(thirdPlayerTurn);
        turnOrderTrack.add(fourthPlayerTurn);


        mapPeriodImageViewTile.put(0, excomTile1);
        mapPeriodImageViewTile.put(1, excomTile2);
        mapPeriodImageViewTile.put(2, excomTile3);

        mapPlayerColorButton.put(PlayerColor.BLUE, passTurnPl1);
        mapPlayerColorButton.put(PlayerColor.GREEN, passTurnPl2);
        mapPlayerColorButton.put(PlayerColor.RED, passTurnPl3);
        mapPlayerColorButton.put(PlayerColor.YELLOW, passTurnPl4);
    }

    public ClientHandler getClientHandler() {
        return clientHandler;
    }

    public void sendAction() {
        if(isMyTurn()) {
            if(actionPending != null) {
                selectAction(actionPending);
            }
            else {
                throw new IllegalStateException("Sending automatically action when it's not possible");
            }
        }
    }


   public int askMeAValue(String title, String request, String minValue){
       TextInputDialog dialog = new TextInputDialog(minValue);
       dialog.setTitle(title);
       dialog.setHeaderText(request);
       dialog.setContentText("Value:");
       Optional<String> result = dialog.showAndWait();
       int value = 0;
       if (result.isPresent()){
           String resToString = result.get();
           if(isInteger(resToString)) {
               int res = Integer.parseInt(resToString);
               if (res >= clientHandler.getOffset() && res < clientHandler.getActions().size())
                   return res;
           }
           value = askMeAValue(title,request, minValue);
       }
       return value;
   }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

    public void handleCouncilPrivilege(CouncilPrivilege councilPrivilege) {

   		if(isMyTurn()) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainBoard.class.getResource("/FXML/DialogCouncilPrivilegeFXML.fxml"));
				Pane page = loader.load();
				Stage dialogStage = new Stage();
				dialogStage.setTitle("Choose Privilege");
				dialogStage.setResizable(false);
				dialogStage.initModality(Modality.WINDOW_MODAL);
				Scene scene = new Scene(page);
				dialogStage.setScene(scene);
				DialogCouncilPrivilegeController controller = loader.getController();
				controller.setDialogStage(dialogStage);

				requestCouncilPrivileges(councilPrivilege, dialogStage, controller);

			} catch (IOException ignored) {}
			finally {
				clientHandler.getEvents().removeFirst();
				clientHandler.handleEvent();
			}
		}
    }

    private void requestCouncilPrivileges(CouncilPrivilege councilPrivilege, Stage dialogStage, DialogCouncilPrivilegeController controller) {
		PlayerColor color = (PlayerColor)mainPane.getUserData();
		while(councilPrivilege.getValue() > 0) {
			int choice = askPrivilege(dialogStage, controller);
			ResourceExchange exchange =	new ResourceExchange(new ArrayList<>(Collections.singletonList(new CouncilPrivilege(1))), councilPrivilegeResources.get(choice));
			Action action = new ActionChooseExchange(match.getPlayers().get(color), exchange);
			List<Action> actions = clientHandler.getActions();
			for (int i = 0; i < actions.size(); i++) {
				if(actions.get(i).equals(action)) {
					clientHandler.removeAction(i);
					councilPrivilege.setValue(councilPrivilege.getValue() - 1);
					setChanged();
					notifyObservers(i);
					break;
				}
			}
		}
	}

    public int askPrivilege(Stage dialogStage, DialogCouncilPrivilegeController controller) {
   		int privilege;
		dialogStage.showAndWait();

		if(controller.getSelected()!=-1){
			privilege = controller.getSelected();
			return privilege;
		}else{
			privilege = askPrivilege(dialogStage, controller);
		}
		return privilege;
    }

	public void councilPalaceClicked(MouseEvent event) {
		PlayerColor color = (PlayerColor)mainPane.getUserData();
		if(lastFamClicked!=null && isMyTurn()){
			Action action = new ActionPlaceOnCouncil(match.getPlayers().get(color), null, new CouncilPalace(1));
			actionPending = action;
			selectAction(action);
		}
		else
			showTurnDenied();
	}
}
