package view;

import java.util.List;
import java.util.ResourceBundle;

import engine.IController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class GUIfactory {

    private ResourceBundle myResources;
    private IController myController;

    public GUIfactory (ResourceBundle bundle, IController control) {
        myController = control;
        // need to decide if controller should be passed to it
        // if not then all the button handlers, etc need to be set in GUI
        myResources = bundle;
    }

    public Node getRoot () {
        return makeBorderPane();

    }

    public BorderPane makeBorderPane () {

        return new BorderPane();

    }

    public Button makeButton (String name, EventHandler<ActionEvent> e) {

        Button b = new Button(name);
        b.setOnAction(e);

        return b;
    }

    public ComboBox makeComboBox () {
        ComboBox b = new ComboBox();
        return b;
    }

    public VBox makeVBox () {

        return new VBox();

    }

    public ListView makeClickableList (ObservableList<String> theList) {

        ListView listView = new ListView();
        listView.setItems(theList);

        return listView;

    }

    public HBox makeHBox () {

        return new HBox();

    }

    public Pane makePane () {
        // Pane p = new Pane();

        return new Pane();
    }

    public TextArea makeTextArea () {
        TextArea t = new TextArea();
        t.setMinSize(200, 100);
        return t;
        // return null;
    }

}
