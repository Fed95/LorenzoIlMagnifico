package it.polimi.ingsw.gc_12.mvc;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.resource.ResourceExchange;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public interface View {

	void startTurn();
	void askAction(boolean isFMPlaced) throws IOException;
	void askOccupiable(FamilyMember familyMember) throws IOException;
	void askServants(Occupiable occupiable, FamilyMember familyMember) throws IOException;
	/*boolean supportChurch();
	void excommunicationMessage();

    int askServants(int requiredServants);

	void viewStatistics();

	void askFreeAction(CardType type, int value);

    int chooseResourceExchange(List<ResourceExchange> exchanges);*/
}
