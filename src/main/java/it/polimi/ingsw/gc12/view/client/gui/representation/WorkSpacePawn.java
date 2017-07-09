package it.polimi.ingsw.gc12.view.client.gui.representation;

import it.polimi.ingsw.gc12.model.board.occupiable.WorkType;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMember;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

/**
 * Representing the pawn onn the workspaces
 */
public class WorkSpacePawn {
    private SimpleObjectProperty<WorkType> workType;
    private SimpleObjectProperty<PlayerColor> playerColor;
    private SimpleObjectProperty<FamilyMemberColor> colorFamilyMember;
    private SimpleObjectProperty<Image> familyTemporaryImage;
    private boolean occupied;

    /**
     * Constructor
     * @param workType type of the workspace represented
     * @param playerColor player color owning the pawn
     * @param familyMemberColor represented by the pawn
     * @param path path of the family member
     */
    public WorkSpacePawn(WorkType workType, PlayerColor playerColor, FamilyMemberColor familyMemberColor, String path){
        Image temporaryImage = new Image(path);
        this.familyTemporaryImage = new SimpleObjectProperty<Image>(temporaryImage);
        this.playerColor = new SimpleObjectProperty<PlayerColor>(playerColor);
        this.colorFamilyMember = new SimpleObjectProperty<FamilyMemberColor>(familyMemberColor);
        this.workType = new SimpleObjectProperty<WorkType>(workType);
    }

    public ObjectProperty<Image> getFamilyTemporaryImage(){
        return familyTemporaryImage;
    }

    /**
     * Removing the pawn from the workspace
     */
    public void removePawn(){
        Image removing = new Image("img/players/transparentPlayer.png");
        familyTemporaryImage.set(removing);
    }

    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Setting the family member
     * @param familyMember family memeber to set
     * @param playerColor player owning the family
     */
    public void setFamilyMember(FamilyMember familyMember, PlayerColor playerColor) {
        familyTemporaryImage.set(new Image("img/players/"+playerColor.toString()+"/"+playerColor.toString()+"_"+familyMember.getColor().toString()+".png"));
        occupied = true;
    }
}
