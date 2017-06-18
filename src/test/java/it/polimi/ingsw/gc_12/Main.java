package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.json.saver.SaverCard;

public class Main {

	public static void main(String[] args) {
		Match match = new Match();
		SaverCard saverCard = new SaverCard(match);
		saverCard.create();
	}

}
