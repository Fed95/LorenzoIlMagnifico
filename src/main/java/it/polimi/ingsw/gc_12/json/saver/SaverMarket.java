package it.polimi.ingsw.gc_12.json.saver;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.json.OccupiableExclusionStrategy;
import it.polimi.ingsw.gc_12.occupiables.Market;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.occupiables.SpaceMarket;
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

	@Override
	public void create(){
		/*Market market = new Market();

		SpaceMarket spaceMarket;
		List<Occupiable> occupiables;
		Event event;
		Effect effect;
		List<Effect> effects;


		occupiables = new ArrayList<>(Arrays.asList(new SpaceMarket(0, null)));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(1));
		effect = new EffectChangeResource(event, new ResourceExchange(null, new Servant(5)), false);
		effects = new ArrayList<>(Arrays.asList(effect));
		spaceMarket = new SpaceMarket(1, effects);
		market.addSpaceMarket(spaceMarket);

		occupiables = new ArrayList<>(Arrays.asList(new SpaceMarket(0, null)));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(1));
		List<Resource> resources = new ArrayList<>(Arrays.asList(new MilitaryPoint(3), new Money(2)));
		effects = new ArrayList<>(Arrays.asList(new EffectChangeResource(event, new ResourceExchange(null, resources), false)));
		spaceMarket = new SpaceMarket(1, effects);
		market.addSpaceMarket(spaceMarket);

		occupiables = new ArrayList<>(Arrays.asList(new SpaceMarket(0, null)));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(1));
		effect = new EffectChangeResource(event, new ResourceExchange(null, new CouncilPrivilege(5)), false);
		effects = new ArrayList<>(Arrays.asList(effect));
		spaceMarket = new SpaceMarket(1, effects);
		market.addSpaceMarket(spaceMarket);


		occupiables = new ArrayList<>(Arrays.asList(new SpaceMarket(0, null)));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(1));
		effect = new EffectChangeResource(event, new ResourceExchange(null, new Money(5)), false);
		effects = new ArrayList<>(Arrays.asList(effect));
		spaceMarket = new SpaceMarket(1, effects);
		market.addSpaceMarket(spaceMarket);

		super.save(market, new OccupiableExclusionStrategy());
	*/}
}
