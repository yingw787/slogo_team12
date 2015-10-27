package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import engine.Controller;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;


public class ClickableManager {

    private Pane canvasBox;
    private Turtle turtle;
    private ObservableList<String> colorsList;
    private Controller controller;
    private TextArea t;
    private List<Clickable> optionsBoxClickables = new ArrayList<Clickable>();
    private List<Clickable> variableBoxClickables = new ArrayList<Clickable>();;
    private List<Clickable> historyBoxClickables = new ArrayList<Clickable>();;
    private List<Clickable> commandAndVarBoxClickables = new ArrayList<Clickable>();;

    public ClickableManager (Pane canvasBox,
                             Turtle turtle,
                             ObservableList<String> ColorsList,
                             Controller controller,
                             TextArea t) {
        this.canvasBox = canvasBox;
        this.colorsList = ColorsList;
        this.turtle = turtle;
        this.controller = controller;
        this.t = t;
        initializeClickables();
    }

    public void initializeClickables () {
        initializeOptionsBoxClickables();
        initializevariableBoxClickables();
        initializehistoryBoxClickables();
        initializecommandAndVarBoxClickables();
    }

    private void initializeOptionsBoxClickables () {
        PaneColorSelect paneColorSelect = new PaneColorSelect(canvasBox, colorsList);
        PenColorSelect penColorSelect = new PenColorSelect(turtle, colorsList);
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
        // TODO Auto-generated method stub

    }

    private void initializehistoryBoxClickables () {
        Reset reset = new Reset(controller);
        historyBoxClickables.add(reset);

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
