package it.polimi.ingsw.gc_12.JSON;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.occupiables.SpaceWork;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;

public class CardExclusionStrategy implements ExclusionStrategy {

	public boolean shouldSkipField(FieldAttributes f) {
		if(f.getDeclaringClass() != Card.class && f.getDeclaringClass() != EffectProvider.class && f.getName().equals("effects")) {
			Class<?> class1 = f.getDeclaringClass();
			Class<?>[] class3 = f.getDeclaringClass().getClasses();
			Class<?> classe2 = f.getDeclaredClass();
			String name = f.getName();
			int i = 0;
		}

		return ((f.getDeclaringClass() == SpaceWork.class && f.getName().equals("spaceWorkZone")) ||
				(f.getDeclaringClass() == Occupiable.class && f.getName().equals("effects")) ||
				(f.getDeclaringClass() == TowerFloor.class && f.getName().equals("tower")) ||
				(f.getDeclaringClass() == Event.class && f.getName().equals("effectProviders")));
		//return false;
	}

	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}

}