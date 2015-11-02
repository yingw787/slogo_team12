package view;

import java.util.Map;
import engine.Controller;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class ColorIndexes implements Clickable<ComboBox> {
    private ComboBox paneColorSelect;
    private Controller controller;
    public ColorIndexes (Pane canvasBox,
                            ObservableList<Integer> colorsList,
                            Controller controller) {
        this.controller = controller;
        paneColorSelect = new ComboBox();
        // paneColorSelect.setVal
        paneColorSelect.setItems(colorsList);
        paneColorSelect.setPromptText("Color Indexes");
        // TODO: Do i even need e here?
       

    }
    @Override
    public
     ComboBox getClickable () {
        return paneColorSelect;
    }

   



}
