package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.client.ClientFactory;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.ClientSender;
import it.polimi.ingsw.gc_12.client.rmi.ClientRMI;
import it.polimi.ingsw.gc_12.client.rmi.ClientViewRemote;
import it.polimi.ingsw.gc_12.java_fx.MainBoard;
import it.polimi.ingsw.gc_12.mvc.ClientView;
import it.polimi.ingsw.gc_12.mvc.ViewCLI;
import it.polimi.ingsw.gc_12.server.view.RMIViewRemote;

import java.util.List;

public class EventPlayerReconnected extends Event {
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

			ClientView view = client.getView();
			if(view instanceof MainBoard) {
				changeMainBoard(client);
			}

			MatchInstance matchInstance = createMatchInstance(client.getView());
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
		return null;
	}
}
