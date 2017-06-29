package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.client.ClientFactory;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.ClientSender;
import it.polimi.ingsw.gc_12.client.rmi.ClientRMI;
import it.polimi.ingsw.gc_12.client.rmi.ClientViewRemote;
import it.polimi.ingsw.gc_12.client.socket.ClientInHandler;
import it.polimi.ingsw.gc_12.mvc.View;
import it.polimi.ingsw.gc_12.mvc.ViewCLI;
import it.polimi.ingsw.gc_12.server.view.RMIViewRemote;
import it.polimi.ingsw.gc_12.server.view.ServerRMIView;

import java.util.List;

public class EventPlayerReconnected extends Event {
	private ClientViewRemote clientRMI;
	private RMIViewRemote serverRMI;
	private Match match;

	public EventPlayerReconnected(Player player, Match match, ClientViewRemote clientRMI) {
		super(player);
		this.clientRMI = clientRMI;
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
					MatchInstance matchInstance = createMatchInstance(client.getView());
					matchInstance.init(match);
					client.setMatch(matchInstance);
					client.setColor(player.getColor());
				}
			}
		}
	}

	private MatchInstance createMatchInstance(View view) {
		if(view instanceof ViewCLI)
			return MatchInstanceCLI.instance();
		else
			return MatchInstanceGUI.instance();
	}


	public ClientViewRemote getClientRMI() {
		return clientRMI;
	}

	public void setServerRMI(RMIViewRemote serverRMI) {
		this.serverRMI = serverRMI;
	}

	public Match getMatch() {
		return match;
	}

	@Override
	public List<Object> getAttributes() {
		return null;
	}

	@Override
	public String toString() {
		return null;
	}
}
