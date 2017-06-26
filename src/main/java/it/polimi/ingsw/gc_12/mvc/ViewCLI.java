package it.polimi.ingsw.gc_12.mvc;

import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.ClientSender;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventCouncilPrivilegeReceived;
import it.polimi.ingsw.gc_12.resource.CouncilPrivilege;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

public class ViewCLI extends Observable implements View{

	private Scanner in;
	private ClientSender clientSender;
	private ClientHandler clientHandler;

	public ViewCLI(ClientSender clientSender, ClientHandler clientHandler) {
		this.in = new Scanner(System.in);
		this.clientSender = clientSender;
		this.clientHandler = clientHandler;
	}

	public void start() throws IOException {
		clientSender.sendAction(0);
		while(in.hasNext()) {
			//Capture input from user
			String inputLine = in.nextLine();
			if(clientHandler.isExcluded()) {
				clientHandler.setExcluded(false);
				System.out.println("Welcome back! You can start playing again.");
			}

			if(!clientHandler.isMyTurn()) {
				System.out.println("It's not your turn!");
				continue;
			}

			int inputInt;
			try {
				inputInt = Integer.parseInt(inputLine);
			}
			catch (NumberFormatException e) {
				System.out.println("You can only insert numbers!");
				continue;
			}

			System.out.println("SENDING " + inputInt);
			List<Action> actions = clientHandler.getActions();
			int offset = clientHandler.getOffset();

			if(inputInt < offset || inputInt >= actions.size()) {
				System.out.println("The inserted number is not among the possible choices");
			}
			else {
				try {
					clientSender.sendAction(inputInt);
					clientHandler.setOffset(0);
					Event event = clientHandler.getEvents().getFirst();
					if(event instanceof EventCouncilPrivilegeReceived) {
						EventCouncilPrivilegeReceived eventCP = (EventCouncilPrivilegeReceived) event;
						CouncilPrivilege councilPrivilege = eventCP.getCouncilPrivilege();
						if(councilPrivilege.getValue() > 1) {
							councilPrivilege.setValue(councilPrivilege.getValue()-1);
							actions.remove(inputInt);
						}
						else
							clientHandler.getEvents().removeFirst();
					}
					else
						clientHandler.getEvents().removeFirst();
					clientHandler.handleEvent();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	@Override
	public ClientSender getClientSender() {
		return clientSender;
	}
}
