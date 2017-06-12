package it.polimi.ingsw.gc_12.client;


import it.polimi.ingsw.gc_12.MatchInstance;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.mvc.View;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class ClientHandler extends UnicastRemoteObject {
	protected MatchInstance match;
	protected View view;
	protected Player currentPlayer;

	protected ClientHandler() throws RemoteException {
		super();
	}

	public void handleEvent(Object change) throws IOException {

		if(change instanceof EventStartTurn) {
			System.out.println("Client: EventStartTurn recognised.");
			EventStartTurn event = (EventStartTurn) change;
			match.newTurn();
			currentPlayer = event.getPlayer();

			if(isMyTurn()) {
				view.askAction(false);
			}
		}
		else if(change instanceof EventChooseFamilyMember) {
			System.out.println("Client: EventChooseFamilyMember recognised.");
			currentPlayer = match.getBoard().getTrackTurnOrder().getCurrentPlayer(); // TODO: fix synchronization to be sure that EventStartTurn is always already executed
			EventChooseFamilyMember event = (EventChooseFamilyMember) change;
			if(isMyTurn()) {
				view.askOccupiable(event.getFamilyMember());
			}
		}
		else if(change instanceof EventRequiredValueNotSatisfied) {
			System.out.println("Client: EventRequiredValueNotSatisfied recognised.");
			currentPlayer = match.getBoard().getTrackTurnOrder().getCurrentPlayer();
			EventRequiredValueNotSatisfied event = (EventRequiredValueNotSatisfied) change;
			if(isMyTurn()) {
				view.askServants(event.getOccupiable(), event.getFamilyMember());
			}
		}
		else if(change instanceof EventPlaceFamilyMember) {
			System.out.println("Client: EventPlaceFamilyMember recognised.");
			EventPlaceFamilyMember event = (EventPlaceFamilyMember) change;
			match.placeFamilyMember(event.getOccupiables().get(0), event.getFamilyMember());
			currentPlayer = match.getBoard().getTrackTurnOrder().getCurrentPlayer();
			if(isMyTurn()) {
				view.askAction(true);
			}
		}else if(change instanceof EventFreeAction){
			EventFreeAction event = (EventFreeAction) change;
			if(isMyTurn())
				view.freeAction(event.getOccupiables(), event.getFamilyMember());
		}
	}

	protected abstract boolean isMyTurn();
}
