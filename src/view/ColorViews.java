package view;

import java.util.ArrayList;
import java.util.Map;

import engine.IController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class ColorViews extends Clickable<ComboBox> {
    private ComboBox penColorSelect;
    private Map<Integer, Color> myColors;
    private IController controller;

    public ColorViews (Turtle turtle,
                           ObservableList<Rectangle> colorView,
                           IController controller) {
        this.myColors = myColors;
        this.controller = controller;
        penColorSelect = new ComboBox();
        penColorSelect.setItems(colorView);
        penColorSelect.setPromptText("Colors");

    }


    @Override
    ComboBox getClickable () {
        return penColorSelect;
    }

}
