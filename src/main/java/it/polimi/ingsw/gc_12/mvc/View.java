package it.polimi.ingsw.gc_12.mvc;

public interface View {

	void startTurn();
	void askAction(boolean isFMPlaced);
	void askOccupiable();
	boolean supportChurch();
	void excommunicationMessage();

    int askServants(int requiredServants);
}
