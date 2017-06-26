package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.resource.Resource;

import java.util.List;

public class ConfigPlayers {

	private int playersNum;
	private int spaceMarketNum;
	private boolean spaceWorkMultiple;
	public static final int PERIODS_NUM = 3;
	public static final int ROUNDS_NUM = 2;


	public ConfigPlayers(int playersNum) {
		this.playersNum = playersNum;
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
