package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.view.client.ClientHandler;
import it.polimi.ingsw.gc12.model.board.occupiable.Occupiable;
import it.polimi.ingsw.gc12.view.client.gui.MainBoard;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EventPlaceFamilyMember extends Event implements EventView{

    protected List<Occupiable> occupiables = new ArrayList<>();
    protected FamilyMember familyMember;
    private int multiplier;
    private String description;

    public EventPlaceFamilyMember(Player player, List<Occupiable> occupiables, FamilyMember familyMember) {
        super(player);
        this.occupiables = occupiables;
        this.familyMember = familyMember;
        this.multiplier = 1;

        effectProviders.addAll(occupiables);
    }

    public EventPlaceFamilyMember(List<Occupiable> occupiables, FamilyMember familyMember) {
        this(null, occupiables, familyMember);
    }

    public EventPlaceFamilyMember(Player player, List<Occupiable> occupiables, FamilyMember familyMember, String description) {
        this(player, occupiables, familyMember);
        this.description = description;
    }

    public EventPlaceFamilyMember(List<Occupiable> occupiables, FamilyMember familyMember, String description) {
        this(null, occupiables, familyMember, description);
    }

    public EventPlaceFamilyMember(List<Occupiable> occupiables){
        super();
        this.occupiables = occupiables;
        this.multiplier = 1;

        effectProviders.addAll(occupiables);
    }

    public EventPlaceFamilyMember(Player player, Occupiable occupiable, FamilyMember familyMember) {
        this(player, new ArrayList<>(Collections.singletonList(occupiable)), familyMember);
    }

    public EventPlaceFamilyMember(){super();}


    public List<Occupiable> getOccupiables() {
        return occupiables;
    }

    public Occupiable getOccupiable() {
        return occupiables.get(0);
    }

    @Override
    public void executeClientSide(ClientHandler client) {
        if(client.isMyTurn()) {
            super.executeClientSide(client);
        }
        client.getMatch().placeFamilyMember(familyMember, getOccupiable(), player.getColor());
        client.getMatch().updateResources(new ArrayList<>(Collections.singletonList(player)));
    }

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((occupiables == null) ? 0 : occupiables.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        //Compares class
        if (!super.equals(obj)) {
            return false;
        }
        EventPlaceFamilyMember that = (EventPlaceFamilyMember) obj;

        if(this.familyMember == null || that.familyMember == null)
            return true;

        if(this.familyMember.getValue() < that.getFamilyMember().getValue()){
            return false;
        }

        // Check if they have an occupiable in common
        List<Occupiable> common = new ArrayList<>(occupiables);
        common.retainAll(that.occupiables);
        if (occupiables == null) {
            if (that.occupiables != null)
                return false;
        } else if (common.size() == 0){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if(description == null)
            return "EventPlaceFamilyMember";
        return description;
    }

    @Override
    public String toStringClient() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("line.separator"));
        sb.append(player.getName() + " has placed the " + familyMember.getColor() + " Family Member (of value " + familyMember.getValue() + ") on:").append(System.getProperty("line.separator"));
        sb.append(occupiables.get(0).toString());
        return sb.toString();
    }

    @Override
    public void executeViewSide(MainBoard view) {
        Platform.runLater(() -> {
            view.getControllerMainBoard().disablePassTurn(false);
            view.getControllerMainBoard().moveOccupiableImage(getOccupiable());
        });
    }
}