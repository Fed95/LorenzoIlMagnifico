package it.polimi.ingsw.gc12.misc.observer;


public interface Observer<C> {

	default void update(C o) {
	}

	void update();
}
