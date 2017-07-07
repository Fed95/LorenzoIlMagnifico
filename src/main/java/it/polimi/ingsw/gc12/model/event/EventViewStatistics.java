package it.polimi.ingsw.gc12.model.event;

import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.player.Player;
import it.polimi.ingsw.gc12.controller.ActionHandler;
import it.polimi.ingsw.gc12.model.action.DiscardAction;
import it.polimi.ingsw.gc12.view.client.ClientHandler;

import java.util.ArrayList;

public class EventViewStatistics extends Event {

    private Player chosenPlayer;

    public EventViewStatistics(Player player, Player chosenPlayer) {
        super(player);
        this.chosenPlayer = chosenPlayer;
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

            sb.append("LeaderCards:").append(System.getProperty("line.separator"));
            sb.append(chosenPlayer.printLeaderCards()).append(System.getProperty("line.separator"));

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

    @Override
    public void setActions(Match match) {
        actions = new ArrayList<>();
        actions.add(new DiscardAction(player));
    }

    public Player getChosenPlayer() {
        return chosenPlayer;
    }

    @Override
    public String toString() {
        return player.getName() + " is viewing " + chosenPlayer.getName() + "'s stats";
    }
}
