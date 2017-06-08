package it.polimi.ingsw.gc_12.server.view;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.FamilyMemberColor;
import it.polimi.ingsw.gc_12.action.ActionPlace;
import it.polimi.ingsw.gc_12.client.rmi.ClientViewRemote;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIViewRemote extends Remote {

	void registerClient(ClientViewRemote clientStub) throws RemoteException, AlreadyBoundException;
	void chooseFamilyMember(ActionPlace action) throws RemoteException;
	void setOccupiable(ActionPlace actionPlace) throws RemoteException;

}
