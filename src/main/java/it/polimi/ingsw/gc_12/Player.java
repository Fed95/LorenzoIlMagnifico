package it.polimi.ingsw.gc_12;

public class Player {
	private String name;
	private Match match;
	public Player(String name,Match match){
		this.name=name;
		this.match=match;
	}
	public void rollDice(){
		match.getBoard().getSpaceDie().rollDice();
	}
}
