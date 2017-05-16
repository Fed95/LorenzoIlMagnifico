package it.polimi.ingsw.gc_12;

public class Board {

	public static final int DEFAULT_SPACE_MARKETS_NUM = 4;
	private SpaceDie spaceDie;
	private TowerSet towerSet;
	private int actionSpaceNum;
	private Market market;
	/*
	private TurnOrderTrack turnOrderTrack;
	private CouncilPalace councilPalace;
	private SpaceAction spaceAction;
	private ExcommunicationSpace excommunicationSpace;
	private FaithPointsTrack faithPointTrack;
	*/
	
	public Board() {
		this.spaceDie = SpaceDie.instance();
		this.towerSet = new TowerSet();
		this.market = new Market(DEFAULT_SPACE_MARKETS_NUM);
	}
	
	public int getActionSpaceNum() {
		return actionSpaceNum;
	}
	public SpaceDie getSpaceDie() {
		return spaceDie;
	}
	public TowerSet getTowerSet() {
		return towerSet;
	}
	public Market getMarket() {
		return market;
	}
	/*
	public TurnOrderTrack getTurnOrderTrack() {
		return turnOrderTrack;
	}
	public CouncilPalace getCouncilPalace() {
		return councilPalace;
	}
	public SpaceAction getSpaceAction() {
		return spaceAction;
	}
	public ExcommunicationSpace getExcommunicationSpace() {
		return excommunicationSpace;
	}
	public FaithPointsTrack getFaithPointTrack() {
		return faithPointTrack;
	}*/

	@Override
	public String toString() {
		return "Board [towerSet=" + towerSet + "]";
	}
}
