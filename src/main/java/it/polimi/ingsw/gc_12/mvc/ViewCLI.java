package it.polimi.ingsw.gc_12.mvc;

import it.polimi.ingsw.gc_12.action.Action;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.client.ClientSender;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

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
		adapter.sendAction(0);
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

			if(!clientHandler.isMyTurn()) {
				System.out.println("It's not your turn!");
				continue;
			}

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
