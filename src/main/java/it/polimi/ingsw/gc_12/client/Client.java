package it.polimi.ingsw.gc_12.client;

import it.polimi.ingsw.gc_12.client.rmi.ClientRMI;
import it.polimi.ingsw.gc_12.client.socket.ClientSocket;
import it.polimi.ingsw.gc_12.mvc.View;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.util.Scanner;

public abstract class Client {

	protected void start(View view) throws NotBoundException, AlreadyBoundException, CloneNotSupportedException, IOException {
		Scanner in = new Scanner(System.in);
		System.out.println("Choose a name");
		String name = "";
		while (true) {
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
							ClientFactory.setClientSender(clientRMI);
							clientRMI.start(view, name);
						}
						else {
							ClientSocket clientSocket = new ClientSocket();
							clientSocket.startClient(view, name);
						}

					}

				}
			}
		}
	}
}
