package it.polimi.ingsw.gc_12.server.observer;

import java.io.IOException;

public interface Observer<C> {

	public default void update(C o) throws IOException {
		System.out.println("I am the " + this.getClass().getSimpleName() +
				". I have been notified with the "+o.getClass().getSimpleName());
	}

	public void update();
}
