package it.polimi.ingsw.gc_12.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.occupiables.SpaceWork;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;

public class OccupiableExclusionStrategy implements ExclusionStrategy {

	public boolean shouldSkipField(FieldAttributes f) {
		return ((f.getDeclaringClass() == SpaceWork.class && f.getName().equals("spaceWorkZone")) ||
				(f.getDeclaringClass() == TowerFloor.class && f.getName().equals("tower")) ||
				(f.getDeclaringClass() == Event.class && f.getName().equals("effectProviders")));
		//return false;
	}

	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}

}
