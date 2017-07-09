package it.polimi.ingsw.gc12.view.client.cli;


import it.polimi.ingsw.gc12.model.board.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc12.model.board.occupiable.Occupiable;
import it.polimi.ingsw.gc12.model.board.occupiable.TowerSet;
import it.polimi.ingsw.gc12.model.card.CardDevelopment;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.view.client.MatchInstance;

import java.util.List;

public class MatchInstanceCLI extends MatchInstance {

	private static MatchInstanceCLI instance;

	private MatchInstanceCLI() {}

	public static MatchInstanceCLI instance() {
		if(instance == null) instance = new MatchInstanceCLI();
		return instance;
	}

	@Override
	public void init(Match match) {
		super.init(match);
		setFamilyMemberObservers();
		initialized = true;
	}

	@Override
    public void setCards(TowerSet towers) {

    }

	@Override
	protected void setFamilyMemberObservers() {

	}

	@Override
	public void pickCard(CardDevelopment card, PlayerColor playerColor) {}

	@Override
	public void placeFamilyMember(FamilyMember familyMember, Occupiable occupiable, PlayerColor playerColor) {}

	@Override
	public void resetFamilyMembers() {}

	@Override
	public void resetFloors() {}

	@Override
	public void updateResources(List<Player> players) {}

	@Override
	public void playerExcommunication(Player player, ExcommunicationTile tile) {}

	@Override
	public void activateLeaderCard(PlayerColor playerColor, int id) {}

	@Override
	public void discardLeaderCard(PlayerColor playerColor, int id) {}
}
