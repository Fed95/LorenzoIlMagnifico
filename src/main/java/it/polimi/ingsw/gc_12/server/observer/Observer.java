package it.polimi.ingsw.gc_12.server.observer;


public interface Observer<C> {

	default void update(C o) {
	}

	void update();
}
