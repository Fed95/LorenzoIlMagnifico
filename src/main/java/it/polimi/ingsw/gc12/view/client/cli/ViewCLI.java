package it.polimi.ingsw.gc12.view.client.cli;

import it.polimi.ingsw.gc12.model.action.Action;
import it.polimi.ingsw.gc12.model.event.*;
import it.polimi.ingsw.gc12.view.client.ClientFactory;
import it.polimi.ingsw.gc12.view.client.ClientHandler;
import it.polimi.ingsw.gc12.view.client.ClientView;
import it.polimi.ingsw.gc12.view.client.ClientViewType;
import it.polimi.ingsw.gc12.view.client.rmi.ClientRMI;
import it.polimi.ingsw.gc12.view.client.socket.ClientSocket;
import it.polimi.ingsw.gc12.model.player.resource.CouncilPrivilege;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Scanner;

/**
 * View for the CLI. Sends information to the ClientSender (RMI or socket) based on the input of the player
 * using the observer pattern.
 */
public class ViewCLI extends Observable implements ClientView {

	private Scanner in;
	private ClientHandler clientHandler;
	private boolean ready;
	private final ClientViewType type = ClientViewType.CLI;

	public ViewCLI() {
		this.in = new Scanner(System.in);
	}

	/**
	 * The main method of the view. It loops waiting for the player to insert a numeric input,
	 * usually to choose the index of the action he/she wants to perform.
	 * The user can send input at any time. The server is responsible of discarding the input if it's not valid.
	 *
	 * @throws IOException
	 * @throws CloneNotSupportedException
	 * @throws NotBoundException
	 * @throws AlreadyBoundException
	 */

	public void start() throws IOException, CloneNotSupportedException, NotBoundException, AlreadyBoundException {
		setup();

		ready = true;

		// If the match has been initialized, sends the notification to the server that the client is ready
		if(clientHandler.isStarted()) {
			setChanged();
			notifyObservers(0);
		}

		while(in.hasNext()) {
			//Capture input from user
			String inputLine = in.nextLine();

			if(!checkExclusion() ||  !checkAuthorization(inputLine) || !checkTurn())
				continue;

			int inputInt;
			try {
				inputInt = Integer.parseInt(inputLine);
			}
			catch (NumberFormatException e) {
				System.out.println("You can only insert numbers!");
				continue;
			}

			List<Action> actions = clientHandler.getActions();
			int offset = clientHandler.getOffset();

			// The player can choose only among the actions save in the clientHandler and received by the server
			if(inputInt < offset || inputInt >= offset + actions.size()) {
				System.out.println("The inserted number is not among the possible choices");
			}
			else {
				try {
					setChanged();
					notifyObservers(inputInt-offset);
					clientHandler.setOffset(0);
					Event event = clientHandler.getEvents().getFirst();
					if(event instanceof EventCouncilPrivilegeReceived)
						handleCouncilPrivileges(inputInt, actions, (EventCouncilPrivilegeReceived) event);
					else
						clientHandler.getEvents().removeFirst();
					clientHandler.handleEvent();
				} catch (NoSuchElementException ignored){}
			}
		}
	}

	/**
	 * Performs the choice of the name by the player and the choice of the communication technology
	 * and starts the right client based on the parameters written by the player.
	 *
	 * @throws NotBoundException
	 * @throws AlreadyBoundException
	 * @throws CloneNotSupportedException
	 * @throws IOException
	 */
	private void setup() throws NotBoundException, AlreadyBoundException, CloneNotSupportedException, IOException {
		System.out.println("Choose a name");
		String name = "";
		loop: while (true) {
			name = in.nextLine();
			name = name.replaceAll("\\s+",""); // Remove whitespaces and invisible characters
			if (!"\n".equals(name) && !"".equals(name)) {
				System.out.println("Choose the technology");
				System.out.println("0 - RMI");
				System.out.println("1 - Socket");
				while(in.hasNext()) {
					//Capture input from user
					String inputLine = in.nextLine();

					int inputInt;
					try {
						inputInt = Integer.parseInt(inputLine);
					}
					catch (NumberFormatException e) {
						System.out.println("You can only insert numbers!");
						continue;
					}

					if(inputInt < 0 || inputInt > 1) {
						System.out.println("The inserted number is not among the possible choices");
					}
					else {
						if(inputInt == 0) {
							ClientRMI clientRMI = new ClientRMI();
							clientRMI.start(this, name);
						}
						else {
							ClientSocket clientSocket = new ClientSocket();
							clientSocket.startClient(this, name);
						}
						break loop;
					}
				}
			}
		}

		this.addObserver(ClientFactory.getClientSender());
		clientHandler = ClientFactory.getClientHandler();
	}

	/**
	 * If the player has been excluded for the action timeout's expiration,
	 * as soon he/she writes any input, a notification to the server is sent.
	 * In this way the server can understand when a player is back from the inactivity.
	 */
	private boolean checkExclusion() {
		if(clientHandler.isExcluded()) {
			clientHandler.setExcluded(false);
			System.out.println("Welcome back! You can start playing again.");
			setChanged();
			notifyObservers(clientHandler.getColor());
			return false;
		}
		return true;
	}

	/**
	 * If the player is not authorized yet (the name it chose was already taken by another user),
	 * if he/she writes an input, it is interpreted as the new name chosen by the player
	 *
	 * @param inputLine The name chosen by the player to log in
	 * @throws IOException
	 */
	private boolean checkAuthorization(String inputLine) throws IOException {
		if(!clientHandler.isAuthorized()) {
			setChanged();
			notifyObservers(new EventNewName(clientHandler.getUnauthorizedId(), inputLine));
			return false;
		}
		return true;
	}

	/**
	 * The player cannot write inputs if it's not his/her turn
	 */

	private boolean checkTurn() {
		if(!clientHandler.isMyTurn()) {
			System.out.println("It's not your turn!");
			return false;
		}
		return true;
	}

	/**
	 * Shows the resources to choose from to exchange a council privilege.
	 * Show the list of resources as many times as the council privilege value (i.e. the number of privileges received)
	 * Every time a resource is chosen from the list, the next time it will not appear again.
	 * This is because you cannot exchange two council privileges obtained at the same time with the same resources
	 * @param inputInt The index of the resources to exchange
	 * @param actions The action representing the exchange of resources the player can perform.
	 * @param event The event related to receiving the council privilege
	 */
	private void handleCouncilPrivileges(int inputInt, List<Action> actions, EventCouncilPrivilegeReceived event) {
		CouncilPrivilege councilPrivilege = event.getCouncilPrivilege();
		if(councilPrivilege.getValue() > 1) {
			councilPrivilege.setValue(councilPrivilege.getValue()-1);
			actions.remove(inputInt);
		}
		else
			clientHandler.getEvents().removeFirst();
	}

	@Override
	public boolean isReady() {
		return ready;
	}

	@Override
	public void update(EventView event) {
		if(event instanceof EventMatchInitialized) {
			if(ready) {
				setChanged();
				notifyObservers(0);
			}
			else
				clientHandler.setStarted(true);
		}
	}

	@Override
	public void update() {

	}

	@Override
	public ClientViewType getType() {
		return type;
	}

}
