package it.polimi.ingsw.gc_12.network;

import java.io.Serializable;
import java.net.Socket;

/** TODO: make it perfectly final **/
public final class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char symbol;
	private String name;
	private Socket socket;

	public Player(String name/*, char symbol*/) {
		this.name = name;
		//this.symbol = symbol;
	}
	
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public char getSymbol() {
		return symbol;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public Socket getSocket(Socket socket) {
		return this.socket;
	}
	
	
}
