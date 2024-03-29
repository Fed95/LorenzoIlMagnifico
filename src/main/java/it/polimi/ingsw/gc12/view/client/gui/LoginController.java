package it.polimi.ingsw.gc12.view.client.gui;

import it.polimi.ingsw.gc12.view.client.rmi.ClientRMI;
import it.polimi.ingsw.gc12.view.client.socket.ClientSocket;
import it.polimi.ingsw.gc12.view.client.ClientView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.util.ResourceBundle;


/**
 * Controller for the login popup asking for the name and the type of the connection
 */
public class LoginController implements Initializable {
    @FXML private TextField nameField;
    @FXML private ChoiceBox connectionChoice;
    @FXML private Button playButton;
    @FXML private Pane mainPane;
    private ObservableList<String> connectionList = FXCollections.observableArrayList("RMI","SOCKET");
    private ClientView mainBoard;

    /**
     * It start the right classes depending on the parameters choosed in the dialog
     * @param event click event
     * @throws NotBoundException
     * @throws AlreadyBoundException
     * @throws CloneNotSupportedException
     * @throws IOException
     */
    @FXML void startGame(MouseEvent event) throws NotBoundException, AlreadyBoundException, CloneNotSupportedException, IOException {
        String name = nameField.getText();
        name = name.replaceAll("\\s+",""); // Remove whitespaces and invisible characters
        String connection = connectionChoice.getValue().toString();
        if(isNameValid(name)) {
            playButton.setDisable(true);
            if(connection.equals("RMI")){
                ClientRMI clientRMI = new ClientRMI();
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

    /**
     * popup that check if the name of the player is valid
     * @param name
     * @return
     */
    private Boolean isNameValid(String name){

        if(name.equals("")){
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
            playButton.setDisable(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Name taken");
            alert.setContentText("This name is already taken from another active player.");
            alert.showAndWait();
        });
    }
}
