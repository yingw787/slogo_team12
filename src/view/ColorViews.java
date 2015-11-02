package view;

import java.util.ArrayList;
import java.util.Map;
import engine.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class ColorViews implements Clickable<ComboBox> {
    private ComboBox penColorSelect;
    private Map<Integer, Color> myColors;
    private Controller controller;

    public ColorViews (Turtle turtle,
                           ObservableList<Rectangle> colorView,
                           Controller controller) {
        this.myColors = myColors;
        this.controller = controller;
        penColorSelect = new ComboBox();
        penColorSelect.setItems(colorView);
        penColorSelect.setPromptText("Colors");

    }


    @Override
    public ComboBox getClickable () {
        return penColorSelect;
    }

}
