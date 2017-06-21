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
