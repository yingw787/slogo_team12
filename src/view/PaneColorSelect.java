package view;

import java.util.Map;
import engine.Controller;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class PaneColorSelect extends Clickable<ComboBox> {
    private ComboBox paneColorSelect;
    private Controller controller;
    public PaneColorSelect (Pane canvasBox,
                            ObservableList<Integer> colorsList,
                            Controller controller) {
        this.controller = controller;
        paneColorSelect = new ComboBox();
        // paneColorSelect.setVal
        paneColorSelect.setItems(colorsList);
        paneColorSelect.setPromptText("Background Color");
        // TODO: Do i even need e here?
        paneColorSelect.setOnAction(e -> this
                .changePaneColor(canvasBox,
                                 paneColorSelect.getSelectionModel().getSelectedItem()));

    }

    private void changePaneColor (Pane canvasBox, Object object) {
        try {
            controller.setBackgroundColor((int) object);
        }
        catch (Exception e) {
            // invalid color
        }

    }


    @Override
    ComboBox getClickable () {
        return paneColorSelect;
    }
}
