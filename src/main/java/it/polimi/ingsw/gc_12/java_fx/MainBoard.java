package it.polimi.ingsw.gc_12.java_fx;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainBoard extends Application{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainBoard.class.getResource("/FXML/FXMLMainBoard.fxml"));
		Parent rootLayout = loader.load();
		//Parent rootLayout= FXMLLoader.load(getClass().getResource("/FXML/FXMLMainBoard.fxml"));
		Scene scene = new Scene(rootLayout,1920,1080);
		primaryStage.setTitle("Lorenzo il magnifico");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}
