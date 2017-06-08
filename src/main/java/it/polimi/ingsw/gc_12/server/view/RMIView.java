package it.polimi.ingsw.gc_12.server.view;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.FamilyMemberColor;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.action.ActionPlace;
import it.polimi.ingsw.gc_12.client.rmi.ClientViewRemote;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.server.Server;
import it.polimi.ingsw.gc_12.server.controller.Change;
import it.polimi.ingsw.gc_12.server.controller.StateChange;
import it.polimi.ingsw.gc_12.server.model.State;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

public class RMIView extends View implements RMIViewRemote {

	private Set<ClientViewRemote> clients;
	private Server server;
	private Match match;

	public RMIView(Server server) {
		this.clients = new HashSet<>();
		this.server = server;
	}

	@Override
	public void registerClient(ClientViewRemote clientStub) throws RemoteException, AlreadyBoundException {
		System.out.println("CLIENT REGISTRATO");

		this.clients.add(clientStub);
		for (ClientViewRemote clientViewRemote : clients) {
			System.out.println(clientViewRemote);
		}
		if(clients.size() == 2) {
			server.startMatch();
			server.newMatch();
		}
	}

	@Override
	public void update(Change o) {
		System.out.println("SENDING THE CHANGE TO THE CLIENT");
		try {
			for (ClientViewRemote clientstub : this.clients) {
				
				clientstub.updateClient(o);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
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
	public void chooseFamilyMember(ActionPlace action) throws RemoteException { 
		this.notifyObserver(action);
		
	}

	@Override
	public void setOccupiable(ActionPlace action) throws RemoteException {
		this.notifyObserver(action);
		
	}
}
