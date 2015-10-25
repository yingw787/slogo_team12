package view;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;


public class PaneColorSelect extends Clickable<ComboBox> {
    ComboBox paneColorSelect;

    public PaneColorSelect (Pane canvasBox, ObservableList<String> colorsList) {
        paneColorSelect = new ComboBox();
        paneColorSelect.setItems(colorsList);
        paneColorSelect.setOnAction(e -> this
                .changePaneColor(canvasBox,
                                 paneColorSelect.getSelectionModel().getSelectedItem().toString()));

    }

    private void changePaneColor (Pane canvasBox, String canvasColor) {
        try {
            canvasBox.setStyle("-fx-background-color: " + canvasColor + ";");
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
