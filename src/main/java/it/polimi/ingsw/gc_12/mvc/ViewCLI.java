package it.polimi.ingsw.gc_12.mvc;

import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.client.ClientFactory;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.ClientViewType;
import it.polimi.ingsw.gc_12.event.*;
import it.polimi.ingsw.gc_12.client.rmi.ClientRMI;
import it.polimi.ingsw.gc_12.client.socket.ClientSocket;
import it.polimi.ingsw.gc_12.resource.CouncilPrivilege;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Scanner;

public class ViewCLI extends Observable implements ClientView {

	private Scanner in;
	private ClientHandler clientHandler;
	private boolean ready;
	private final ClientViewType type = ClientViewType.CLI;

	public ViewCLI() {
		this.in = new Scanner(System.in);
	}

	public void start() throws IOException, CloneNotSupportedException, NotBoundException, AlreadyBoundException {
		setup();

		ready = true;
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

			if(inputInt < offset || inputInt >= actions.size()) {
				System.out.println("The inserted number is not among the possible choices");
			}
			else {
				try {
					setChanged();
					notifyObservers(inputInt);
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

	private void setup() throws NotBoundException, AlreadyBoundException, CloneNotSupportedException, IOException {
		System.out.println("Choose a name");
		String name = "";
		loop: while (true) {
			name = in.nextLine();
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

	private boolean checkExclusion() {
		if(clientHandler.isExcluded()) {
			clientHandler.setExcluded(false);
			System.out.println("Welcome back! You can start playing again.");
			return false;
		}
		return true;
	}

	private boolean checkAuthorization(String inputLine) throws IOException {
		if(!clientHandler.isAuthorized()) {
			setChanged();
			notifyObservers(new EventNewName(clientHandler.getUnauthorizedId(), inputLine));
			return false;
		}
		return true;
	}

	private boolean checkTurn() {
		if(!clientHandler.isMyTurn()) {
			System.out.println("It's not your turn!");
			return false;
		}
		return true;
	}

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
	public void update(Event event) {
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
