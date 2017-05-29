package it.polimi.ingsw.gc_12;

import it.polimi.ingsw.gc_12.exceptions.NotEnoughResourcesException;

public class TestMatch {

	public static void main(String[] args){
		Match.instance().setup();
		Match.instance().start();
	}

}
