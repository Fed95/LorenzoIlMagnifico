package it.polimi.ingsw.gc_12;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.gc_12.JSON.AskCards;
import it.polimi.ingsw.gc_12.JSON.LoaderCard;


import it.polimi.ingsw.gc_12.JSON.LoaderTowerSet;
import it.polimi.ingsw.gc_12.card.Card;

public class Main {

	public static void main(String[] args) {
		/*List<Card> cards= new ArrayList<>();
		LoaderCard jsonobj= new LoaderCard("card");
		AskCards askCards=new AskCards();
		cards=askCards.buildCards();
		jsonobj.createCards(cards);
		cards=jsonobj.getCards();*/

		LoaderTowerSet loaderTowerSet = new LoaderTowerSet("towerSet");
		loaderTowerSet.create();
	}

}
