package it.polimi.ingsw.gc12.view.client.gui.representation;

import it.polimi.ingsw.gc12.model.board.occupiable.WorkType;
import it.polimi.ingsw.gc12.model.player.PlayerColor;
import it.polimi.ingsw.gc12.model.player.familymember.FamilyMemberColor;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

/**
 * Created by rugge on 08/07/2017.
 */
public class WorkSpacePawn {
    private SimpleObjectProperty<WorkType> workType;
    private SimpleObjectProperty<PlayerColor> playerColor;
    private SimpleObjectProperty<FamilyMemberColor> colorFamilyMember;
    private SimpleObjectProperty<Image> familyTemporaryImage;

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
    public void removePawn(){
        Image removing = new Image("img/players/transparentPlayer.png");
        familyTemporaryImage.set(removing);
    }
}
