package it.polimi.ingsw.gc_12.network;

import java.io.Serializable;

public final class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String command;
	private final Object parameter;
	
	public Message(String command) {
		this(command, null);
	}
	
	public Message(String command, Object parameter) {
		this.command = command;
		this.parameter = parameter;
	}

	public String getCommand() {
		return command;
	}

	public Object getParameter() {
		return parameter;
	}
}
