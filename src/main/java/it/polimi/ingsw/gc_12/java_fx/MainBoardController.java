package it.polimi.ingsw.gc_12.java_fx;


import it.polimi.ingsw.gc_12.FamilyMemberColor;
import it.polimi.ingsw.gc_12.MatchInstance;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.PlayerColor;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.rmi.ClientRMI;
import it.polimi.ingsw.gc_12.mvc.GUIAdapter;
import javafx.application.Platform;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;


public class MainBoardController implements Initializable, Observer {
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
    private Map<FamilyMemberColor, Label> bluePlayerLabel = new HashMap<>();
    private Map<FamilyMemberColor, Label> greenPlayerLabel = new HashMap<>();
    private Map<FamilyMemberColor, Label> redPlayerLabel = new HashMap<>();
    private Map<FamilyMemberColor, Label> yellowPlayerLabel = new HashMap<>();
    private Map<PlayerColor , Map<FamilyMemberColor,Label>> mapPlayerColorFamilyColorLabel = new HashMap<>();

    private ImageView lastFamClicked = null;

    private GUIAdapter adapter;
    private MatchInstance match;
    private ClientHandler clientView;

    @FXML void familyClicked(MouseEvent event) {
        ImageView familyMemberClicked = (ImageView) event.getTarget();
        List<Action> actions = clientView.getActions();
        for(Action action: actions)
            System.out.println(action);



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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        match = MatchInstance.instance();
        match.addObserver(this);

        Image image = new Image("img/Card/card_1.png");
        Image image1 = new Image("img/Card/card_2.png");

        cardFloor0.setImage(image);
        cardFloor1.setImage(image1);
        cardFloor2.setImage(image);
        cardFloor3.setImage(image1);
        cardFloor4.setImage(image);
        cardFloor5.setImage(image1);
        cardFloor6.setImage(image);
        cardFloor7.setImage(image1);
        cardFloor8.setImage(image);
        cardFloor9.setImage(image1);
        cardFloor10.setImage(image);
        cardFloor11.setImage(image1);
        cardFloor12.setImage(image);
        cardFloor13.setImage(image1);
        cardFloor14.setImage(image);
        cardFloor15.setImage(image1);

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

        mapPlayerColorTab.put(PlayerColor.BLUE, bluePlayer);
        mapPlayerColorTab.put(PlayerColor.GREEN, greenPlayer);
        mapPlayerColorTab.put(PlayerColor.RED, redPlayer);
        mapPlayerColorTab.put(PlayerColor.YELLOW, yellowPlayer);

        bluePlayer.setDisable(true);
        greenPlayer.setDisable(true);
        redPlayer.setDisable(true);
        yellowPlayer.setDisable(true);

        showCards.setOpacity(0);
        this.clientView = ClientRMI.instance().getRmiView();
        this.adapter = new GUIAdapter(ClientRMI.instance());
        try {
            adapter.sendAction(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> {
            bindAllFamilyMember(match);
            disableTab(match);
        });
    }

    private void bindAllFamilyMember(MatchInstance match){
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
    private void disableTab(MatchInstance match){
        for(Player player : match.getPlayers().values()) {
            mapPlayerColorTab.get(player.getColor()).setDisable(false);
        }
    }
}
