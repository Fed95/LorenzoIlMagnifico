package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.FamilyMember;
import it.polimi.ingsw.gc_12.Match;
import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.action.*;
import it.polimi.ingsw.gc_12.effect.EffectProvider;
import it.polimi.ingsw.gc_12.occupiables.SpaceWork;
import it.polimi.ingsw.gc_12.occupiables.SpaceWorkZone;

import java.util.ArrayList;
import java.util.List;

public class EventWorkplaceChosen extends Event {

    private FamilyMember familyMember;

    public EventWorkplaceChosen(Player player, FamilyMember familyMember) {
        super(player);
        this.familyMember = familyMember;
    }

    @Override
    public void setActions(ActionHandler actionHandler, Match match) {
        actions = new ArrayList<>();
        for(SpaceWorkZone spaceWorkZone : match.getBoard().getSpaceWorkZones().values()){
            for(SpaceWork spaceWork : spaceWorkZone.getSpaceWorks()){
                ActionPlace action = ActionFactory.createActionPlace(player, familyMember, spaceWork);
                if(action.isValid(match))
                    actions.add(new ActionPlaceOnSpaceWork(player, familyMember, spaceWork));
            }
        }
        actions.add(new DiscardAction(player));
    }

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    @Override
    public List<Object> getAttributes() {
        return null;
    }

    @Override
    public String toString() {
        return player.getName() + " is viewing the Workplace";
    }
}
