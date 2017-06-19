package it.polimi.ingsw.gc_12.mvc;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;

import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.action.ActionFactory;
import it.polimi.ingsw.gc_12.action.ActionPlace;
import it.polimi.ingsw.gc_12.action.ActionPlaceOnTower;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.ClientSender;
import it.polimi.ingsw.gc_12.client.rmi.ClientRMI;
import it.polimi.ingsw.gc_12.client.rmi.ClientRMIView;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.resource.ResourceType;
import it.polimi.ingsw.gc_12.resource.Servant;
import it.polimi.ingsw.gc_12.server.controller.Change;

public class ViewCLI extends Observable implements View {

	private Scanner in;
	private CLIAdapter adapter;
	private ClientHandler clientHandler;

	public ViewCLI(ClientSender client, ClientHandler clientHandler) {
		this.adapter = new CLIAdapter(this, client);
		this.in = new Scanner(System.in);
		this.clientHandler = clientHandler;
	}

	public void start() throws IOException {
		while (true) {
			//Capture input from user
			int inputInt = in.nextInt();
			System.out.println("SENDING " + inputInt);
			List<Action> actions = clientHandler.getActions();

			if(inputInt >= actions.size()) {
				System.out.println("The inserted number is not among the possible choices");
			}
			else {
				try {
					adapter.sendAction(inputInt);
					clientHandler.getEvents().removeFirst();
					clientHandler.handleEvent();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
