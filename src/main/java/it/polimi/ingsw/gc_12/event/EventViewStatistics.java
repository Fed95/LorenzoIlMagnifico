package it.polimi.ingsw.gc_12.event;

import it.polimi.ingsw.gc_12.Player;
import it.polimi.ingsw.gc_12.client.ClientHandler;
import it.polimi.ingsw.gc_12.effect.EffectProvider;

import java.util.List;

public class EventViewStatistics extends Event {

    private Player chosenPlayer;

    public EventViewStatistics(Player player, Player chosenPlayer) {
        super(player);
        this.chosenPlayer = chosenPlayer;
    }

    @Override
    public List<Object> getAttributes() {
        return null;
    }

    @Override
    public void executeClientSide(ClientHandler client) {
        if(client.isMyTurn()) {
            StringBuilder sb = new StringBuilder();
            sb.append(System.getProperty("line.separator"));
            sb.append("Viewing statistics of " + chosenPlayer.getName() + ":").append(System.getProperty("line.separator"));
            sb.append(System.getProperty("line.separator"));

            sb.append("Family Members:").append(System.getProperty("line.separator"));
            sb.append(chosenPlayer.printFamilyMembers()).append(System.getProperty("line.separator"));

            sb.append("Resources:").append(System.getProperty("line.separator"));
            sb.append(chosenPlayer.printResources()).append(System.getProperty("line.separator"));

            sb.append("Excommunications:").append(System.getProperty("line.separator"));
            sb.append(chosenPlayer.getExcommunications()).append(System.getProperty("line.separator"));
            sb.append(System.getProperty("line.separator"));

            sb.append("Cards:").append(System.getProperty("line.separator"));
            sb.append(chosenPlayer.getCards());

            System.out.println(sb.toString());
            super.executeClientSide(client);
        }
    }

    public Player getChosenPlayer() {
        return chosenPlayer;
    }

    @Override
    public String toString() {
        return player.getName() + " is viewing " + chosenPlayer.getName() + "'s stats.";
    }
}
