package it.polimi.ingsw.gc12.view.client.gui;

import it.polimi.ingsw.gc12.model.action.Action;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.view.client.ClientFactory;
import it.polimi.ingsw.gc12.view.client.ClientHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javax.xml.soap.Text;
import java.util.List;
import java.util.Observable;

public abstract class GUIController extends Observable {

	@FXML
	protected ImageView showCards;

	@FXML
	protected Pane mainPane;

	protected ClientHandler clientHandler;
	protected PlayerColor playerColor;
	protected MatchInstanceGUI match;
	protected Player player;
	protected TextArea chat;

	public GUIController(ClientHandler clientHandler) {
		this.clientHandler = clientHandler;
		this.playerColor = clientHandler.getColor();
		this.match = (MatchInstanceGUI) clientHandler.getMatch();
		this.addObserver(ClientFactory.getClientSender());
	}

    /**
     * Method for checking if the action is avaliable.
     * If is avaliable it will be sended to  the server throw clientHandler
     * @param action action choosed
     * @return
     */
	protected boolean selectAction(Action action) {
		List<Action> actions = clientHandler.getActions();
		for (int i = 0; i < actions.size(); i++) {
			if(actions.get(i).equals(action)) {
				setChanged();
				notifyObservers(i);
				if(clientHandler.getEvents().size() > 0) {
					clientHandler.getEvents().removeFirst();
					clientHandler.handleEvent();
				}
				return true;
			}
		}
		return false;
	}

	protected boolean isMyTurn(){
		return clientHandler.getMyTurn();
	}

    /**
     * Not your turn alert
     */
	protected void showTurnDenied() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Not Your Turn");
		alert.setContentText("Wait your turn bra!");
		alert.showAndWait();
	}

    /**
     * Method for showing cards in the detail
     * @param mouseEvent
     */
	@FXML protected void showCard(MouseEvent mouseEvent) {
		checkExclusion();
		ImageView imageView = (ImageView) mouseEvent.getSource();
		Image card = imageView.getImage();
		showCards.setImage(card);
		showCards.setOpacity(1);
	}

	public void setShowCards(ImageView showCards) {
		this.showCards = showCards;
	}

	public void setChat(TextArea chat) {
		this.chat = chat;
	}

    /**
     * Notify the reconnection to the layer
     * @return boolean
     */
	protected boolean checkExclusion() {
		if(clientHandler.isExcluded()) {
			clientHandler.setExcluded(false);
			chat.appendText("[SERVER]: Welcome back! You can start playing again.\n");
			setChanged();
			notifyObservers(clientHandler.getColor());
			return false;
		}
		return true;
	}

	/**
	 * Alert for tell to the user that an action is not valid
	 */
	protected void actionDenied(){
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Action denied");
		alert.setHeaderText("You can't do this action");
		alert.setContentText("The action selected is not valid, sorry");
		alert.showAndWait();
	}
}
