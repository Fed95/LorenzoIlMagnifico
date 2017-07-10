package it.polimi.ingsw.gc12.model.board.occupiable;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all the markets
 */
public class Market implements Zone, Serializable{
	
	private List<SpaceMarket> spaceMarkets = new ArrayList<>();
	public static final int DEFAULT_SET_NUM = 4;

	public List<SpaceMarket> getSpaceMarkets() {
		return spaceMarkets;
	}

    /**
     * set a market
     * @param spaceMarkets
     */
	public void setSpaceMarkets(List<SpaceMarket> spaceMarkets) {
		this.spaceMarkets = spaceMarkets;
	}

	@Override
	public boolean canBeOccupiedBy(FamilyMember familyMember) {
		return true;
	}

	@Override
	public List<Occupiable> getOccupiables() {
		List<Occupiable> occupiables = new ArrayList<>();
		occupiables.addAll(spaceMarkets);
		return occupiables;
	}
}
