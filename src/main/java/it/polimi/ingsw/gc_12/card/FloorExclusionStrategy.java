package it.polimi.ingsw.gc_12.card;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import it.polimi.ingsw.gc_12.EffectProvider;
import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.SpaceWork;
import it.polimi.ingsw.gc_12.TowerFloor;
import it.polimi.ingsw.gc_12.event.Event;

public class FloorExclusionStrategy implements ExclusionStrategy {

	public boolean shouldSkipField(FieldAttributes f) {
		if(f.getDeclaringClass() != Card.class && f.getDeclaringClass() != EffectProvider.class && f.getName().equals("effects")) {
			Class<?> class1 = f.getDeclaringClass();
			Class<?>[] class3 = f.getDeclaringClass().getClasses();
			Class<?> classe2 = f.getDeclaredClass();
			String name = f.getName();
			int i = 0;
		}

		return ((f.getDeclaringClass() == SpaceWork.class && f.getName().equals("spaceWorkZone")) ||
				(f.getDeclaringClass() == TowerFloor.class && f.getName().equals("tower")) ||
				(f.getDeclaringClass() == Event.class && f.getName().equals("effectProviders")));
		//return false;
	}

	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}

}