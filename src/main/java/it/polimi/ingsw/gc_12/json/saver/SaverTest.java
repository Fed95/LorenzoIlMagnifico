package it.polimi.ingsw.gc_12.json.saver;

import it.polimi.ingsw.gc_12.FamilyMemberColor;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.card.CardExclusionStrategy;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.card.LeaderCard;
import it.polimi.ingsw.gc_12.effect.Effect;
import it.polimi.ingsw.gc_12.effect.EffectDenyEffect;
import it.polimi.ingsw.gc_12.event.EventPlaceFamilyMember;
import it.polimi.ingsw.gc_12.excommunication.ExcommunicationTile;
import it.polimi.ingsw.gc_12.json.loader.LoaderExcommmunications;
import it.polimi.ingsw.gc_12.occupiables.TowerFloor;
import it.polimi.ingsw.gc_12.resource.Resource;

import java.io.File;
import java.util.*;

/**
 * Created by feder on 2017-06-29.
 */
public class SaverTest extends Saver<List<FamilyMemberColor>> {

    public SaverTest(String filename) {
        super(filename);
    }

    Scanner scanner = new Scanner(System.in);
    public SaverTest(Match match){
        super("test");
    }

    @Override
    public void create(){

        int id = -1;

        List<FamilyMemberColor> colors = new ArrayList<>();

        colors.add(FamilyMemberColor.BLACK);


        File file = new File("test.JSON");



        super.save(colors, new CardExclusionStrategy());
    }

}
