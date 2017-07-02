package it.polimi.ingsw.gc_12.java_fx;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rugge on 02/07/2017.
 */
public class DialogCouncilPrivilegeController {
    @FXML private Ellipse privilege0;
    @FXML private Ellipse privilege1;
    @FXML private Ellipse privilege2;
    @FXML private Ellipse privilege3;
    @FXML private Ellipse privilege4;
    Map<Ellipse, Integer> mapEllipseInteger = new HashMap<>();
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

    @FXML
    private void privilegeClicked(MouseEvent event){
        clicked = true;
        selected = mapEllipseInteger.get(event.getSource());

        dialogStage.close();
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public int getSelected(){
        if(clicked) {
            return selected;
        }
        return -1;
    }
}
