package it.polimi.ingsw.gc_12.client;


import it.polimi.ingsw.gc_12.MatchInstance;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.PlayerColor;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.mvc.View;
import it.polimi.ingsw.gc_12.resource.ResourceType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class ClientHandler extends UnicastRemoteObject {
	protected MatchInstance match;
	protected View view;
	protected List<Action> actions = new ArrayList<>();
	protected LinkedList<Event> events = new LinkedList<>();
	protected int offset;
	protected int multiplier;
	private boolean myTurn;
	protected PlayerColor color;

	protected ClientHandler() throws RemoteException {
		super();
		this.multiplier = 1;
	}

	public void handleEvent() {
		Event event = events.peekFirst();
		if(event == null)
			return;

		if(event.toStringClient() != null)
			System.out.println(event.toStringClient());
		actions = event.getActions();

		System.out.println("HANDLING "+event.getClass().getSimpleName());

		if(event instanceof EventStartMatch) {
			EventStartMatch eventStartMatch = (EventStartMatch) event;

			match = MatchInstance.instance();
			match.init(eventStartMatch.getMatch());

		}
		else if(event instanceof EventStartTurn) {
			myTurn = (color == event.getPlayer().getColor());
		}
		if(event.getPlayer() != null && myTurn) {
			if (event instanceof EventServantsRequested) {
				actions = event.getActions();
				printServantsChoice((EventServantsRequested) event);
			}
			else {
				if(event instanceof EventViewStatistics)
					printStatistics(event);
				if (event instanceof EventPlaceFamilyMember) {
					EventPlaceFamilyMember eventPlaceFamilyMember = (EventPlaceFamilyMember) event;
					match.placeFamilyMember(eventPlaceFamilyMember.getOccupiable(), eventPlaceFamilyMember.getFamilyMember());
				}
				actions = event.getActions();
				if(event.getActions().size() > 0) {
					System.out.println("What would you like to do?");
					System.out.println();
				}
				for (int i = 0; i < actions.size(); i++)
					System.out.println(i + " - " + actions.get(i));
			}
			if(event.getActions().size() == 0) {
				events.removeFirst();
				handleEvent();
			}
		}
		else
			events.removeFirst();
	}

	private void printServantsChoice(EventServantsRequested event) {
		int multiplier = event.getMultiplier();
		offset = (event.getOccupiable().getRequiredValue() - event.getFamilyMember().getValue()) * multiplier;
		offset = (offset < 0 ? 0 : offset);
		int minValue = (offset*multiplier);
		int maxValue = event.getPlayer().getResourceValue(ResourceType.SERVANT);

		System.out.println("You have " + event.getPlayer().getResourceValue(ResourceType.SERVANT) + " Servants");

		if(multiplier > 1){
			this.multiplier = multiplier;
			double value = 1.0 / (double) multiplier;
			System.out.println("(An effect multiplies the value of your servants by : " + value + ")");
		}
		System.out.println("How many would you like to use?	min: " + offset + ", max: " + maxValue + " - (Press " + (maxValue + 1) + " to go back)");
	}

	private void printStatistics(Event event) {
		Player chosenPlayer = ((EventViewStatistics) event).getChosenPlayer();
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

	public LinkedList<Event> getEvents() {
		return events;
	}

	public void setView(ClientSender client, View view) {
		this.view = view;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getMultiplier() {
		return multiplier;
	}

	public boolean isMyTurn() {
		return myTurn;
	}
}