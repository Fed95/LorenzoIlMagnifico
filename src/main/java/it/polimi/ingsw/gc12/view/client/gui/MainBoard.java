package it.polimi.ingsw.gc12.view.client.gui;

import it.polimi.ingsw.gc12.model.event.EventView;
import it.polimi.ingsw.gc12.view.client.ClientViewType;
import it.polimi.ingsw.gc12.view.client.ClientView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represent the main stage of the application before the login FXML
 * and then after login the MainBoardFXML
 */
public class MainBoard extends Application implements ClientView {
	private Stage primaryStage;
	private LoginController controller;
	private MainBoardController controllerMainBoard;
	private boolean ready;
	private final ClientViewType type = ClientViewType.GUI;

    /**
     * Initialize the login
     * @param primaryStage primary stage of the application
     * @throws Exception
     */
	public void start(Stage primaryStage) throws Exception{
	    this.primaryStage = primaryStage;
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainBoard.class.getResource("/FXML/LoginFXML.fxml"));
        Pane rootLayout = loader.load();
        LoginController controller = loader.getController();
        this.controller = controller;
        controller.setMainBoard(this);
        Scene scene = new Scene(rootLayout,688,454);
        primaryStage.setTitle("Benvenuto");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
		primaryStage.show();
		responsive(scene, rootLayout, primaryStage);
	}

    /**
     * Method that change scene with the FXML given
     * @param fxml
     * @param widthScene
     * @param heightScene
     * @param resize
     * @param title
     * @throws IOException
     */
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

    /**
     * Method that scale down the graphics whem the screen is resized
     * @param scene
     * @param root
     * @param primaryStage
     */
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
	public void update(EventView event) {
		event.executeViewSide(this);
	}

	@Override
	public void update() {}
}
