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

	}
}
