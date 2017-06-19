package it.polimi.ingsw.gc_12.client;


import it.polimi.ingsw.gc_12.MatchInstance;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.mvc.View;
import it.polimi.ingsw.gc_12.resource.ResourceType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public abstract class ClientHandler extends UnicastRemoteObject {
	protected MatchInstance match;
	protected View view;
	protected List<Action> actions = new ArrayList<>();

	protected ClientHandler() throws RemoteException {
		super();
	}

	public void handleEvent(Event event) {
		if(event instanceof EventStartMatch) {
			System.out.println("ClientRMI: EventStartMatch recognised. Creating view with local match.");
			EventStartMatch eventStartMatch = (EventStartMatch) event;
			match = eventStartMatch.getMatchInstance();
		}
		if(event.getPlayer() != null && isMyTurn(event.getPlayer())) {
			if (event instanceof EventRequiredValueNotSatisfied) {
				printServantsChoice(event);
			}
			else {
				if(event instanceof EventViewStatistics)
					printStatistics(event);
				if (event instanceof EventPlaceFamilyMember) {
					EventPlaceFamilyMember eventPlaceFamilyMember = (EventPlaceFamilyMember) event;
					match.placeFamilyMember(eventPlaceFamilyMember.getOccupiable(), eventPlaceFamilyMember.getFamilyMember());
				}
				actions = event.getActions();
				if(!(event instanceof EventPickCard)) {
					System.out.println("What would you like to do?");
					System.out.println();
				}
				for (int i = 0; i < actions.size(); i++)
					System.out.println(i + " - " + actions.get(i));
			}
		}
	}

	private void printServantsChoice(Event event) {
		int minValue = ((EventRequiredValueNotSatisfied) event).getOccupiable().getRequiredValue() - ((EventRequiredValueNotSatisfied) event).getFamilyMember().getValue();
		int maxValue = event.getPlayer().getResourceValue(ResourceType.SERVANT);
		System.out.println("You have " + maxValue + " Servants");
		System.out.println("How many would you like to use?	min: " + minValue + ", max: " + maxValue + " - (Press 0 to go back)");
	}

	private void printStatistics(Event event) {
		Player chosenPlayer = event.getPlayer();
		StringBuilder sb = new StringBuilder();
		sb.append(System.getProperty("line.separator"));
		sb.append("Viewing statistics of " + chosenPlayer.getName() + ":").append(System.getProperty("line.separator"));
		sb.append(System.getProperty("line.separator"));

		sb.append("Family Members:").append(System.getProperty("line.separator"));
		sb.append(chosenPlayer.printFamilyMembers()).append(System.getProperty("line.separator"));

		sb.append("Resources:").append(System.getProperty("line.separator"));
		sb.append(chosenPlayer.printResources()).append(System.getProperty("line.separator"));

		sb.append("Excommunications:").append(System.getProperty("line.separator"));
		sb.append(chosenPlayer.getExcommunications()).append(System.getProperty("line.separator"));
		sb.append(System.getProperty("line.separator"));

		sb.append("Cards:").append(System.getProperty("line.separator"));
		sb.append(chosenPlayer.getCards());

		System.out.println(sb.toString());
	}

	public List<Action> getActions() {
		return actions;
	}

	protected abstract boolean isMyTurn(Player player);
}