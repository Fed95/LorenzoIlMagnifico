package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.action.*;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;

import java.util.ArrayList;

/**
 * Generated when the placement has successfully ended
 */
public class EventPlacementEnded extends Event{

	public EventPlacementEnded(Player player) {
		super(player);
	}

	@Override
	public void setActions(Match match) {
		actions = new ArrayList<>();
		match.getActionHandler().setHasPlaced(true);
		if(player.getPersonalBoard().getLeaderCardsSpace().getCards().size() > 0) {
			if (player.getPlayableLeaderCards().size() > 0)
				actions.add(new ActionViewPlayableLeaderCards(player));
			if (player.getAvailableLeaderCards().size() > 0)
				actions.add(new ActionViewAvailableLeaderCards(player));
			actions.add(new ActionViewDiscardableLeaderCards(player));
		}
		actions.add(new ActionPassTurn(player));
		actions.add(new ActionRequestStatistics(player));
	}

	@Override
	public String toString() {
		return "";
	}
}
