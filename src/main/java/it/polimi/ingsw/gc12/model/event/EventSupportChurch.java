package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.view.client.ClientHandler;

import java.util.ArrayList;
import java.util.Collections;

public class EventSupportChurch extends Event {

    public EventSupportChurch(Player player){
        super(player);
    }

    public EventSupportChurch(){
        super();
    }

    @Override
    public void executeClientSide(ClientHandler client) {
        client.getMatch().updateResources(new ArrayList<>(Collections.singletonList(player)));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("line.separator"));
        sb.append(player.getName() + " has chosen to support the church.");
        return sb.toString();
    }
}
