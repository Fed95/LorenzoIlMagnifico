package it.polimi.ingsw.gc12.view.client.gui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for the FXML of the Council Privilege.
 * It's a popup representing the choice of te privilege when you choose council
 * Palace with your family Member
 */
public class DialogCouncilPrivilegeController {
    @FXML private ImageView privilege0;
    @FXML private ImageView privilege1;
    @FXML private ImageView privilege2;
    @FXML private ImageView privilege3;
    @FXML private ImageView privilege4;
    Map<ImageView, Integer> mapEllipseInteger = new HashMap<>();
    private Stage dialogStage;
    private Boolean clicked = false;
    private int selected = -1;

    @FXML
    private void initialize() {
        mapEllipseInteger.put(privilege0, 0);
        mapEllipseInteger.put(privilege1, 1);
        mapEllipseInteger.put(privilege2, 2);
        mapEllipseInteger.put(privilege3, 3);
        mapEllipseInteger.put(privilege4, 4);
    }

    /**
     * Method for the click on a privilege in the popup
     * @param event
     */
    @FXML
    private void privilegeClicked(MouseEvent event){
        clicked = true;
        selected = mapEllipseInteger.get(event.getSource());
        ImageView image = (ImageView) event.getSource();
        image.setVisible(false);
        dialogStage.close();
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Method that return the index of the selected element
     * @return integer
     */
    public int getSelected(){
        if(clicked) {
            return selected;
        }
        return -1;
    }
}
