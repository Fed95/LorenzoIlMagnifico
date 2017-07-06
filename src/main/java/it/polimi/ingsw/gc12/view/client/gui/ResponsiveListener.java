package it.polimi.ingsw.gc12.view.client.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;


/**
 * Created by rugge on 17/06/2017.
 */
public class ResponsiveListener implements ChangeListener<Number> {
    private final Scene scene;
    private final double ratio;
    private final double initHeight;
    private final double initWidth;
    private final Pane root;
    private final Stage primaryStage;
    private double adjust = 1.6;//adjust size for minor screen size

    public ResponsiveListener(Scene scene, double ratio, double initHeight, double initWidth, Pane root, Stage primaryStage) {
        this.scene = scene;
        this.ratio = ratio;
        this.initHeight = initHeight;
        this.initWidth = initWidth;
        this.root = root;
        this.primaryStage = primaryStage;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
        final double newWidth  = scene.getWidth();
        final double newHeight = scene.getHeight();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        int realWidth = (int)primaryScreenBounds.getWidth();
        int realHeight = (int)primaryScreenBounds.getHeight();
        if(realWidth == 1920 && realHeight == 1080){
            adjust = 1;
        }

        double scaleFactor;
        //usa come fattore di scala il lato piu piccolo  della finestra
        if(newWidth/newHeight > ratio){
            scaleFactor = (newHeight / initHeight)/adjust;
        }else{
            scaleFactor = (newWidth / initWidth)/adjust;
        }

        Scale scale = new Scale(scaleFactor, scaleFactor);
        scale.setPivotX(0);
        scale.setPivotY(0);
        scene.getRoot().getTransforms().setAll(scale);
    }
}
