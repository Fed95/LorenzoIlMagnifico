package it.polimi.ingsw.gc12.misc.json;

import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.gc12.model.board.occupiable.*;
import it.polimi.ingsw.gc12.model.card.*;
import it.polimi.ingsw.gc12.model.effect.*;
import it.polimi.ingsw.gc12.model.event.*;
import it.polimi.ingsw.gc12.model.player.resource.*;

public class TypeAdapter {

	private TypeAdapter() {}

	public static GsonBuilder create() {
		final RuntimeTypeAdapterFactory<EffectProvider> factoryEffectProvider = RuntimeTypeAdapterFactory
				.of(EffectProvider.class, "effectProvider")
				.registerSubtype(Card.class, Card.class.getSimpleName())
				.registerSubtype(Occupiable.class, Occupiable.class.getSimpleName())
				.registerSubtype(CardTerritory.class, CardTerritory.class.getSimpleName())
				.registerSubtype(CardBuilding.class, CardBuilder.class.getSimpleName())
				.registerSubtype(CardCharacter.class, CardCharacter.class.getSimpleName())
				.registerSubtype(CardVenture.class, CardVenture.class.getSimpleName())
				.registerSubtype(TowerFloor.class, TowerFloor.class.getSimpleName());


		final RuntimeTypeAdapterFactory<Occupiable> factoryOccupiable = RuntimeTypeAdapterFactory
				.of(Occupiable.class, "occupiable")
				.registerSubtype(SpaceMarket.class, SpaceMarket.class.getSimpleName())
				.registerSubtype(TowerFloor.class, TowerFloor.class.getSimpleName())
				.registerSubtype(SpaceWorkMultiple.class, SpaceWorkMultiple.class.getSimpleName())
				.registerSubtype(SpaceWorkSingle.class, SpaceWorkSingle.class.getSimpleName())
				.registerSubtype(CouncilPalace.class, CouncilPalace.class.getSimpleName());

		final RuntimeTypeAdapterFactory<Card> factoryCard = RuntimeTypeAdapterFactory
				.of(Card.class, "type")
				.registerSubtype(CardBuilding.class, CardBuilding.class.getSimpleName())
				.registerSubtype(CardCharacter.class, CardCharacter.class.getSimpleName())
				.registerSubtype(CardTerritory.class, CardTerritory.class.getSimpleName())
				.registerSubtype(CardVenture.class, CardVenture.class.getSimpleName())
				.registerSubtype(LeaderCard.class, LeaderCard.class.getSimpleName());

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
				.registerSubtype(EffectChangeFamilyMemberValue.class, EffectChangeFamilyMemberValue.class.getSimpleName())
				.registerSubtype(EffectFreeAction.class, EffectFreeAction.class.getSimpleName())
				.registerSubtype(EffectResourceForCards.class, EffectResourceForCards.class.getSimpleName())
				.registerSubtype(EffectResourceForResource.class, EffectResourceForResource.class.getSimpleName())
				.registerSubtype(EffectReduceReceivedResource.class, EffectReduceReceivedResource.class.getSimpleName())
				.registerSubtype(EffectServantsMultiplier.class, EffectServantsMultiplier.class.getSimpleName())
				.registerSubtype(EffectDenyExchange.class, EffectDenyExchange.class.getSimpleName())
				.registerSubtype(EffectCardDiscount.class, EffectCardDiscount.class.getSimpleName())
				.registerSubtype(EffectMultiplyCardResourceBonus.class, EffectMultiplyCardResourceBonus.class.getSimpleName())
				.registerSubtype(EffectSetFamilyMemberValue.class, EffectSetFamilyMemberValue.class.getSimpleName())
				.registerSubtype(EffectFriendlyFamilyMembers.class, EffectFriendlyFamilyMembers.class.getSimpleName())
				.registerSubtype(EffectNoRequisites.class, EffectNoRequisites.class.getSimpleName())
				.registerSubtype(EffectCopyLeaderCard.class, EffectCopyLeaderCard.class.getSimpleName())
				.registerSubtype(EffectPlacementDenied.class, EffectPlacementDenied.class.getSimpleName());	// if the flag equals the class name, you can skip the second parameter. This is only necessary, when the "type" field does not equal the class name.

		final RuntimeTypeAdapterFactory<Event> factoryEvent = RuntimeTypeAdapterFactory
				.of(Event.class, "eventType")
				.registerSubtype(EventPlaceFamilyMember.class, EventPlaceFamilyMember.class.getSimpleName())
				.registerSubtype(EventFamilyMemberChosen.class, EventFamilyMemberChosen.class.getSimpleName())
				.registerSubtype(EventPickCard.class, EventPickCard.class.getSimpleName())
				.registerSubtype(EventSpendResource.class, EventSpendResource.class.getSimpleName())
				.registerSubtype(EventSupportChurch.class, EventSupportChurch.class.getSimpleName())
				.registerSubtype(EventEndMatch.class, EventEndMatch.class.getSimpleName())
				.registerSubtype(EventStartTurn.class, EventStartTurn.class.getSimpleName())
				.registerSubtype(EventServantsRequested.class, EventServantsRequested.class.getSimpleName())
				.registerSubtype(EventActivateLeaderCard.class, EventActivateLeaderCard.class.getSimpleName())
				.registerSubtype(EventReceiveResource.class, EventReceiveResource.class.getSimpleName());


		return new GsonBuilder()
				.registerTypeAdapterFactory(factoryEffectProvider)
				.registerTypeAdapterFactory(factoryOccupiable)
				.registerTypeAdapterFactory(factoryCard)
 				.registerTypeAdapterFactory(factoryResource)
				.registerTypeAdapterFactory(factoryEffect)
				.registerTypeAdapterFactory(factoryEvent)
				.registerTypeAdapter(CardDevelopment.class, new CardDevelopmentInstanceCreator());
	}
	
}
