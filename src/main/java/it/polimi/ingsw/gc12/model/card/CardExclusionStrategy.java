package it.polimi.ingsw.gc12.model.card;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import it.polimi.ingsw.gc12.model.effect.EffectProvider;
import it.polimi.ingsw.gc12.model.event.Event;
import it.polimi.ingsw.gc12.model.board.occupiable.Occupiable;
import it.polimi.ingsw.gc12.model.board.occupiable.SpaceWork;
import it.polimi.ingsw.gc12.model.board.occupiable.TowerFloor;

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