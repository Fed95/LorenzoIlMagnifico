package it.polimi.ingsw.gc_12.java_fx;


import it.polimi.ingsw.gc_12.client.ClientSender;
import it.polimi.ingsw.gc_12.mvc.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainBoard extends Application implements View{

	private ClientSender client;
	
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

	@Override
	public void start() throws IOException {
		launch();
	}

	@Override
	public ClientSender getClientSender() {
		return client;
	}
}
