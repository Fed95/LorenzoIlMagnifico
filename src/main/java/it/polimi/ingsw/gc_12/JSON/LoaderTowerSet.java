package it.polimi.ingsw.gc_12.JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.regexp.internal.RE;
import it.polimi.ingsw.gc_12.*;
import it.polimi.ingsw.gc_12.card.Card;
import it.polimi.ingsw.gc_12.card.CardExclusionStrategy;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.card.FloorExclusionStrategy;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectChangeResource;
import it.polimi.ingsw.gc_12.event.Event;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.resource.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: remove it on production
public class LoaderTowerSet extends JsonMaster {

	private Type towerSetType = new TypeToken<TowerSet>() {}.getType();

	public LoaderTowerSet(String filename){
		super(filename);
	}

	public void create(){

		TowerSet towerSet = new TowerSet();
		Tower tower;
		TowerFloor floor;
		List<Occupiable> occupiables;
		Event event;
		List<Effect> effects;

		// Territory tower
		tower = towerSet.getTower(CardType.TERRITORY);

		// 3rd floor
		floor = tower.getFloor(2);
		occupiables = new ArrayList<>(Arrays.asList(new TowerFloor(tower, floor.getFloorNum(), floor.getRequiredValue(), tower.getType())));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(floor.getRequiredValue()));
		effects = new ArrayList<>(Arrays.asList(new EffectChangeResource(event, null, new Wood(1))));
		floor.setEffects(effects);

		// 4th floor
		floor = tower.getFloor(3);
		occupiables = new ArrayList<>(Arrays.asList(new TowerFloor(tower, floor.getFloorNum(), floor.getRequiredValue(), tower.getType())));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(floor.getRequiredValue()));
		effects = new ArrayList<>(Arrays.asList(new EffectChangeResource(event, null, new Wood(2))));
		floor.setEffects(effects);

		// Character tower
		tower = towerSet.getTower(CardType.CHARACTER);

		// 3rd floor
		floor = tower.getFloor(2);
		occupiables = new ArrayList<>(Arrays.asList(new TowerFloor(tower, floor.getFloorNum(), floor.getRequiredValue(), tower.getType())));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(floor.getRequiredValue()));
		effects = new ArrayList<>(Arrays.asList(new EffectChangeResource(event, null, new Stone(1))));
		floor.setEffects(effects);

		// 4th floor
		floor = tower.getFloor(3);
		occupiables = new ArrayList<>(Arrays.asList(new TowerFloor(tower, floor.getFloorNum(), floor.getRequiredValue(), tower.getType())));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(floor.getRequiredValue()));
		effects = new ArrayList<>(Arrays.asList(new EffectChangeResource(event, null, new Stone(2))));
		floor.setEffects(effects);

		// Building tower
		tower = towerSet.getTower(CardType.BUILDING);

		// 3rd floor
		floor = tower.getFloor(2);
		occupiables = new ArrayList<>(Arrays.asList(new TowerFloor(tower, floor.getFloorNum(), floor.getRequiredValue(), tower.getType())));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(floor.getRequiredValue()));
		effects = new ArrayList<>(Arrays.asList(new EffectChangeResource(event, null, new MilitaryPoint(1))));
		floor.setEffects(effects);

		// 4th floor
		floor = tower.getFloor(3);
		occupiables = new ArrayList<>(Arrays.asList(new TowerFloor(tower, floor.getFloorNum(), floor.getRequiredValue(), tower.getType())));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(floor.getRequiredValue()));
		effects = new ArrayList<>(Arrays.asList(new EffectChangeResource(event, null, new MilitaryPoint(2))));
		floor.setEffects(effects);

		// Venture tower
		tower = towerSet.getTower(CardType.VENTURE);

		// 3rd floor
		floor = tower.getFloor(2);
		occupiables = new ArrayList<>(Arrays.asList(new TowerFloor(tower, floor.getFloorNum(), floor.getRequiredValue(), tower.getType())));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(floor.getRequiredValue()));
		effects = new ArrayList<>(Arrays.asList(new EffectChangeResource(event, null, new Money(1))));
		floor.setEffects(effects);

		// 4th floor
		floor = tower.getFloor(3);
		occupiables = new ArrayList<>(Arrays.asList(new TowerFloor(tower, floor.getFloorNum(), floor.getRequiredValue(), tower.getType())));
		event = new EventPlaceFamilyMember(occupiables, new FamilyMember(floor.getRequiredValue()));
		effects = new ArrayList<>(Arrays.asList(new EffectChangeResource(event, null, new Money(2))));
		floor.setEffects(effects);


		GsonBuilder gsonBuiler = TypeAdapter.create();
		Gson gson = gsonBuiler.setExclusionStrategies(new FloorExclusionStrategy()).create();
		String gsonTower = gson.toJson(towerSet, towerSetType);
		ManageJsonFile manageJsonFile=new ManageJsonFile();
		manageJsonFile.toJsonFile(gsonTower, this);
	}

	public TowerSet get(){
		ManageJsonFile manageJsonFile=new ManageJsonFile();
		String json = manageJsonFile.fromJsonFile(this);
		GsonBuilder gsonBuiler = TypeAdapter.create();
		Gson gson = gsonBuiler.setExclusionStrategies(new FloorExclusionStrategy()).create();
		return gson.fromJson(json, towerSetType);
	}
}