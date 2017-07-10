package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.view.client.gui.MainBoard;

/**
 * This interface is used for all the events that have to interact directly with the view,
 * like opening popup or everything couldn't be represented with the binding of the GUI elements
 * with the elements of the simplified model MatchInstanceGUI
 */
public interface EventView {

	void executeViewSide(MainBoard view);
}
