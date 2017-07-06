package it.polimi.ingsw.gc12.model.match;

public class ConfigPlayers {

	private int playersNum;
	private int spaceMarketNum;
	private boolean spaceWorkMultiple;
	public static final int PERIODS_NUM = 3;
	public static final int ROUNDS_NUM = 2;


	public ConfigPlayers(int playersNum) {
		this.playersNum = playersNum;
		if(playersNum == 4)
			setSpaceWorkMultiple(true);
	}

	public void setSpaceMarketNum(int spaceMarketNum) {
		this.spaceMarketNum = spaceMarketNum;
	}

	public void setSpaceWorkMultiple(boolean spaceWorkMultiple) {
		this.spaceWorkMultiple = spaceWorkMultiple;
	}

	public int getSpaceMarketNum() {
		return spaceMarketNum;
	}

	public boolean isSpaceWorkMultiple() {
		return spaceWorkMultiple;
	}


}
