package it.polimi.ingsw.gc_12.JSON.saver;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.JSON.OccupiableExclusionStrategy;
import it.polimi.ingsw.gc_12.Market;
import it.polimi.ingsw.gc_12.Occupiable;
import it.polimi.ingsw.gc_12.SpaceMarket;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectChangeResource;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.resource.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by marco on 27/05/2017.
 */
public class SaverMarket extends Saver<Market> {

	public SaverMarket(){
		super("market");
	}

	public void create(){
		Market market = new Market();

		SpaceMarket spaceMarket;
		List<Occupiable> occupiables;
		Event event;
		List<Effect> effects;

		spaceMarket = new SpaceMarket(null, 1, 1);
		occupiables = new ArrayList<>(Arrays.asList(new SpaceMarket(null, 1, 1)));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(spaceMarket.getRequiredValue()));
		effects = new ArrayList<>(Arrays.asList(new EffectChangeResource(event, null, new Servant(5))));
		spaceMarket.setEffects(effects);
		market.addSpaceMarket(spaceMarket);

		spaceMarket = new SpaceMarket(null, 1, 1);
		occupiables = new ArrayList<>(Arrays.asList(new SpaceMarket(null, 1, 1)));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(spaceMarket.getRequiredValue()));
		List<Resource> resources = new ArrayList<>(Arrays.asList(new MilitaryPoint(3), new Money(2)));
		effects = new ArrayList<>(Arrays.asList(new EffectChangeResource(event, null, resources)));
		spaceMarket.setEffects(effects);
		market.addSpaceMarket(spaceMarket);

		spaceMarket = new SpaceMarket(null, 1, 1);
		occupiables = new ArrayList<>(Arrays.asList(new SpaceMarket(null, 1, 1)));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(spaceMarket.getRequiredValue()));
		effects = new ArrayList<>(Arrays.asList(new EffectChangeResource(event, null, new CouncilPrivilege(5))));
		spaceMarket.setEffects(effects);
		market.addSpaceMarket(spaceMarket);

		spaceMarket = new SpaceMarket(null, 1, 1);
		occupiables = new ArrayList<>(Arrays.asList(new SpaceMarket(null, 1, 1)));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(spaceMarket.getRequiredValue()));
		effects = new ArrayList<>(Arrays.asList(new EffectChangeResource(event, null, new Money(5))));
		spaceMarket.setEffects(effects);
		market.addSpaceMarket(spaceMarket);

		super.save(market, new OccupiableExclusionStrategy());
	}
}
