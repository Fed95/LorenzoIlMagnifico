package it.polimi.ingsw.gc12.view.client.gui;

import it.polimi.ingsw.gc12.misc.json.loader.LoaderConfigPlayers;
import it.polimi.ingsw.gc12.model.action.*;
import it.polimi.ingsw.gc12.model.board.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc12.model.board.occupiable.*;
import it.polimi.ingsw.gc12.model.match.ConfigPlayers;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.player.resource.*;
import it.polimi.ingsw.gc12.model.card.CardType;
import it.polimi.ingsw.gc12.view.client.ClientFactory;

import it.polimi.ingsw.gc12.misc.json.loader.LoaderConfig;
import it.polimi.ingsw.gc12.view.client.gui.representation.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
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


public class MainBoardController extends GUIController implements Initializable, Observer {
    //Card on floors

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
    private Map<CardType, List<ImageView>> cardFloors = new HashMap<>();

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
    private Map<CardType, List<ImageView>> towerFloors = new HashMap<>();

    @FXML private Circle firstPlayerTurn;
    @FXML private Circle secondPlayerTurn;
    @FXML private Circle thirdPlayerTurn;
    @FXML private Circle fourthPlayerTurn;
    private List<Circle> turnOrderTrack = new ArrayList<>();

    @FXML private TableView<ResourceRepresentation> tableVictoryPoints;
    @FXML private TableColumn<ResourceRepresentation, String> nameVictoryPoints;
    @FXML private TableColumn<ResourceRepresentation, String> pointsVictoryPoints;

    @FXML private TableView<ResourceRepresentation> tableMilitaryPoints;
    @FXML private TableColumn<ResourceRepresentation, String> nameMilitaryPoints;
    @FXML private TableColumn<ResourceRepresentation, String> pointsMilitaryPoints;

    @FXML private TableView<ResourceRepresentation> tableFaithPoints;
    @FXML private TableColumn<ResourceRepresentation, String> nameFaithPoints;
    @FXML private TableColumn<ResourceRepresentation, String> pointsFaithPoints;

    @FXML private ImageView excomTile1;
    @FXML private ImageView excomTile2;
    @FXML private ImageView excomTile3;
    private List<ImageView> excomTiles = new ArrayList<>();

    @FXML private TextArea chatTextArea;

    @FXML private Button passTurnPl1;

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

    @FXML private Circle excomm1Pl1;
    @FXML private Circle excomm1Pl2;
    @FXML private Circle excomm1Pl3;
    @FXML private Circle excomm1Pl4;
    private Map<PlayerColor, Circle> excommunication1PlayersColor = new HashMap<>();

    @FXML private Circle excomm2Pl1;
    @FXML private Circle excomm2Pl2;
    @FXML private Circle excomm2Pl3;
    @FXML private Circle excomm2Pl4;
    private Map<PlayerColor, Circle> excommunication2PlayersColor = new HashMap<>();

    @FXML private Circle excomm3Pl1;
    @FXML private Circle excomm3Pl2;
    @FXML private Circle excomm3Pl3;
    @FXML private Circle excomm3Pl4;
    private Map<PlayerColor, Circle> excommunication3PlayersColor = new HashMap<>();

    private List<Map<PlayerColor, Circle> > exomunicationOccupiableColors = new ArrayList<>();

    @FXML private ImageView councilPawn0;
    @FXML private ImageView councilPawn1;
    @FXML private ImageView councilPawn2;
    @FXML private ImageView councilPawn3;
    private List<ImageView>  councilPawns= new ArrayList<>();

    @FXML private ImageView harvestPawn0;
    @FXML private ImageView harvestPawn1;
    @FXML private ImageView harvestPawn2;
    @FXML private ImageView harvestPawn3;
    private List<ImageView>  harvestPawns = new ArrayList<>();

    @FXML private ImageView productionPawn0;
    @FXML private ImageView productionPawn1;
    @FXML private ImageView productionPawn2;
    @FXML private ImageView productionPawn3;
    private List<ImageView>  productionPawns = new ArrayList<>();

    private Map<WorkType, List<ImageView>> workSpacesPawns = new HashMap<>();

    private List<List<Resource>> councilPrivilegeResources =  new LoaderConfig().get(null).getCouncilPrivilegeResources();

    @FXML private PlayerBoardController playerBoardController;

    public MainBoardController() {
        super(ClientFactory.getClientHandler());
        match.addObserver(this);
    }

    @FXML synchronized void floorClicked(MouseEvent event){
        ImageView floorClicked = (ImageView) event.getTarget();
        if(isMyTurn()){
            loop: for(Map.Entry<CardType, List<ImageView>> entryType : towerFloors.entrySet()) {
                for (int i = 0; i < entryType.getValue().size(); i++) {
                    if(entryType.getValue().get(i).equals(floorClicked)) {
                        if(clientHandler.getActionPending() instanceof ActionPlace) {
                            selectAction(new DiscardAction(player));
                        }
                        Action action = new ActionChooseTower(match.getPlayers().get(playerColor), null, new Tower(entryType.getKey()));
                        if(clientHandler.getActions().contains(action))
                            clientHandler.setActionPending(new ActionPlaceOnTower(match.getPlayers().get(playerColor), null, new TowerFloor(i, entryType.getKey())));
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
        if(market.getUserData() != "block") {
            if (isMyTurn()) {
                for (int i = 0; i < markets.size(); i++) {
                    if (markets.get(i).equals(market)) {
                        Action action = new ActionChooseMarket(match.getPlayers().get(playerColor), null);
                        clientHandler.setActionPending(new ActionPlaceOnMarket(match.getPlayers().get(playerColor), null, new SpaceMarket(i, 1, new ArrayList<>())));
                        selectAction(action);
                        break;
                    }

                }
            } else
                showTurnDenied();
        }
    }

    @FXML void workspaceClicked(MouseEvent event){
        ImageView workplace = (ImageView) event.getTarget();
        if(workplace.getUserData() != "block") {
            if (isMyTurn()) {
                loop:
                for (Map.Entry<WorkType, List<ImageView>> entryType : workplaces.entrySet()) {
                    for (int i = 0; i < entryType.getValue().size(); i++) {
                        if (entryType.getValue().get(i).equals(workplace)) {
                            Action action = new ActionChooseWorkplace(match.getPlayers().get(playerColor), null);
                            if (i == 0)
                                clientHandler.setActionPending(new ActionPlaceOnSpaceWork(match.getPlayers().get(playerColor), null, new SpaceWorkSingle(entryType.getKey())));
                            else
                                clientHandler.setActionPending(new ActionPlaceOnSpaceWork(match.getPlayers().get(playerColor), null, new SpaceWorkMultiple(entryType.getKey())));
                            selectAction(action);
                            break loop;
                        }
                    }
                }
            } else
                showTurnDenied();
        }
    }

    @FXML void passTurn(){
        if(isMyTurn()) {
            Action action = new ActionPassTurn(match.getPlayers().get(playerColor));
            selectAction(action);
        }else
            showTurnDenied();
    }
    @FXML void whoWeAre(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Politecnico di Milano");
        alert.setHeaderText("Ingegneria del Software gruppo gc_12");
        alert.setContentText("Hi we are Ruggero, Marco and Federico the developers of Lorenzo il Magnifico pc game. Thanks for playing our game, hope you like it :) \n \n Cheers!!");

        alert.showAndWait();
    }

    public void actionDenied(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Action denied");
        alert.setHeaderText("You can't do this action");
        alert.setContentText("The action selected is not valid, sorry");
        alert.showAndWait();
    }
    public void requestServants() {
        if(isMyTurn()) {
            int maxValue = match.getPlayers().get(playerColor).getResourceValue(ResourceType.SERVANT);
            String request = "Insert the number of servants\nmin value: "+clientHandler.getOffset()+" max value: " + maxValue;
            int servants = askValue("servant", request, String.valueOf(clientHandler.getOffset()));
            Action actionPending = clientHandler.getActionPending();
            if(servants != -1 && actionPending instanceof ActionPlace) {
                ((ActionPlace)actionPending).setServants(new Servant(servants));
                selectAction(actionPending);
                clientHandler.setActionPending(null);
            }
        }
    }

    public void chooseExchange(List<ResourceExchange> resources) {
        if(isMyTurn()) {
            int choice = askWhich("resources", resources);
            ActionChooseExchange action = new ActionChooseExchange(match.getPlayers().get(playerColor), resources.get(choice));
            selectAction(action);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initializeAllMapsAndLists();
        playerBoardController.setShowCards(showCards);
        showCards.setOpacity(0);
        //this.addObserver(ClientFactory.getClientSender());
        clientHandler.setMainBoardController(this);
        if(clientHandler.isStarted())
            setChanged();


        //saying to the table where to find the values in the representation class for Military points
        nameMilitaryPoints.setCellValueFactory(cellData -> cellData.getValue().getPlayerColor().asString());
        pointsMilitaryPoints.setCellValueFactory(cellData -> cellData.getValue().getValue().asString());
        //saying to the table where to find the values in the representation class for Victory points
        nameVictoryPoints.setCellValueFactory(cellData -> cellData.getValue().getPlayerColor().asString());
        pointsVictoryPoints.setCellValueFactory(cellData -> cellData.getValue().getValue().asString());
        //saying to the table where to find the values in the representation class for Faith points
        nameFaithPoints.setCellValueFactory(cellData -> cellData.getValue().getPlayerColor().asString());
        pointsFaithPoints.setCellValueFactory(cellData -> cellData.getValue().getValue().asString());

        notifyObservers(0);
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> {
            blockOccupiable();
            playerBoardController.bindFamilyMembers();
            playerBoardController.disableTab();
            bindCardsToFloor();
            playerBoardController.bindResources();
            bindTrackOrder();
            bindTrackMilitaryVictory();
            bindExcomunocationTile();
            bindCouncilPawns();
            bindWorkSpaces();
            playerBoardController.setPlayerToPane();
            playerBoardController.bindPlayerCard();
            playerBoardController.bindPlayerLeaderCard();
            player = match.getPlayers().get(playerColor);
        });
    }

    private void blockOccupiable(){
        int playersNum = match.getPlayers().size();
        Match match = new Match();
        match.setPlayers(clientHandler.getMatch().getPlayers());
        ConfigPlayers config = new LoaderConfigPlayers().get(null).get(playersNum);
        if(config.getSpaceMarketNum() == 2) {
            blockMarkets();
        }

        if(!config.isSpaceWorkMultiple())
           blockSpaceWork();

    }

    private void blockMarkets() {
        Image blockMarket0 = new Image("img/block/market0Block.png");
        Image blockMarket1 = new Image("img/block/market1Block.png");
        market2.setImage(blockMarket0);
        market3.setImage(blockMarket1);
        market2.setLayoutY(843);
        market2.setLayoutX(489);
        market2.setFitWidth(70);
        market2.setFitHeight(77);

        market3.setLayoutY(892);
        market3.setLayoutX(539);
        market3.setFitWidth(70);
        market3.setFitHeight(77);
        market2.setUserData("block");
        market3.setUserData("block");
    }

    private void blockSpaceWork(){
        Image blockProduction = new Image("img/block/productionBlock.png");
        Image blockHarvest = new Image("img/block/harvestBlock.png");
        productionBig.setImage(blockProduction);
        harvestBig.setImage(blockHarvest);
        productionBig.setLayoutX(79);
        productionBig.setLayoutY(843);
        productionBig.setFitWidth(162);
        productionBig.setFitHeight(80);
        harvestBig.setFitHeight(79);
        harvestBig.setFitWidth(160);
        harvestBig.setLayoutY(931);
        harvestBig.setLayoutX(80);
        productionBig.setUserData("block");
        harvestBig.setUserData("block");
        harvestPawns = new ArrayList<>();
        productionPawns = new ArrayList<>();
    }

    private void bindCardsToFloor(){
        for(CardType cardType : CardType.values()){
            ObservableList<CardFloorRepresentation> cardFloorRepresentations = match.getCardsFloors().get(cardType);
            for(CardFloorRepresentation cardFloorRepresentation : cardFloorRepresentations){
                int floor = cardFloorRepresentation.getFloorNumber();
                cardFloors.get(cardType).get(floor).imageProperty().bind(match.getCardsFloors().get(cardType).get(floor).getPathProperty());
                towerFloors.get(cardType).get(floor).imageProperty().bind(match.getCardsFloors().get(cardType).get(floor).getPathFloorProperty());
            }
        }
    }

    private void bindTrackOrder(){
        List<Player> players = match.getBoard().getTrackTurnOrder().getOrderedPlayers();
        for(int i = 0; i < players.size(); i++){
            turnOrderTrack.get(i).fillProperty().bind(match.getTurnOrderTracks().get(i).getPlayerProperty());
            turnOrderTrack.get(i).setOpacity(1);
        }
    }

    private void bindTrackMilitaryVictory(){
        tableMilitaryPoints.setItems(match.getMilitaryResources());
        tableVictoryPoints.setItems(match.getVictoryResources());
        tableFaithPoints.setItems(match.getFaithResources());
    }

    private void bindExcomunocationTile(){
        for(int i = 0; i < excomTiles.size(); i++){
            int j = i+1;
            ExcommunicationTileRepresentation excommunicationTileRepresentation = match.getExcommunicationTiles().stream().filter(period -> period.getPeriod() == j).collect(collectingAndThen(toList(), l -> FXCollections.observableArrayList(l))).get(0);
            excomTiles.get(i).imageProperty().bind(excommunicationTileRepresentation.getpathProperty());
            for(PlayerColor playerColor : PlayerColor.values()) {
                exomunicationOccupiableColors.get(i).get(playerColor).fillProperty().bind(excommunicationTileRepresentation.getRetriveColorProperty().get(playerColor));
            }
        }
    }

    private void bindCouncilPawns(){
        for (int i = 0; i < 4; i++){
            councilPawns.get(i).imageProperty().bind(match.getCouncilPawns().get(i).getFamilyTemporaryImage());
        }
    }

    private void bindWorkSpaces(){
        ConfigPlayers config = new LoaderConfigPlayers().get(null).get(match.getPlayers().size());
        for(WorkType workType : WorkType.values()){
            ObjectProperty<Image> imageObjectProperty = match.getWorkSpacesPawn().get(workType).get(0).getFamilyTemporaryImage();
            workplaces.get(workType).get(0).imageProperty().bind(imageObjectProperty);
        }
        if(config.isSpaceWorkMultiple()) {
            for(WorkType workType : WorkType.values()){
                for (int i = 0; i < 4; i++){
                    workSpacesPawns.get(workType).get(i).imageProperty().bind(match.getWorkSpacesPawn().get(workType).get(i+1).getFamilyTemporaryImage());
                }
            }
        }
    }

    public TextArea getChat(){
        return chatTextArea;
    }

    public void sendAction() {
        if(isMyTurn()) {
            Action actionPending = clientHandler.getActionPending();
            if(actionPending != null) {
                if(clientHandler.getActions().contains(actionPending))
                    selectAction(actionPending);
                else {
                    clientHandler.setActionPending(clientHandler.getActionFM());
                    selectAction(new DiscardAction(player));
                }

            }
        }
    }


   private int askValue(String title, String request, String minValue){
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
           value = askValue(title,request, minValue);
       }
       else {
           Action action = new DiscardAction(match.getPlayers().get(playerColor));
           clientHandler.setActionPending(null);
           selectAction(action);
           return -1;
       }
       return value;
   }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
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

			} catch (IOException e) {
			    e.printStackTrace();
            }
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

	public void councilPalaceClicked() {
		if(isMyTurn()){
			Action action = new ActionPlaceOnCouncil(match.getPlayers().get(playerColor), null, new CouncilPalace(1));
			clientHandler.setActionPending(action);
			selectAction(action);
		}
		else
			showTurnDenied();
	}

    private int askWhich(String type, List<ResourceExchange> resources){
        int alternatives = resources.size();
        List<String> choices = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        int choosen = -1;
        for(ResourceExchange resourceExchange : resources){

            if(resourceExchange.getCost() != null && resourceExchange.getCost().size() > 0){
                str.append("Receive ");
                for (int i = 0; i < resourceExchange.getCost().size(); i++){
                    int value = resourceExchange.getCost().get(i).getValue();
                    String abbreviation = resourceExchange.getCost().get(i).getType().toString().substring(0,1);
                    str.append(" ").append(value).append(abbreviation);
                }
                if(resourceExchange.getBonus()!=null && resourceExchange.getBonus().size() > 0) {
                    str.append(" - ");
                }
            }

            if(resourceExchange.getBonus()!=null && resourceExchange.getBonus().size() > 0){
                str.append(" Spend: ");
                for (int i = 0; i < resourceExchange.getBonus().size(); i++){
                    int value = resourceExchange.getBonus().get(i).getValue();
                    String abbreviation = resourceExchange.getBonus().get(i).getType().toString().substring(0,1);
                    str.append(" ").append(value).append(abbreviation);
                }
            }
            choices.add(str.toString());
            str = new StringBuilder();
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Choose " + type);
        dialog.setHeaderText("You can choose between "+alternatives + " " + type);
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

    public void vaticanReport(PlayerColor playerColor, ExcommunicationTile tile) {
        if(this.playerColor.equals(playerColor)) {
            boolean support = askSupportChurch();
            Action action;
            if(support) {
                action = new ActionSupportChurch(player);
            }
            else
                action = new ActionReceiveExcommunication(player, tile);
            selectAction(action);
        }
    }

    private Boolean askSupportChurch(){
        Dialog dialog = new Dialog<>();
        dialog.setTitle("Support Church");
        dialog.setHeaderText("You have enough faith point \nfor this period for avoid the excommunication. \n\n Do you want to support the church?");
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/img/pointsTab/FAITH_POINT.png").toString()));
        ButtonType yesButtonType = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButtonType = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(yesButtonType, noButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.get() == yesButtonType){
            return true;
        } else if(result.get() == noButtonType) {
            return false;
        }
        return askSupportChurch();
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

    public void disablePassTurn(boolean disable) {
        if(!disable && !isMyTurn())
            return;

        passTurnPl1.setDisable(disable);
    }

    public void moveOccupiableImage(Occupiable occupiable) {
        if(occupiable instanceof TowerFloor) {
            TowerFloor floor = (TowerFloor) occupiable;
            towerFloors.get(floor.getType()).get(floor.getFloorNum()).setX(17);
            towerFloors.get(floor.getType()).get(floor.getFloorNum()).setY(-20);
        }
    }

    public void resetOccupiableImages() {
        for(CardType cardType: CardType.values()) {
            for(ImageView floor: towerFloors.get(cardType)) {
                floor.setX(0);
                floor.setY(0);
            }
        }
    }



    private void initializeAllMapsAndLists(){
        playerBoardController.setFamilyMembers();
        playerBoardController.setFamilyMemberLabels();
        playerBoardController.setPlayerTabs();
        setCardFloors();
        playerBoardController.setResourceLabels();
        setTowerFloors();
        setTrackTurnOrder();
        setExcommunicationTiles();
        setExcommunicationOccupiableColors();
        setMarkets();
        setWorkplaces();
        playerBoardController.setPlayerCards();
        playerBoardController.setCardLeaders();
        setCouncilPawns();
        setWorkSpaces();
    }

    private void setCardFloors() {
        //associating floor number to id
        cardFloors.put(CardType.TERRITORY, new ArrayList<>(Arrays.asList(cardFloor3, cardFloor2, cardFloor1, cardFloor0)));
        cardFloors.put(CardType.BUILDING, new ArrayList<>(Arrays.asList(cardFloor11, cardFloor10, cardFloor9, cardFloor8)));
        cardFloors.put(CardType.CHARACTER, new ArrayList<>(Arrays.asList(cardFloor7, cardFloor6, cardFloor5, cardFloor4)));
        cardFloors.put(CardType.VENTURE, new ArrayList<>(Arrays.asList(cardFloor15, cardFloor14, cardFloor13, cardFloor12)));
    }

    private void setTowerFloors() {
        //associating floor number to id
        towerFloors.put(CardType.TERRITORY, new ArrayList<>(Arrays.asList(floorToBeClicked3, floorToBeClicked2, floorToBeClicked1, floorToBeClicked0)));
        towerFloors.put(CardType.BUILDING, new ArrayList<>(Arrays.asList(floorToBeClicked11, floorToBeClicked10, floorToBeClicked9, floorToBeClicked8)));
        towerFloors.put(CardType.CHARACTER, new ArrayList<>(Arrays.asList(floorToBeClicked7, floorToBeClicked6, floorToBeClicked5, floorToBeClicked4)));
        towerFloors.put(CardType.VENTURE, new ArrayList<>(Arrays.asList(floorToBeClicked15, floorToBeClicked14, floorToBeClicked13, floorToBeClicked12)));
    }

    private void setTrackTurnOrder() {
        turnOrderTrack.add(firstPlayerTurn);
        turnOrderTrack.add(secondPlayerTurn);
        turnOrderTrack.add(thirdPlayerTurn);
        turnOrderTrack.add(fourthPlayerTurn);
    }

    private void setExcommunicationTiles() {
        excomTiles.add(excomTile1);
        excomTiles.add(excomTile2);
        excomTiles.add(excomTile3);
    }
    private void setExcommunicationOccupiableColors() {
       excommunication1PlayersColor.put(PlayerColor.BLUE, excomm1Pl1);
       excommunication1PlayersColor.put(PlayerColor.GREEN, excomm1Pl2);
       excommunication1PlayersColor.put(PlayerColor.RED, excomm1Pl3);
       excommunication1PlayersColor.put(PlayerColor.YELLOW, excomm1Pl4);

        excommunication2PlayersColor.put(PlayerColor.BLUE, excomm2Pl1);
        excommunication2PlayersColor.put(PlayerColor.GREEN, excomm2Pl2);
        excommunication2PlayersColor.put(PlayerColor.RED, excomm2Pl3);
        excommunication2PlayersColor.put(PlayerColor.YELLOW, excomm2Pl4);

        excommunication3PlayersColor.put(PlayerColor.BLUE, excomm3Pl1);
        excommunication3PlayersColor.put(PlayerColor.GREEN, excomm3Pl2);
        excommunication3PlayersColor.put(PlayerColor.RED, excomm3Pl3);
        excommunication3PlayersColor.put(PlayerColor.YELLOW, excomm3Pl4);

        exomunicationOccupiableColors.add(excommunication1PlayersColor);
        exomunicationOccupiableColors.add(excommunication2PlayersColor);
        exomunicationOccupiableColors.add(excommunication3PlayersColor);
    }
    private void setMarkets() {
        markets.add(market0);
        markets.add(market1);
        markets.add(market2);
        markets.add(market3);
    }

    private void setWorkplaces() {
        List<ImageView> productions = new ArrayList<>();
        productions.add(production);
        productions.add(productionBig);
        List<ImageView> harvests = new ArrayList<>();
        harvests.add(harvest);
        harvests.add(harvestBig);
        workplaces.put(WorkType.PRODUCTION, productions);
        workplaces.put(WorkType.HARVEST, harvests);
    }

    public void resetFamilyMembers() {
        playerBoardController.resetFamilyMembers();
    }

    private void setCouncilPawns(){
        councilPawns.add(councilPawn0);
        councilPawns.add(councilPawn1);
        councilPawns.add(councilPawn2);
        councilPawns.add(councilPawn3);
    }

    private void setWorkSpaces(){
        harvestPawns.add(harvestPawn0);
        harvestPawns.add(harvestPawn1);
        harvestPawns.add(harvestPawn2);
        harvestPawns.add(harvestPawn3);
        productionPawns.add(productionPawn0);
        productionPawns.add(productionPawn1);
        productionPawns.add(productionPawn2);
        productionPawns.add(productionPawn3);
        workSpacesPawns.put(WorkType.PRODUCTION, productionPawns);
        workSpacesPawns.put(WorkType.HARVEST, harvestPawns);
    }
}
