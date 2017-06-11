package it.polimi.ingsw.gc_12.json.saver;

import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.json.OccupiableExclusionStrategy;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectChangeResource;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.occupiables.Occupiable;
import it.polimi.ingsw.gc_12.occupiables.SpaceWorkMultiple;
import it.polimi.ingsw.gc_12.occupiables.SpaceWorkSingle;
import it.polimi.ingsw.gc_12.resource.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaverBonusTile extends Saver<List<BonusTile>> {
	private Match match;
	public SaverBonusTile(Match match){
		super("bonusTiles");
		this.match = match;
	}

	@Override
	public void create() {
		match.init(null);
		List<BonusTile> bonusTiles = new ArrayList<>();

		List<Effect> effects;
		List<Resource> resourcesProduction;
		List<Resource> resourcesHarvest;
		Effect effectProduction;
		Effect effectHarvest;
		GameMode gameMode;
		
		List<Occupiable> occupiablesProduction = new ArrayList<>();
		occupiablesProduction.add(new SpaceWorkSingle(WorkType.PRODUCTION, 1,null));
		occupiablesProduction.add(new SpaceWorkMultiple(WorkType.PRODUCTION, 1,null));
		Event eventProduction = new EventPlaceFamilyMember(occupiablesProduction, new FamilyMember());

		List<Occupiable> occupiablesHarvest = new ArrayList<>();
		occupiablesHarvest.add(new SpaceWorkSingle(WorkType.HARVEST, 1,null));
		occupiablesHarvest.add(new SpaceWorkMultiple(WorkType.HARVEST, 1,null));
		Event eventHarvest = new EventPlaceFamilyMember(occupiablesHarvest, new FamilyMember());

		resourcesProduction = new ArrayList<>(Arrays.asList(new MilitaryPoint(1), new Money(2)));
		effectProduction = new EffectChangeResource(eventProduction, new ResourceExchange(null, resourcesProduction), false);
		resourcesHarvest = new ArrayList<>(Arrays.asList(new Wood(1), new Stone(1), new Servant(1)));
		effectHarvest = new EffectChangeResource(eventHarvest, new ResourceExchange(null, resourcesHarvest), false);
		effects = new ArrayList<>(Arrays.asList(effectProduction, effectHarvest));
		gameMode=GameMode.NORMAL;
		bonusTiles.add(new BonusTile(gameMode , effects));

		resourcesProduction = new ArrayList<>(Arrays.asList(new Servant(2), new Money(1)));
		effectProduction = new EffectChangeResource(eventProduction, new ResourceExchange(null, resourcesProduction), false);
		resourcesHarvest = new ArrayList<>(Arrays.asList(new Wood(1), new Stone(1), new MilitaryPoint(1)));
		effectHarvest = new EffectChangeResource(eventHarvest, new ResourceExchange(null, resourcesHarvest), false);
		effects = new ArrayList<>(Arrays.asList(effectProduction, effectHarvest));
		gameMode=GameMode.NORMAL;
		bonusTiles.add(new BonusTile(gameMode , effects));

		resourcesProduction = new ArrayList<>(Arrays.asList(new MilitaryPoint(2), new Money(1)));
		effectProduction = new EffectChangeResource(eventProduction, new ResourceExchange(null, resourcesProduction), false);
		resourcesHarvest = new ArrayList<>(Arrays.asList(new Wood(1), new Stone(1), new Servant(1)));
		effectHarvest = new EffectChangeResource(eventHarvest, new ResourceExchange(null, resourcesHarvest), false);
		effects = new ArrayList<>(Arrays.asList(effectProduction, effectHarvest));		
		gameMode=GameMode.ADVANCED;
		bonusTiles.add(new BonusTile(gameMode , effects));

		resourcesProduction = new ArrayList<>(Arrays.asList(new Servant(1), new Money(2)));
		effectProduction = new EffectChangeResource(eventProduction, new ResourceExchange(null, resourcesProduction), false);
		resourcesHarvest = new ArrayList<>(Arrays.asList(new Wood(1), new Stone(1), new MilitaryPoint(1)));
		effectHarvest = new EffectChangeResource(eventHarvest, new ResourceExchange(null, resourcesHarvest), false);
		effects = new ArrayList<>(Arrays.asList(effectProduction, effectHarvest));
		bonusTiles.add(new BonusTile(gameMode , effects));

		resourcesProduction = new ArrayList<>(Arrays.asList(new Servant(1), new MilitaryPoint(2)));
		effectProduction = new EffectChangeResource(eventProduction, new ResourceExchange(null, resourcesProduction), false);
		resourcesHarvest = new ArrayList<>(Arrays.asList(new Wood(1), new Stone(1), new Money(1)));
		effectHarvest = new EffectChangeResource(eventHarvest, new ResourceExchange(null, resourcesHarvest), false);
		effects = new ArrayList<>(Arrays.asList(effectProduction, effectHarvest));
		gameMode=GameMode.NORMAL;
		bonusTiles.add(new BonusTile(gameMode , effects));

		super.save(bonusTiles, new OccupiableExclusionStrategy());
	}
}
