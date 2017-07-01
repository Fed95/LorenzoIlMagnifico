package it.polimi.ingsw.gc_12.java_fx;

import it.polimi.ingsw.gc_12.MatchInstanceGUI;
import it.polimi.ingsw.gc_12.client.ClientFactory;
import it.polimi.ingsw.gc_12.client.ClientViewType;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventMatchInitialized;
import it.polimi.ingsw.gc_12.event.EventNewName;
import it.polimi.ingsw.gc_12.event.EventPlayerReconnected;
import it.polimi.ingsw.gc_12.mvc.ClientView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainBoard extends Application implements ClientView {
	private Stage primaryStage;
	private LoginController controller;
	private MainBoardController controllerMainBoard;
	private boolean ready;
	private final ClientViewType type = ClientViewType.GUI;

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
        primaryStage.setTitle("Benvenuto");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
		primaryStage.show();
		responsive(scene, rootLayout, primaryStage);
	}

    public void changeScene(String fxml,int widthScene, int heightScene, Boolean resize, String title) throws IOException {
        FXMLLoader loader1 = new FXMLLoader();
        String path = "/FXML/"+fxml+".fxml";
        loader1.setLocation(MainBoard.class.getResource(path));
        Pane newLayout = loader1.load();
        MainBoardController controller = loader1.getController();
        this.controllerMainBoard = controller;
        Scene newScene = new Scene(newLayout,widthScene,heightScene);
        primaryStage.setResizable(resize);
        primaryStage.setTitle(title);
        primaryStage.setScene(newScene);
        primaryStage.show();
        responsive(newScene, newLayout, primaryStage);
        ready = true;
		MatchInstanceGUI match = MatchInstanceGUI.instance();
		if(match.isInitialized()) {
			match.notifyInit();
		}

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

    public LoginController getLoginController() {
        return controller;
    }

    public MainBoardController getControllerMainBoard() {
        return controllerMainBoard;
    }

    @Override
	public boolean isReady() {
		return ready;
	}

	@Override
	public ClientViewType getType() {
		return type;
	}

	@Override
	public void update(Event event) {
		try {
			if(event instanceof EventNewName) {
				controller.showErrorNameTaken();
			}
			else if(event instanceof EventMatchInitialized) {
				if(ready) {
					controllerMainBoard.notifyObservers(0);
				}
				else
					controllerMainBoard.getClientHandler().setStarted(true);


					changeScene("FXMLMainBoard", 1980, 1080, true, "Lorenzo il magnifico");

			}
			else if(event instanceof EventPlayerReconnected) {
				changeScene("FXMLMainBoard", 1980, 1080, true, "Lorenzo il magnifico");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {

	}
}
