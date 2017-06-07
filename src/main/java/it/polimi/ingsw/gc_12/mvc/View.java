package it.polimi.ingsw.gc_12.mvc;

import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;

import java.rmi.RemoteException;
import java.util.List;

public interface View {

	void startTurn();
	void askAction(boolean isFMPlaced) throws RemoteException;
	/*void askOccupiable();
	boolean supportChurch();
	void excommunicationMessage();

    int askServants(int requiredServants);

	void viewStatistics();

	void askFreeAction(CardType type, int value);

    int chooseResourceExchange(List<ResourceExchange> exchanges);*/
}
