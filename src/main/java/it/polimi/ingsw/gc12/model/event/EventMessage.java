package it.polimi.ingsw.gc12.model.event;

public class EventMessage extends Event {

	private String message;

	public EventMessage(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public String toStringClient() {
		return message;
	}

	@Override
	public String toStringClientGUI() {
		return message;
	}
}
