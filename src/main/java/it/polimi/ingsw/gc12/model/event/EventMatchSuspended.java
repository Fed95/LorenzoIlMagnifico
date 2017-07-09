package it.polimi.ingsw.gc12.model.event;

public class EventMatchSuspended extends Event {


	@Override
	public String toString() {
		return null;
	}

	@Override
	public String toStringClient() {
		return "The match has been suspended for lack of players";
	}

	@Override
	public String toStringClientGUI() {
		return "The match has been suspended for lack of players";
	}
}
