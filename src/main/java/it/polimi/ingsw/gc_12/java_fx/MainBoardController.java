package it.polimi.ingsw.gc_12.java_fx;


import it.polimi.ingsw.gc_12.MatchInstance;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.rmi.ClientRMI;
import it.polimi.ingsw.gc_12.mvc.GUIAdapter;
import javafx.application.Platform;
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
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;


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
    @FXML private Label orangeValuepl1;
    @FXML private Label whiteValuepl1;
    @FXML private Label neutralValuepl1;
    @FXML private Label blackValuePl2;
    @FXML private Label orangeValuepl2;
    @FXML private Label whiteValuepl2;
    @FXML private Label neutralValuepl2;
    @FXML private Label blackValuePl3;
    @FXML private Label orangeValuepl3;
    @FXML private Label whiteValuepl3;
    @FXML private Label neutralValuepl3;
    @FXML private Label blackValuePl4;
    @FXML private Label orangeValuepl4;
    @FXML private Label whiteValuepl4;
    @FXML private Label neutralValuepl4;
    //pane tb that contains tab of the players
    @FXML private TabPane playersBoards;

    //tab players
    @FXML private Tab bluePlayer;
    @FXML private Tab greenPlayer;
    @FXML private Tab redPlayer;
    @FXML private Tab yellowPlayer;

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
        match.getFamilyMemberBlueRepresentationObservableList().get(0).setValueProperty(10);
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
            blackValuePl1.textProperty().bind(match.getFamilyMemberBlueRepresentationObservableList().get(0).getValueProperty().asString());
        });
    }
}
