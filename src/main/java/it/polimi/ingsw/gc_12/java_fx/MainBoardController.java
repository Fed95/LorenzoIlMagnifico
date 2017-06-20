package it.polimi.ingsw.gc_12.java_fx;


import it.polimi.ingsw.gc_12.event.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;


public class MainBoardController implements Initializable {
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
    private ImageView lastFamClicked = null;

    @FXML void familyClicked(MouseEvent event) {
        ImageView familyMemberClicked = (ImageView) event.getSource();
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
    }
    @FXML void showCard(MouseEvent event){
        ImageView imageView = (ImageView) event.getSource();
        Image card = imageView.getImage();
        showCards.setImage(card);
        showCards.setOpacity(1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    }
}
