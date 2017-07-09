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
 * This class is implements the responsiveness of the GUI application, it scale down with the window
 */
public class ResponsiveListener implements ChangeListener<Number> {
    private final Scene scene;
    private final double ratio;
    private final double initHeight;
    private final double initWidth;
    private final Pane root;
    private final Stage primaryStage;
    private double adjust = 1;//adjust size for minor screen size

    /**
     *
     * @param scene scene to scale down
     * @param ratio required parameter to understand if we are horizontally or vertically scaling down
     * @param initHeight height
     * @param initWidth width
     * @param root root element to scale down
     * @param primaryStage primary stage to scale down
     */
    public ResponsiveListener(Scene scene, double ratio, double initHeight, double initWidth, Pane root, Stage primaryStage) {
        this.scene = scene;
        this.ratio = ratio;
        this.initHeight = initHeight;
        this.initWidth = initWidth;
        this.root = root;
        this.primaryStage = primaryStage;
    }

    /**
     * Set the scale down to the scene
     * @param observableValue
     * @param oldValue
     * @param newValue
     */
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
