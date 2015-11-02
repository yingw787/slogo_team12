package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import engine.Controller;
import engine.IController;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class ClickableManager {

    private Pane canvasBox;
    private Turtle turtle;
    private ObservableList<Integer> colorsList;
    private ObservableList<Rectangle> colorView;
    private Map<Integer,Color> myColors;
    private IController controller;
    private TextArea t;
    private ObservableList<String> myHistList;
    private ObservableList<String> myVariableNames;
    private ObservableList<String> myVariableValues;
    private GUI gui;
    private List<Clickable> optionsBoxClickables = new ArrayList<Clickable>();
    private List<Clickable> variableBoxClickables = new ArrayList<Clickable>();;
    private List<Clickable> historyBoxClickables = new ArrayList<Clickable>();;
    private List<Clickable> commandAndVarBoxClickables = new ArrayList<Clickable>();;

    public ClickableManager (Pane canvasBox,
                             Turtle turtle,
                             ObservableList<Integer> ColorsList,
                             Map<Integer,Color> myColors,
                             ObservableList<Rectangle> colorView,
                             IController myController,
                             TextArea t,
                             ObservableList<String> myHistList,
                             ObservableList<String> myVariableNames,
                             ObservableList<String> myVariableValues,
                             GUI gui) {
        this.canvasBox = canvasBox;
        this.colorsList = ColorsList;
        this.myColors = myColors;
        this.colorView = colorView;
        this.turtle = turtle;
        this.controller = myController;
        this.t = t;
        this.myHistList = myHistList;
        this.myVariableNames = myVariableNames;
        this.myVariableValues = myVariableValues;
        this.gui = gui;
        initializeClickables();
    }

    public void initializeClickables () {
        initializeOptionsBoxClickables();
        initializevariableBoxClickables();
        initializehistoryBoxClickables();
        initializecommandAndVarBoxClickables();
    }

    private void initializeOptionsBoxClickables () {
        ColorIndexes paneColorSelect = new ColorIndexes(canvasBox, colorsList,controller);
        ColorViews penColorSelect = new ColorViews(turtle, colorView,controller);
        PickImage pickImage = new PickImage(canvasBox, turtle);
        optionsBoxClickables.add(paneColorSelect);
        optionsBoxClickables.add(penColorSelect);
        optionsBoxClickables.add(pickImage);
    }

    private void initializecommandAndVarBoxClickables () {
        Go go = new Go(t, controller);
        Save save = new Save(controller, t);
        Load load = new Load(t);
        commandAndVarBoxClickables.add(go);
        commandAndVarBoxClickables.add(save);
        commandAndVarBoxClickables.add(load);

    }

    private void initializevariableBoxClickables () {
        VarNameListView variableNames = new VarNameListView(controller, myVariableNames, gui);
        VarValueListView variableValues = new VarValueListView(controller, myVariableValues, gui);
        variableBoxClickables.add(variableNames);
        variableBoxClickables.add(variableValues);

    }

    private void initializehistoryBoxClickables () {
        HistListView histListView = new HistListView(t, myHistList);
        Reset reset = new Reset(controller);
        NewWindow newWindow = new NewWindow(controller);
        historyBoxClickables.add(histListView);
        historyBoxClickables.add(reset);
        historyBoxClickables.add(newWindow);

    }

    public List<Clickable> getOptionsBoxClickables () {

        return optionsBoxClickables;
    }

    public List<Clickable> getVariableBoxClickables () {

        return variableBoxClickables;
    }

    public List<Clickable> getHistoryBoxClickables () {

        return historyBoxClickables;
    }

    public List<Clickable> getCommandAndVarBoxClickables () {
        return commandAndVarBoxClickables;
    }
}
