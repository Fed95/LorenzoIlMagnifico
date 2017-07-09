package it.polimi.ingsw.gc12.model.event;


import it.polimi.ingsw.gc12.model.action.*;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.view.client.ClientHandler;

import java.util.ArrayList;
import java.util.Collections;

public class EventPlayLeaderCard extends Event {

	private int cardId;

	public EventPlayLeaderCard(Player player, int cardId) {
		super(player);
		this.cardId = cardId;
	}

	@Override
	public void executeClientSide(ClientHandler client) {
		super.executeClientSide(client);
		client.getMatch().updateResources(new ArrayList<>(Collections.singletonList(player)));
		client.getMatch().activateLeaderCard(player.getColor(), cardId);
	}

	@Override
	public void setActions(Match match) {
		boolean hasPlaced = match.getActionHandler().hasPlaced();
		actions = new ArrayList<>();
		if(!hasPlaced) {
			for (FamilyMember familyMember : player.getAvailableFamilyMembers()) {
				actions.add(new ActionChooseFamilyMember(player, familyMember));
			}
		}
		if(player.getPersonalBoard().getLeaderCardsSpace().getCards().size() > 0) {
			if (player.getPlayableLeaderCards().size() > 0)
				actions.add(new ActionViewPlayableLeaderCards(player));
			if (player.getAvailableLeaderCards().size() > 0)
				actions.add(new ActionViewAvailableLeaderCards(player));
			actions.add(new ActionViewDiscardableLeaderCards(player));
		}
		if(hasPlaced)
			actions.add(new ActionPassTurn(player));
		actions.add(new ActionRequestStatistics(player));
	}

	@Override
	public String toString() {
		return null;
	}
}
