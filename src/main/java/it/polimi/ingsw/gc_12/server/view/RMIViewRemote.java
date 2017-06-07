package it.polimi.ingsw.gc_12.server.view;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.FamilyMemberColor;
import it.polimi.ingsw.gc_12.client.rmi.ClientViewRemote;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIViewRemote extends Remote {

	void registerClient(ClientViewRemote clientStub) throws RemoteException, AlreadyBoundException;
	void setFamilyMember(FamilyMember familyMember) throws RemoteException;

}
