package it.polimi.ingsw.gc_12.java_fx;

import it.polimi.ingsw.gc_12.client.ClientFactory;
import it.polimi.ingsw.gc_12.client.rmi.ClientRMI;
import it.polimi.ingsw.gc_12.client.socket.ClientSocket;
import it.polimi.ingsw.gc_12.mvc.View;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.util.ResourceBundle;


/**
 * Created by rugge on 28/06/2017.
 */
public class LoginController implements Initializable {
    @FXML private TextField nameField;
    @FXML private ChoiceBox connectionChoice;
    @FXML private Button playButton;
    @FXML private Pane mainPane;
    private ObservableList<String> connectionList = FXCollections.observableArrayList("RMI","SOCKET");
    private View mainBoard;

    @FXML void startGame(MouseEvent event) throws NotBoundException, AlreadyBoundException, CloneNotSupportedException, IOException {
        String name = nameField.getText();
        String connection = connectionChoice.getValue().toString();
        if(isNameValid(name)) {
            if(connection.equals("RMI")){
                ClientRMI clientRMI = new ClientRMI();
                ClientFactory.setClientSender(clientRMI);
                clientRMI.start(mainBoard, name);
            }else if(connection.equals("SOCKET")) {
                ClientSocket clientSocket = new ClientSocket();
                clientSocket.startClient(mainBoard, name);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectionChoice.setValue("RMI");
        connectionChoice.setItems(connectionList);
    }

    public void setMainBoard(MainBoard mainBoard){
        this.mainBoard = mainBoard;
    }

    private Boolean isNameValid(String name){
        if(name.equals("") || name.equals(" ")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty Name");
            alert.setContentText("The Name is required for starting the game");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public void showErrorNameTaken() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Name taken");
            alert.setContentText("This name is already taken from another active player.");
            alert.showAndWait();
        });
    }
}
