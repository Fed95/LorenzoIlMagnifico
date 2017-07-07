package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.action.*;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.view.client.gui.MainBoard;
import javafx.application.Platform;

import java.util.ArrayList;


public class EventPlacementEnded extends Event implements EventView{

	public EventPlacementEnded(Player player) {
		super(player);
	}

	@Override
	public void setActions(Match match) {
		actions = new ArrayList<>();
		match.getActionHandler().setHasPlaced(true);
		if(player.getPlayableLeaderCards().size() > 0)
			actions.add(new ActionViewPlayableLeaderCards(player));
		if(player.getAvailableLeaderCards().size() > 0)
			actions.add(new ActionViewAvailableLeaderCards(player));
		if(player.getPersonalBoard().getLeaderCardsSpace().getCards().size() > 0)
			actions.add(new ActionViewDiscardableLeaderCards(player));
		actions.add(new ActionPassTurn(player));
		actions.add(new ActionRequestStatistics(player));
	}

	@Override
	public String toString() {
		return "";
	}

	@Override
	public void executeViewSide(MainBoard view) {
		Platform.runLater(() -> view.getControllerMainBoard().disablePassTurn(false, player));
	}
}
