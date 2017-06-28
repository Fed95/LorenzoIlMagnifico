package it.polimi.ingsw.gc_12.java_fx;


import it.polimi.ingsw.gc_12.client.Client;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.ClientSender;
import it.polimi.ingsw.gc_12.mvc.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainBoard extends Application implements View{
	private Stage primaryStage;
	private LoginController controller;
	private Client client;
	private boolean ready;

	public void start(Stage primaryStage) throws Exception{
	    this.primaryStage = primaryStage;
		FXMLLoader loader = new FXMLLoader();
		//loader.setLocation(MainBoard.class.getResource("/FXML/FXMLMainBoard.fxml"));
        loader.setLocation(MainBoard.class.getResource("/FXML/LoginFXML.fxml"));
        Pane rootLayout = loader.load();
        LoginController controller = loader.getController();
        this.controller = controller;
        controller.setMainBoard(this);
        //Parent rootLayout= FXMLLoader.load(getClass().getResource("/FXML/FXMLMainBoard.fxml"));
		//Scene scene = new Scene(rootLayout,1920,1080);
        Scene scene = new Scene(rootLayout,688,454);
        primaryStage.setTitle("Lorenzo il magnifico");
        primaryStage.setResizable(false);

        primaryStage.setScene(scene);
		primaryStage.show();
		responsive(scene, rootLayout, primaryStage);
	}

    public void changeScene(String fxml,int widthScene, int heightScene, Boolean resize, String title) throws IOException {
        FXMLLoader loader1 = new FXMLLoader();
        String path = "/FXML/"+fxml+".fxml";
        System.out.println(path);
        loader1.setLocation(MainBoard.class.getResource(path));
        Pane newLayout = loader1.load();
        Scene newScene = new Scene(newLayout,widthScene,heightScene);
        primaryStage.setResizable(resize);
        primaryStage.setTitle(title);
        primaryStage.setScene(newScene);
        primaryStage.show();
        responsive(newScene, newLayout, primaryStage);
        ready = true;

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
		return null;
	}

	@Override
	public void setClientSender(ClientSender clientSender) {

	}

	@Override
	public void setClientHandler(ClientHandler clientHandler) {

	}

    public LoginController getController() {
        return controller;
    }

	@Override
	public boolean isReady() {
		return ready;
	}
}
