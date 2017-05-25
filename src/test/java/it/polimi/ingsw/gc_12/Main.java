package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.JSON.AskCards;
import it.polimi.ingsw.gc_12.JSON.JsonCard;


import it.polimi.ingsw.gc_12.card.Card;

public class Main {

	public static void main(String[] args) {
		List<Card> cards= new ArrayList<>();		
		//Card card1= new Card(1,"ciao",new ArrayList<>(),new ArrayList<>());
		//Card card2= new Card(22,"ciao",new ArrayList<>(),new ArrayList<>());
		//cards.add(card1);
		//cards.add(card2);
		JsonCard jsonobj= new JsonCard("card");
		//jsonobj.createCards(cards);
		//cards=jsonobj.getCards();
		
		AskCards askCards=new AskCards();
		cards=askCards.buildCards();
		jsonobj.createCards(cards);
		cards=jsonobj.getCards();	
	}

}
