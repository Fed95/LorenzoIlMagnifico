package it.polimi.ingsw.gc_12.JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc_12.*;
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

/**
 * Created by marco on 27/05/2017.
 */
public class LoaderMarket extends JsonMaster {

	private Type marketType = new TypeToken<Market>() {}.getType();

	public LoaderMarket (String filename){
		super(filename);
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

		GsonBuilder gsonBuiler = TypeAdapter.create();
		Gson gson = gsonBuiler.setExclusionStrategies(new FloorExclusionStrategy()).create();
		String gsonMarket = gson.toJson(market, marketType);
		ManageJsonFile manageJsonFile=new ManageJsonFile();
		manageJsonFile.toJsonFile(gsonMarket, this);
	}

	public TowerSet get(){
		ManageJsonFile manageJsonFile=new ManageJsonFile();
		String json = manageJsonFile.fromJsonFile(this);
		GsonBuilder gsonBuiler = TypeAdapter.create();
		Gson gson = gsonBuiler.setExclusionStrategies(new FloorExclusionStrategy()).create();
		return gson.fromJson(json, marketType);
	}
}
