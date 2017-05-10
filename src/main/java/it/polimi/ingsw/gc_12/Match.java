package it.polimi.ingsw.gc_12;

public class Match {
	private int playerNum;
	private int roundNum;
	private int periodNum;
	private GameMode gameMode; 
	private Board board;
	public Match(int playerNum,GameMode gameMode){
		this.playerNum=playerNum;
		this.roundNum=1;
		this.periodNum=1;
		this.gameMode=gameMode;
	}
	public int getPlayersNum(){
		return playerNum;
	}
	
	public void start(){
		//setta le risorse base distriuendo monete servitori legna pietra-->creo changeresources
		
	}
	private void givingInitialResource(){
		
	}
	public GameMode getGameMode(){
		return gameMode;
	}
	public Board getBoard(){
		return board;
	}
	public int getPlayerNum(){
		return playerNum; 
	}
	public int getRoundNum(){
		return roundNum;
	}
	public int getPeriodNum(){
		return periodNum;
	}
	
}
