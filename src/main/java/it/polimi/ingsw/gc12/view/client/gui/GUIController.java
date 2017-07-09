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

	protected void selectAction(Action action) {
		List<Action> actions = clientHandler.getActions();
		for (int i = 0; i < actions.size(); i++) {
			if(actions.get(i).equals(action)) {
				setChanged();
				notifyObservers(i);
				if(clientHandler.getEvents().size() > 0) {
					clientHandler.getEvents().removeFirst();
					clientHandler.handleEvent();
				}
				break;
			}
		}
	}

	protected boolean isMyTurn(){
		return clientHandler.getMyTurn();
	}

	protected void showTurnDenied() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Not Your Turn");
		alert.setContentText("Wait your turn bra!");
		alert.showAndWait();
	}

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
}
