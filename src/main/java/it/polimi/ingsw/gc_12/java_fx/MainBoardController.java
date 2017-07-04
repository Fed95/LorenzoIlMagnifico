package it.polimi.ingsw.gc_12.java_fx;

import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.action.*;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.ClientFactory;

import it.polimi.ingsw.gc_12.json.loader.LoaderConfig;
import it.polimi.ingsw.gc_12.occupiables.*;
import it.polimi.ingsw.gc_12.resource.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.StringBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

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


    @FXML private ImageView councilPalace;
    @FXML private ImageView market0;
    @FXML private ImageView market1;
    @FXML private ImageView market2;
    @FXML private ImageView market3;
    private List<ImageView> markets = new ArrayList<>();

    @FXML private ImageView production;
    @FXML private ImageView productionBig;
    @FXML private ImageView harvest;
    @FXML private ImageView harvestBig;

    private Map<WorkType, List<ImageView>> workplaces = new HashMap<>();


    @FXML private ImageView territory0CardPlayer1;
    @FXML private ImageView territory1CardPlayer1;
    @FXML private ImageView territory2CardPlayer1;
    @FXML private ImageView territory3CardPlayer1;
    @FXML private ImageView territory4CardPlayer1;
    @FXML private ImageView territory5CardPlayer1;
    private List<ImageView> territoryPlayer1 = new ArrayList<>();

    @FXML private ImageView building0CardPlayer1;
    @FXML private ImageView building1CardPlayer1;
    @FXML private ImageView building2CardPlayer1;
    @FXML private ImageView building3CardPlayer1;
    @FXML private ImageView building4CardPlayer1;
    @FXML private ImageView building5CardPlayer1;
    private List<ImageView> buildingPlayer1 = new ArrayList<>();

    @FXML private ImageView character0CardPlayer1;
    @FXML private ImageView character1CardPlayer1;
    @FXML private ImageView character2CardPlayer1;
    @FXML private ImageView character3CardPlayer1;
    @FXML private ImageView character4CardPlayer1;
    @FXML private ImageView character5CardPlayer1;
    private List<ImageView> characterPlayer1 = new ArrayList<>();


    @FXML private ImageView venture0CardPlayer1;
    @FXML private ImageView venture1CardPlayer1;
    @FXML private ImageView venture2CardPlayer1;
    @FXML private ImageView venture3CardPlayer1;
    @FXML private ImageView venture4CardPlayer1;
    @FXML private ImageView venture5CardPlayer1;
    private List<ImageView> venturePlayer1 = new ArrayList<>();

    private Map<CardType, List<ImageView> > mapCardTypeImageViewPlayerCardPl1 = new HashMap<>();


    @FXML private ImageView territory0CardPlayer2;
    @FXML private ImageView territory1CardPlayer2;
    @FXML private ImageView territory2CardPlayer2;
    @FXML private ImageView territory3CardPlayer2;
    @FXML private ImageView territory4CardPlayer2;
    @FXML private ImageView territory5CardPlayer2;
    private List<ImageView> territoryPlayer2 = new ArrayList<>();

    @FXML private ImageView building0CardPlayer2;
    @FXML private ImageView building1CardPlayer2;
    @FXML private ImageView building2CardPlayer2;
    @FXML private ImageView building3CardPlayer2;
    @FXML private ImageView building4CardPlayer2;
    @FXML private ImageView building5CardPlayer2;
    private List<ImageView> buildingPlayer2 = new ArrayList<>();

    @FXML private ImageView character0CardPlayer2;
    @FXML private ImageView character1CardPlayer2;
    @FXML private ImageView character2CardPlayer2;
    @FXML private ImageView character3CardPlayer2;
    @FXML private ImageView character4CardPlayer2;
    @FXML private ImageView character5CardPlayer2;
    private List<ImageView> characterPlayer2 = new ArrayList<>();


    @FXML private ImageView venture0CardPlayer2;
    @FXML private ImageView venture1CardPlayer2;
    @FXML private ImageView venture2CardPlayer2;
    @FXML private ImageView venture3CardPlayer2;
    @FXML private ImageView venture4CardPlayer2;
    @FXML private ImageView venture5CardPlayer2;
    private List<ImageView> venturePlayer2 = new ArrayList<>();

    private Map<CardType, List<ImageView> > mapCardTypeImageViewPlayerCardPl2 = new HashMap<>();


    @FXML private ImageView territory0CardPlayer3;
    @FXML private ImageView territory1CardPlayer3;
    @FXML private ImageView territory2CardPlayer3;
    @FXML private ImageView territory3CardPlayer3;
    @FXML private ImageView territory4CardPlayer3;
    @FXML private ImageView territory5CardPlayer3;
    private List<ImageView> territoryPlayer3 = new ArrayList<>();

    @FXML private ImageView building0CardPlayer3;
    @FXML private ImageView building1CardPlayer3;
    @FXML private ImageView building2CardPlayer3;
    @FXML private ImageView building3CardPlayer3;
    @FXML private ImageView building4CardPlayer3;
    @FXML private ImageView building5CardPlayer3;
    private List<ImageView> buildingPlayer3 = new ArrayList<>();

    @FXML private ImageView character0CardPlayer3;
    @FXML private ImageView character1CardPlayer3;
    @FXML private ImageView character2CardPlayer3;
    @FXML private ImageView character3CardPlayer3;
    @FXML private ImageView character4CardPlayer3;
    @FXML private ImageView character5CardPlayer3;
    private List<ImageView> characterPlayer3 = new ArrayList<>();


    @FXML private ImageView venture0CardPlayer3;
    @FXML private ImageView venture1CardPlayer3;
    @FXML private ImageView venture2CardPlayer3;
    @FXML private ImageView venture3CardPlayer3;
    @FXML private ImageView venture4CardPlayer3;
    @FXML private ImageView venture5CardPlayer3;
    private List<ImageView> venturePlayer3 = new ArrayList<>();

    private Map<CardType, List<ImageView> > mapCardTypeImageViewPlayerCardPl3 = new HashMap<>();


    @FXML private ImageView territory0CardPlayer4;
    @FXML private ImageView territory1CardPlayer4;
    @FXML private ImageView territory2CardPlayer4;
    @FXML private ImageView territory3CardPlayer4;
    @FXML private ImageView territory4CardPlayer4;
    @FXML private ImageView territory5CardPlayer4;
    private List<ImageView> territoryPlayer4 = new ArrayList<>();

    @FXML private ImageView building0CardPlayer4;
    @FXML private ImageView building1CardPlayer4;
    @FXML private ImageView building2CardPlayer4;
    @FXML private ImageView building3CardPlayer4;
    @FXML private ImageView building4CardPlayer4;
    @FXML private ImageView building5CardPlayer4;
    private List<ImageView> buildingPlayer4 = new ArrayList<>();

    @FXML private ImageView character0CardPlayer4;
    @FXML private ImageView character1CardPlayer4;
    @FXML private ImageView character2CardPlayer4;
    @FXML private ImageView character3CardPlayer4;
    @FXML private ImageView character4CardPlayer4;
    @FXML private ImageView character5CardPlayer4;
    private List<ImageView> characterPlayer4 = new ArrayList<>();


    @FXML private ImageView venture0CardPlayer4;
    @FXML private ImageView venture1CardPlayer4;
    @FXML private ImageView venture2CardPlayer4;
    @FXML private ImageView venture3CardPlayer4;
    @FXML private ImageView venture4CardPlayer4;
    @FXML private ImageView venture5CardPlayer4;
    private List<ImageView> venturePlayer4 = new ArrayList<>();

    private Map<CardType, List<ImageView> > mapCardTypeImageViewPlayerCardPl4 = new HashMap<>();




    private Map<PlayerColor, Map<CardType, List<ImageView> > > mapPlayerColorCardTypeListImageView = new HashMap<>();


    private ImageView lastFamClicked = null;
    private MatchInstanceGUI match;
    private ClientHandler clientHandler;
    private Action actionPending;
    private List<List<Resource>> councilPrivilegeResources =  new LoaderConfig().get(null).getCouncilPrivilegeResources();
    private PlayerColor playerColor;

    @FXML void familyClicked(MouseEvent event) {
        ImageView familyMemberClicked = (ImageView) event.getTarget();

        if(isMyFam(playerColor, familyMemberClicked) && isMyTurn()){

            for (Map.Entry<FamilyMemberColor, ImageView> entry : mapPlayerColorFamImageView.get(playerColor).entrySet()) {
                if(entry.getValue().equals(familyMemberClicked)) {
                    FamilyMember familyMember = new FamilyMember(playerColor, entry.getKey());
                    Action action;
                    if(actionPending != null) {
                        action = new DiscardAction(match.getPlayers().get(playerColor));
                        actionPending = new ActionChooseFamilyMember(match.getPlayers().get(playerColor), familyMember);
                    }
                    else {
                        action = new ActionChooseFamilyMember(match.getPlayers().get(playerColor), familyMember);
                        actionPending = action;
                    }
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
        ImageView floorClicked = (ImageView) event.getTarget();
        if(lastFamClicked!=null && isMyTurn()){
            loop: for(Map.Entry<CardType, Map<Integer, ImageView>> entryType : mapCardTypeFloorImageView.entrySet()) {
                for(Map.Entry<Integer, ImageView> entryFloor: entryType.getValue().entrySet()) {
                    if(entryFloor.getValue().equals(floorClicked)) {
                        Action action = new ActionChooseTower(match.getPlayers().get(playerColor), null, new Tower(entryType.getKey()));
                        actionPending = new ActionPlaceOnTower(match.getPlayers().get(playerColor), null, new TowerFloor(entryFloor.getKey(), entryType.getKey()));
                        selectAction(action);
                        break loop;
                    }
                }
            }
        }
        else
            showTurnDenied();
    }

    @FXML void marketClicked(MouseEvent event){
        ImageView market = (ImageView) event.getTarget();
        if(lastFamClicked!=null && isMyTurn()){
            for (int i = 0; i < markets.size(); i++) {
                if(markets.get(i).equals(market)) {
                    Action action = new ActionChooseMarket(match.getPlayers().get(playerColor), null);
                    actionPending = new ActionPlaceOnMarket(match.getPlayers().get(playerColor), null, new SpaceMarket(i, 1, new ArrayList<>()));
                    selectAction(action);
                    break;
                }

            }
        }
        else
            showTurnDenied();
    }

    @FXML void workspaceClicked(MouseEvent event){
        ImageView workplace = (ImageView) event.getTarget();
        if(lastFamClicked!=null && isMyTurn()){
            loop: for(Map.Entry<WorkType, List<ImageView>> entryType : workplaces.entrySet()) {
                for (int i = 0; i < entryType.getValue().size(); i++) {
                    if(entryType.getValue().get(i).equals(workplace)) {
                        Action action = new ActionChooseWorkplace(match.getPlayers().get(playerColor), null);
                        if(i == 0)
                            actionPending = new ActionPlaceOnSpaceWork(match.getPlayers().get(playerColor), null, new SpaceWorkSingle(entryType.getKey()));
                        else
                            actionPending = new ActionPlaceOnSpaceWork(match.getPlayers().get(playerColor), null, new SpaceWorkMultiple(entryType.getKey()));
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
        Action action = new ActionPassTurn(match.getPlayers().get(playerColor));
        selectAction(action);
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
        playerColor = clientHandler.getColor();
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
            bindPlayerCard();
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
    private void bindPlayerCard(){
        for(Player player : match.getPlayers().values()) {
            for(CardType cardType : CardType.values()) {
                for(int i = 0; i < 6; i++) {
                    CardPlayerRepresentation cardPlayerRepresentation = match.getMapPlayerColorCardTypePlayerCard().get(player.getColor()).get(cardType).get(i);
                    mapPlayerColorCardTypeListImageView.get(player.getColor()).get(cardType).get(i).imageProperty().bind(cardPlayerRepresentation.getPathProperty());
                }
            }
        }
    }
    private void setPlayerToPane(){
        playersBoards.getSelectionModel().select(mapPlayerColorTab.get(playerColor));
        mainPane.setUserData(playerColor);
        for(Button button : mapPlayerColorButton.values()){
            if(!button.equals(mapPlayerColorButton.get(playerColor))){
                button.setDisable(true);
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

    private void showTurnDenied() {
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


        markets.add(market0);
        markets.add(market1);
        markets.add(market2);
        markets.add(market3);

        List<ImageView> productions = new ArrayList<>();
        productions.add(production);
        productions.add(productionBig);
        List<ImageView> harvests = new ArrayList<>();
        harvests.add(harvest);
        harvests.add(harvestBig);
        workplaces.put(WorkType.PRODUCTION, productions);
        workplaces.put(WorkType.HARVEST, harvests);

        //all card of player 1
        territoryPlayer1.add(territory0CardPlayer1);
        territoryPlayer1.add(territory1CardPlayer1);
        territoryPlayer1.add(territory2CardPlayer1);
        territoryPlayer1.add(territory3CardPlayer1);
        territoryPlayer1.add(territory4CardPlayer1);
        territoryPlayer1.add(territory5CardPlayer1);

        venturePlayer1.add(venture0CardPlayer1);
        venturePlayer1.add(venture1CardPlayer1);
        venturePlayer1.add(venture2CardPlayer1);
        venturePlayer1.add(venture3CardPlayer1);
        venturePlayer1.add(venture4CardPlayer1);
        venturePlayer1.add(venture5CardPlayer1);

        buildingPlayer1.add(building0CardPlayer1);
        buildingPlayer1.add(building1CardPlayer1);
        buildingPlayer1.add(building2CardPlayer1);
        buildingPlayer1.add(building3CardPlayer1);
        buildingPlayer1.add(building4CardPlayer1);
        buildingPlayer1.add(building5CardPlayer1);

        characterPlayer1.add(character0CardPlayer1);
        characterPlayer1.add(character1CardPlayer1);
        characterPlayer1.add(character2CardPlayer1);
        characterPlayer1.add(character3CardPlayer1);
        characterPlayer1.add(character4CardPlayer1);
        characterPlayer1.add(character5CardPlayer1);

        mapCardTypeImageViewPlayerCardPl1.put(CardType.TERRITORY, territoryPlayer1);
        mapCardTypeImageViewPlayerCardPl1.put(CardType.BUILDING, buildingPlayer1);
        mapCardTypeImageViewPlayerCardPl1.put(CardType.VENTURE, venturePlayer1);
        mapCardTypeImageViewPlayerCardPl1.put(CardType.CHARACTER, characterPlayer1);

        //all card of player 2
        territoryPlayer2.add(territory0CardPlayer2);
        territoryPlayer2.add(territory1CardPlayer2);
        territoryPlayer2.add(territory2CardPlayer2);
        territoryPlayer2.add(territory3CardPlayer2);
        territoryPlayer2.add(territory4CardPlayer2);
        territoryPlayer2.add(territory5CardPlayer2);

        venturePlayer2.add(venture0CardPlayer2);
        venturePlayer2.add(venture1CardPlayer2);
        venturePlayer2.add(venture2CardPlayer2);
        venturePlayer2.add(venture3CardPlayer2);
        venturePlayer2.add(venture4CardPlayer2);
        venturePlayer2.add(venture5CardPlayer2);

        buildingPlayer2.add(building0CardPlayer2);
        buildingPlayer2.add(building1CardPlayer2);
        buildingPlayer2.add(building2CardPlayer2);
        buildingPlayer2.add(building3CardPlayer2);
        buildingPlayer2.add(building4CardPlayer2);
        buildingPlayer2.add(building5CardPlayer2);

        characterPlayer2.add(character0CardPlayer2);
        characterPlayer2.add(character1CardPlayer2);
        characterPlayer2.add(character2CardPlayer2);
        characterPlayer2.add(character3CardPlayer2);
        characterPlayer2.add(character4CardPlayer2);
        characterPlayer2.add(character5CardPlayer2);

        mapCardTypeImageViewPlayerCardPl2.put(CardType.TERRITORY, territoryPlayer2);
        mapCardTypeImageViewPlayerCardPl2.put(CardType.BUILDING, buildingPlayer2);
        mapCardTypeImageViewPlayerCardPl2.put(CardType.VENTURE, venturePlayer2);
        mapCardTypeImageViewPlayerCardPl2.put(CardType.CHARACTER, characterPlayer2);

        //all card of player 3
        territoryPlayer3.add(territory0CardPlayer3);
        territoryPlayer3.add(territory1CardPlayer3);
        territoryPlayer3.add(territory2CardPlayer3);
        territoryPlayer3.add(territory3CardPlayer3);
        territoryPlayer3.add(territory4CardPlayer3);
        territoryPlayer3.add(territory5CardPlayer3);

        venturePlayer3.add(venture0CardPlayer3);
        venturePlayer3.add(venture1CardPlayer3);
        venturePlayer3.add(venture2CardPlayer3);
        venturePlayer3.add(venture3CardPlayer3);
        venturePlayer3.add(venture4CardPlayer3);
        venturePlayer3.add(venture5CardPlayer3);

        buildingPlayer3.add(building0CardPlayer3);
        buildingPlayer3.add(building1CardPlayer3);
        buildingPlayer3.add(building2CardPlayer3);
        buildingPlayer3.add(building3CardPlayer3);
        buildingPlayer3.add(building4CardPlayer3);
        buildingPlayer3.add(building5CardPlayer3);

        characterPlayer3.add(character0CardPlayer3);
        characterPlayer3.add(character1CardPlayer3);
        characterPlayer3.add(character2CardPlayer3);
        characterPlayer3.add(character3CardPlayer3);
        characterPlayer3.add(character4CardPlayer3);
        characterPlayer3.add(character5CardPlayer3);

        mapCardTypeImageViewPlayerCardPl3.put(CardType.TERRITORY, territoryPlayer3);
        mapCardTypeImageViewPlayerCardPl3.put(CardType.BUILDING, buildingPlayer3);
        mapCardTypeImageViewPlayerCardPl3.put(CardType.VENTURE, venturePlayer3);
        mapCardTypeImageViewPlayerCardPl3.put(CardType.CHARACTER, characterPlayer3);

        //all card of player 4
        territoryPlayer4.add(territory0CardPlayer4);
        territoryPlayer4.add(territory1CardPlayer4);
        territoryPlayer4.add(territory2CardPlayer4);
        territoryPlayer4.add(territory3CardPlayer4);
        territoryPlayer4.add(territory4CardPlayer4);
        territoryPlayer4.add(territory5CardPlayer4);

        venturePlayer4.add(venture0CardPlayer4);
        venturePlayer4.add(venture1CardPlayer4);
        venturePlayer4.add(venture2CardPlayer4);
        venturePlayer4.add(venture3CardPlayer4);
        venturePlayer4.add(venture4CardPlayer4);
        venturePlayer4.add(venture5CardPlayer4);

        buildingPlayer4.add(building0CardPlayer4);
        buildingPlayer4.add(building1CardPlayer4);
        buildingPlayer4.add(building2CardPlayer4);
        buildingPlayer4.add(building3CardPlayer4);
        buildingPlayer4.add(building4CardPlayer4);
        buildingPlayer4.add(building5CardPlayer4);

        characterPlayer4.add(character0CardPlayer4);
        characterPlayer4.add(character1CardPlayer4);
        characterPlayer4.add(character2CardPlayer4);
        characterPlayer4.add(character3CardPlayer4);
        characterPlayer4.add(character4CardPlayer4);
        characterPlayer4.add(character5CardPlayer4);

        mapCardTypeImageViewPlayerCardPl4.put(CardType.TERRITORY, territoryPlayer4);
        mapCardTypeImageViewPlayerCardPl4.put(CardType.BUILDING, buildingPlayer4);
        mapCardTypeImageViewPlayerCardPl4.put(CardType.VENTURE, venturePlayer4);
        mapCardTypeImageViewPlayerCardPl4.put(CardType.CHARACTER, characterPlayer4);

        mapPlayerColorCardTypeListImageView.put(PlayerColor.BLUE, mapCardTypeImageViewPlayerCardPl1);
        mapPlayerColorCardTypeListImageView.put(PlayerColor.GREEN, mapCardTypeImageViewPlayerCardPl2);
        mapPlayerColorCardTypeListImageView.put(PlayerColor.RED, mapCardTypeImageViewPlayerCardPl3);
        mapPlayerColorCardTypeListImageView.put(PlayerColor.YELLOW, mapCardTypeImageViewPlayerCardPl4);


    }

    public ClientHandler getClientHandler() {
        return clientHandler;
    }

    public void sendAction() {
        if(isMyTurn()) {
            if(actionPending != null) {
                selectAction(actionPending);
            }
        }
    }

    public void resetActionPending() {
        actionPending = null;
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
       else {
           Action action = new DiscardAction(match.getPlayers().get(playerColor));
           selectAction(action);
           resetActionPending();
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
		while(councilPrivilege.getValue() > 0) {
			int choice = askPrivilege(dialogStage, controller);
			ResourceExchange exchange =	new ResourceExchange(new ArrayList<>(Collections.singletonList(new CouncilPrivilege(1))), councilPrivilegeResources.get(choice));
			Action action = new ActionChooseExchange(match.getPlayers().get(playerColor), exchange);
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

    private int askPrivilege(Stage dialogStage, DialogCouncilPrivilegeController controller) {
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
		if(lastFamClicked!=null && isMyTurn()){
			Action action = new ActionPlaceOnCouncil(match.getPlayers().get(playerColor), null, new CouncilPalace(1));
			actionPending = action;
			selectAction(action);
		}
		else
			showTurnDenied();
	}

    public int askWhich(String type, List<ResourceExchange> resources){
        int alternatives = resources.size();
        List<String> choices = new ArrayList<>();
        String str = "";
        int choosen = -1;
        for(ResourceExchange resourceExchange : resources){
            if(resourceExchange.getCost()!=null){
                for (int i = 0; i < resourceExchange.getCost().size(); i++){
                    int value = resourceExchange.getCost().get(i).getValue();
                    String abbreviation = resourceExchange.getCost().get(i).getType().toString().substring(0,1);
                    str = str+" "+value+abbreviation;
                }
            }
            if(resourceExchange.getBonus()!=null){
                str = str+"-->";
                for (int i = 0; i < resourceExchange.getBonus().size(); i++){
                    int value = resourceExchange.getBonus().get(i).getValue();
                    String abbreviation = resourceExchange.getBonus().get(i).getType().toString().substring(0,1);
                    str = str+" "+value+abbreviation;
                }
            }
            choices.add(str);
            str = "";
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Choose " +type);
        dialog.setHeaderText("You can choose between "+alternatives+" "+type);
        dialog.setContentText("Choose :");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            choosen = choices.indexOf(result.get());
        }
        if(choosen==-1){
            choosen = askWhich(type, resources);
        }

        return choosen;
    }

	private void applyPulseEffect(Color color, List<Node> nodes) {
        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(color);
        borderGlow.setSpread(0.5);
        for (Node node: nodes)
            node.setEffect(borderGlow);

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(borderGlow.colorProperty(), Color.TRANSPARENT);
        final KeyFrame kf = new KeyFrame(Duration.millis(1000), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    private void removeEffects(List<Node> nodes) {
       for(Node node: nodes) {
           node.setEffect(null);
       }
    }
}
