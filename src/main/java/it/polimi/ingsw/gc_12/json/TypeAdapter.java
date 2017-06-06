package it.polimi.ingsw.gc_12.json;

import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.gc_12.card.*;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectChangeFamilyMemberValue;
import it.polimi.ingsw.gc_12.effect.EffectChangeResource;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.resource.*;
import it.polimi.ingsw.gc_12.occupiables.*;

/**
 * Created by marco on 27/05/2017.
 */
public class TypeAdapter {

	private TypeAdapter() {}

	public static GsonBuilder create() {
		final RuntimeTypeAdapterFactory<EffectProvider> factoryEffectProvider = RuntimeTypeAdapterFactory
				.of(EffectProvider.class, "effectProvider")
				.registerSubtype(Card.class, Card.class.getSimpleName())
				.registerSubtype(Occupiable.class, Occupiable.class.getSimpleName());

		final RuntimeTypeAdapterFactory<Occupiable> factoryOccupiable = RuntimeTypeAdapterFactory
				.of(Occupiable.class, "occupiable")
				.registerSubtype(SpaceMarket.class, SpaceMarket.class.getSimpleName())
				.registerSubtype(TowerFloor.class, TowerFloor.class.getSimpleName())
				.registerSubtype(SpaceWorkMultiple.class, SpaceWorkMultiple.class.getSimpleName())
				.registerSubtype(SpaceWorkSingle.class, SpaceWorkSingle.class.getSimpleName());//council palace

		final RuntimeTypeAdapterFactory<Card> factoryCard = RuntimeTypeAdapterFactory
				.of(Card.class, "type")
				.registerSubtype(CardBuilding.class, CardBuilding.class.getSimpleName())
				.registerSubtype(CardCharacter.class, CardCharacter.class.getSimpleName())
				.registerSubtype(CardTerritory.class, CardTerritory.class.getSimpleName())
				.registerSubtype(CardVenture.class, CardVenture.class.getSimpleName());
		//registerSubtype(CardLeader.class, "LEADER");

		final RuntimeTypeAdapterFactory<Resource> factoryResource = RuntimeTypeAdapterFactory
				.of(Resource.class, "resourceType")
				.registerSubtype(CouncilPrivilege.class, CouncilPrivilege.class.getSimpleName())
				.registerSubtype(FaithPoint.class, FaithPoint.class.getSimpleName())
				.registerSubtype(MilitaryPoint.class, MilitaryPoint.class.getSimpleName())
				.registerSubtype(Money.class, Money.class.getSimpleName())
				.registerSubtype(Servant.class, Servant.class.getSimpleName())
				.registerSubtype(Stone.class, Stone.class.getSimpleName())
				.registerSubtype(VictoryPoint.class, VictoryPoint.class.getSimpleName())
				.registerSubtype(Wood.class, Wood.class.getSimpleName());

		final RuntimeTypeAdapterFactory<Effect> factoryEffect = RuntimeTypeAdapterFactory
				.of(Effect.class, "type") // Here you specify which is the parent class and what field particularizes the child class.
				.registerSubtype(EffectChangeResource.class, EffectChangeResource.class.getSimpleName())
				.registerSubtype(EffectChangeFamilyMemberValue.class, EffectChangeFamilyMemberValue.class.getSimpleName()); // if the flag equals the class name, you can skip the second parameter. This is only necessary, when the "type" field does not equal the class name.

		final RuntimeTypeAdapterFactory<Event> factoryEvent = RuntimeTypeAdapterFactory
				.of(Event.class, "eventType")
				.registerSubtype(EventPlaceFamilyMember.class, EventPlaceFamilyMember.class.getSimpleName());

		return new GsonBuilder()
				.registerTypeAdapterFactory(factoryEffectProvider)
				.registerTypeAdapterFactory(factoryOccupiable)
				.registerTypeAdapterFactory(factoryCard)
				.registerTypeAdapterFactory(factoryResource)
				.registerTypeAdapterFactory(factoryEffect)
				.registerTypeAdapterFactory(factoryEvent);
	}

}
