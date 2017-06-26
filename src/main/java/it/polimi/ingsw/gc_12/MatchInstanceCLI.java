package it.polimi.ingsw.gc_12;


import it.polimi.ingsw.gc_12.occupiables.TowerSet;

public class MatchInstanceCLI extends MatchInstance {

	private static MatchInstanceCLI instance;

	private MatchInstanceCLI() {}

	public static MatchInstanceCLI instance() {
		if(instance == null) instance = new MatchInstanceCLI();
		return instance;
	}

    @Override
    public void setCards(TowerSet towers) {

    }
}
