package it.polimi.ingsw.gc12.model.match;


import it.polimi.ingsw.gc12.model.board.occupiable.TowerSet;

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
}
