package it.polimi.ingsw.gc12.model.event;

public class EventActionUnavailable extends Event {

	private boolean isFMPlaced;

	public EventActionUnavailable(boolean isFMPlaced) {
		this.isFMPlaced = isFMPlaced;
	}

	public boolean isFMPlaced() {
		return isFMPlaced;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(System.getProperty("line.separator"));
		sb.append("EventActionUnavailable");
		return sb.toString();
	}
}
