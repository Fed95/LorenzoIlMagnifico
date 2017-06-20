package it.polimi.ingsw.gc_12.server.view;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.FamilyMemberColor;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.action.ActionPlace;
import it.polimi.ingsw.gc_12.client.rmi.ClientViewRemote;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.server.Server;
import it.polimi.ingsw.gc_12.server.controller.Change;
import it.polimi.ingsw.gc_12.server.controller.StateChange;
import it.polimi.ingsw.gc_12.server.model.State;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

public class ServerRMIView extends View implements RMIViewRemote {

	private Set<ClientViewRemote> clients;
	private Server server;
	private Match match;

	public ServerRMIView(Server server, Match match) {
		this.clients = new HashSet<>();
		this.server = server;
		this.match = match;
	}

	@Override
	public void registerClient(ClientViewRemote clientStub) throws IOException, AlreadyBoundException, CloneNotSupportedException {
		System.out.println("CLIENT REGISTERED");

		this.clients.add(clientStub);
		for (ClientViewRemote clientViewRemote : clients) {
			System.out.println(clientViewRemote);
		}
		server.numOfClients++;
		if(clients.size() == 2) {
			server.startMatch();
			server.newMatch();
		}
	}

	@Override
	public void update(Event event) {
		System.out.println("RMIVIEW: SENDING " + event.getClass() + " CHANGE TO THE CLIENT");
		for (ClientViewRemote clientStub : this.clients) {
			try {
				clientStub.updateClient(event);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	public Set<ClientViewRemote> getClients() {
		return clients;
	}

	@Override
	public void receiveAction(int input){
		Action action = match.getActionHandler().getAvailableAction(input);
		System.out.println("ServerRMIView: " + action.getClass().getSimpleName() + " received from ClientRMI. Notifying observers (Server Controller).");
		this.notifyObserver(action);
	}

	public Match getMatch() {
		return match;
	}
}