package it.polimi.ingsw.gc_12;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.occupiables.SpaceWorkZone;
import it.polimi.ingsw.gc_12.occupiables.Tower;
import it.polimi.ingsw.gc_12.occupiables.TowerSet;

/**
 * Created by marco on 07/06/2017.
 */
public interface MatchRemote extends Remote {

	MatchInstance getInstance() throws RemoteException, CloneNotSupportedException;
	Player getCurrentPlayer() throws RemoteException;
	boolean isFMPlaced() throws RemoteException;
	List<Zone> getZones() throws RemoteException;
	Tower getTower(CardType cardType) throws RemoteException;
	SpaceWorkZone getSpaceWorkZone(WorkType workType) throws RemoteException;
	int getPeriodNum() throws RemoteException;
}
