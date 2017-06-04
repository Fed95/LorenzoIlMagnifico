package it.polimi.ingsw.gc_12.mvc;

import it.polimi.ingsw.gc_12.card.CardType;

public interface View {

	void startTurn();
	void askAction();
	void askOccupiable();
	boolean supportChurch();
	void excommunicationMessage();

    int askServants(int requiredServants);

	void viewStatistics();

	void askFreeAction(CardType type, int value);
}
