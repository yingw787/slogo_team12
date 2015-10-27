package view;

import java.util.Map;
import engine.Controller;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;


public class PenColorSelect extends Clickable<ComboBox> {
    private ComboBox penColorSelect;
    private ObservableList<Integer> colorsList;
    private Controller controller;
    public PenColorSelect (Turtle turtle,
                           ObservableList<Integer> colorsList,
                           Controller controller) {
        this.colorsList = colorsList;
        this.controller = controller;
        penColorSelect = new ComboBox();
        penColorSelect.setItems(colorsList);
        penColorSelect.setPromptText("Pen Color");
        penColorSelect.setOnAction(e -> this
                .changePenColor(turtle,
                                penColorSelect.getSelectionModel().getSelectedItem()));

    }

    private void changePenColor (Turtle turtle, Object penIndex) {
        try {
            controller.setPenColor((int)penIndex);
        }
        catch (Exception e) {
            // invalid color
        }
    }

    @Override
    ComboBox getClickable () {
        return penColorSelect;
    }

}
