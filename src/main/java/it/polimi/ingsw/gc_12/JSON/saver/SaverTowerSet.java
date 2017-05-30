package it.polimi.ingsw.gc_12.JSON.saver;

import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.JSON.OccupiableExclusionStrategy;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectChangeResource;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.occupiables.Tower;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;
import it.polimi.ingsw.gc_12.occupiables.TowerSet;
import it.polimi.ingsw.gc_12.resource.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: remove it on production
public class SaverTowerSet extends Saver<TowerSet> {

	public SaverTowerSet(){
		super("towers");
	}

	@Override
	public void create(){
		TowerSet towerSet = new TowerSet();
		Tower tower;
		TowerFloor floor;
		List<Occupiable> occupiables;
		Event event;
		Effect effect;
		List<Effect> effects;

		// Territory tower
		tower = towerSet.getTower(CardType.TERRITORY);

		// 3rd floor
		floor = tower.getFloor(2);
		occupiables = new ArrayList<>(Arrays.asList(new TowerFloor(tower, floor.getFloorNum(), floor.getRequiredValue(), tower.getType())));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(floor.getRequiredValue()));
		effect = new EffectChangeResource(event, new ResourceExchange(null, new Wood(1)), false);
		effects = new ArrayList<>(Arrays.asList(effect));
		floor.setEffects(effects);

		// 4th floor
		floor = tower.getFloor(3);
		occupiables = new ArrayList<>(Arrays.asList(new TowerFloor(tower, floor.getFloorNum(), floor.getRequiredValue(), tower.getType())));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(floor.getRequiredValue()));
		effect = new EffectChangeResource(event, new ResourceExchange(null, new Wood(2)), false);
		effects = new ArrayList<>(Arrays.asList(effect));
		floor.setEffects(effects);

		// Character tower
		tower = towerSet.getTower(CardType.CHARACTER);

		// 3rd floor
		floor = tower.getFloor(2);
		occupiables = new ArrayList<>(Arrays.asList(new TowerFloor(tower, floor.getFloorNum(), floor.getRequiredValue(), tower.getType())));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(floor.getRequiredValue()));
		effect = new EffectChangeResource(event, new ResourceExchange(null, new Stone(1)), false);
		effects = new ArrayList<>(Arrays.asList(effect));
		floor.setEffects(effects);

		// 4th floor
		floor = tower.getFloor(3);
		occupiables = new ArrayList<>(Arrays.asList(new TowerFloor(tower, floor.getFloorNum(), floor.getRequiredValue(), tower.getType())));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(floor.getRequiredValue()));
		effect = new EffectChangeResource(event, new ResourceExchange(null, new Stone(2)), false);
		effects = new ArrayList<>(Arrays.asList(effect));
		floor.setEffects(effects);

		// Building tower
		tower = towerSet.getTower(CardType.BUILDING);

		// 3rd floor
		floor = tower.getFloor(2);
		occupiables = new ArrayList<>(Arrays.asList(new TowerFloor(tower, floor.getFloorNum(), floor.getRequiredValue(), tower.getType())));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(floor.getRequiredValue()));
		effect = new EffectChangeResource(event, new ResourceExchange(null, new MilitaryPoint(1)), false);
		effects = new ArrayList<>(Arrays.asList(effect));
		floor.setEffects(effects);

		// 4th floor
		floor = tower.getFloor(3);
		occupiables = new ArrayList<>(Arrays.asList(new TowerFloor(tower, floor.getFloorNum(), floor.getRequiredValue(), tower.getType())));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(floor.getRequiredValue()));
		effect = new EffectChangeResource(event, new ResourceExchange(null, new Stone(2)), false);
		effects = new ArrayList<>(Arrays.asList(effect));
		floor.setEffects(effects);

		// Venture tower
		tower = towerSet.getTower(CardType.VENTURE);

		// 3rd floor
		floor = tower.getFloor(2);
		occupiables = new ArrayList<>(Arrays.asList(new TowerFloor(tower, floor.getFloorNum(), floor.getRequiredValue(), tower.getType())));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(floor.getRequiredValue()));
		effect = new EffectChangeResource(event, new ResourceExchange(null, new Money(1)), false);
		effects = new ArrayList<>(Arrays.asList(effect));
		floor.setEffects(effects);

		// 4th floor
		floor = tower.getFloor(3);
		occupiables = new ArrayList<>(Arrays.asList(new TowerFloor(tower, floor.getFloorNum(), floor.getRequiredValue(), tower.getType())));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(floor.getRequiredValue()));
		effect = new EffectChangeResource(event, new ResourceExchange(null, new Money(2)), false);
		effects = new ArrayList<>(Arrays.asList(effect));
		floor.setEffects(effects);

		super.save(towerSet, new OccupiableExclusionStrategy());
	}
}
