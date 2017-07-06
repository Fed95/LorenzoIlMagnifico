package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.match.MatchInstance;
import it.polimi.ingsw.gc12.model.match.MatchInstanceCLI;
import it.polimi.ingsw.gc12.model.match.MatchInstanceGUI;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.view.client.ClientFactory;
import it.polimi.ingsw.gc12.view.client.ClientHandler;
import it.polimi.ingsw.gc12.view.client.ClientSender;
import it.polimi.ingsw.gc12.view.client.rmi.ClientRMI;
import it.polimi.ingsw.gc12.view.client.rmi.ClientViewRemote;
import it.polimi.ingsw.gc12.view.client.gui.MainBoard;
import it.polimi.ingsw.gc12.view.client.ClientView;
import it.polimi.ingsw.gc12.view.client.cli.ViewCLI;
import it.polimi.ingsw.gc12.view.server.RMIViewRemote;
import javafx.application.Platform;

import java.io.IOException;

public class EventPlayerReconnected extends Event implements EventView{
	private ClientViewRemote clientViewRemote;
	private RMIViewRemote serverRMI;
	private Match match;

	public EventPlayerReconnected(Player player, Match match, ClientViewRemote clientViewRemote) {
		super(player);
		this.clientViewRemote = clientViewRemote;
		this.match = match;
	}

	public EventPlayerReconnected(Player player, Match match) {
		super(player);
		this.match = match;
	}

	@Override
	public void executeClientSide(ClientHandler client) {
		if(client.getColor() == null) {
			if(serverRMI != null) {
				ClientSender clientSender = ClientFactory.getClientSender();
				if(clientSender instanceof ClientRMI) {
					ClientRMI clientRMI = (ClientRMI) clientSender;
					clientRMI.setServerStub(serverRMI);

				}
			}

			MatchInstance matchInstance = client.createMatchInstance();
			matchInstance.init(match);
			client.setMatch(matchInstance);
			client.setColor(player.getColor());
			client.getMatch().getBoard().setTowerSet(match.getBoard().getTowerSet());
			client.getMatch().setCards(match.getBoard().getTowerSet());
			client.getMatch().setDice(match.getBoard().getSpaceDie());
		}
	}

	private MatchInstance createMatchInstance(ClientView view) {
		if(view instanceof ViewCLI)
			return MatchInstanceCLI.instance();
		else
			return MatchInstanceGUI.instance();
	}


	public ClientViewRemote getClientViewRemote() {
		return clientViewRemote;
	}

	public void setServerRMI(RMIViewRemote serverRMI) {
		this.serverRMI = serverRMI;
	}

	public Match getMatch() {
		return match;
	}

	@Override
	public String toString() {
		return "";
	}

	@Override
	public void executeViewSide(MainBoard view) {
		Platform.runLater(() -> {
			try {
				view.changeScene("FXMLMainBoard", 1980, 1080, true, "Lorenzo il magnifico");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}
