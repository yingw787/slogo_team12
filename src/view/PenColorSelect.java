package view;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;

public class PenColorSelect extends Clickable<ComboBox>{
    ComboBox penColorSelect;

    public PenColorSelect (Turtle turtle, ObservableList<String> colorsList) {
        penColorSelect = new ComboBox();
        penColorSelect.setItems(colorsList);
        penColorSelect.setPromptText("Pen Color");
        penColorSelect.setOnAction(e -> this
                .changePenColor(turtle,
                                 penColorSelect.getSelectionModel().getSelectedItem().toString()));

    }


    private void changePenColor (Turtle turtle, String penColor) {
        try {
            //System.out.println(penColor);
            turtle.setPenColor(Color.valueOf(penColor));
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
