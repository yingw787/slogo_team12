package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;


public class ClickableManager {

    Pane canvasBox;
    Turtle turtle;
    ObservableList<String> colorsList;
    private List<Clickable> optionsBoxClickables = new ArrayList<Clickable>();
    private List<Clickable> variableBoxClickables = new ArrayList<Clickable>();;
    private List<Clickable> historyBoxClickables = new ArrayList<Clickable>();;
    private List<Clickable> commandAndVarBoxClickables = new ArrayList<Clickable>();;

    public ClickableManager (Pane canvasBox, Turtle turtle, ObservableList<String> ColorsList) {
        this.canvasBox = canvasBox;
        this.colorsList = ColorsList;
        this.turtle = turtle;
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
        // TODO Auto-generated method stub

    }

    private void initializevariableBoxClickables () {
        // TODO Auto-generated method stub

    }

    private void initializehistoryBoxClickables () {
        // TODO Auto-generated method stub

    }

    public List<Clickable> getOptionsBoxClickables () {

        return optionsBoxClickables;
    }

}
