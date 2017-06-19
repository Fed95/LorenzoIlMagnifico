package it.polimi.ingsw.gc_12.java_fx;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainBoard extends Application{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainBoard.class.getResource("/FXML/FXMLMainBoard.fxml"));
		Pane rootLayout = loader.load();
		//Parent rootLayout= FXMLLoader.load(getClass().getResource("/FXML/FXMLMainBoard.fxml"));
		Scene scene = new Scene(rootLayout,1920,1080);
		primaryStage.setTitle("Lorenzo il magnifico");


		primaryStage.setScene(scene);
		primaryStage.show();
		responsive(scene, rootLayout, primaryStage);
	}
	private void responsive(Scene scene, Pane root, Stage primaryStage){
		final double initWidth  = scene.getWidth();
		final double initHeight = scene.getHeight();
		final double ratio      = initWidth / initHeight;
		ResponsiveListener sizeListener = new ResponsiveListener(scene, ratio, initHeight, initWidth, root, primaryStage);
		scene.widthProperty().addListener(sizeListener);
		scene.heightProperty().addListener(sizeListener);
	}
}
