package view;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import com.sun.istack.internal.logging.Logger;
import engine.Controller;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class GUI {

    private GUIfactory myFactory;

    public static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
    private ResourceBundle myResources;
    private Controller myController;
    private BorderPane root;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private ObservableList<String> myHistList;
    private ObservableList<String> myColorsList;
    private Pane canvasBox;
    private Turtle turtle;
    private ReadOnlyIntegerProperty activeTurtle;
    // consider adding a public method called get myHistList, that returns
    // immutable histList

    public GUI (Controller controller, String language, ReadOnlyIntegerProperty myActiveTurtle) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        myHistList = FXCollections.observableArrayList();
        myColorsList = FXCollections.observableArrayList();
        turtle = new Turtle(SCREEN_WIDTH, SCREEN_HEIGHT);
        myController = controller;
        myFactory = new GUIfactory(myResources, myController);

        activeTurtle = myActiveTurtle;

        initColors(myColorsList);// myHistList.add("History");
        TextArea t = myFactory.makeTextArea();
        canvasBox = myFactory.makePane();

        root = myFactory.makeBorderPane();

        HBox optionsBox = myFactory.makeHBox();
        root.setTop(optionsBox);
        HBox commandAndVarBox = myFactory.makeHBox();
        commandAndVarBox.setMaxHeight(SCREEN_HEIGHT / 5);
        commandAndVarBox.getChildren().add(t);
        root.setBottom(commandAndVarBox);


        /*
         * Background canvasBG = new Background(???);
         * canvasBox.setBackground(canvasBG);
         */

        // Make VARIABLE CHANGEABLE BY BACKEND
        // canvasBox.getChildren().add(new Rectangle(50,50));

        // not sure if this actually works
        canvasBox.setPrefSize(SCREEN_WIDTH * (3 / 4), SCREEN_HEIGHT * (3 / 4));
        root.setCenter(canvasBox);

        ClickableManager myClickableManager =
                new ClickableManager(canvasBox, turtle, myColorsList, myController, t);
        List<Clickable> optionsBoxClickables = myClickableManager.getOptionsBoxClickables();
        List<Clickable> commandAndVarBoxClickables =
                myClickableManager.getCommandAndVarBoxClickables();

        for (Clickable g : optionsBoxClickables) {
            optionsBox.getChildren().add((Node) g.getClickable());
        }
        for (Clickable g : commandAndVarBoxClickables) {
            commandAndVarBox.getChildren().add((Node) g.getClickable());
        }


        VBox historyBox = myFactory.makeVBox();
        historyBox.setMaxWidth(SCREEN_WIDTH / 4);
        historyBox.getChildren().add(new Text("History"));
        root.setRight(historyBox);

        ListView myHistListView = myFactory.makeClickableList(myHistList);
        historyBox.getChildren().add(myHistListView);
        historyBox.getChildren().add(myFactory.makeButton("reset", e -> myController.reset()));
        historyBox.getChildren().add(myFactory
                .makeButton("New Window", e -> myController.makeNewWindow(new Stage())));

        // this should be moved to a more appropriate place
        myHistListView.setOnMouseClicked(e -> {
            // lambda, checks if selected is not null, if its not, populate the
            // command box with the
            // selected history
            if (!(myHistListView.getSelectionModel().getSelectedItem() == null)) {
                t.setText((myHistListView.getSelectionModel().getSelectedItem().toString()));
            }

        });

        // makeButton: setOnAction(e-> myController.submit(t.getText()));

        VBox variablesBox = myFactory.makeVBox();
        variablesBox.getChildren().add(new Text("Variables"));

        ///////// The list of variables needs to come from backend. updated by
        ///////// backend.
        /// Rather than be a list of strings, list of some type of object
        ///////// (variables object?) that
        ///////// can
        // be changed on click. To change on click see lambda function for
        ///////// history command box.
        ObservableList<String> listOfVariables = FXCollections.observableArrayList();
        listOfVariables.add("Variable 1");

        variablesBox.getChildren().add(myFactory.makeClickableList(listOfVariables));
        commandAndVarBox.getChildren().add(variablesBox);

        // make a root, etc, layout everything with the GUIfactory
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("turtle.gif"));
        turtle.setTurtleImage(image);
        canvasBox.getChildren().add(turtle.getTurtleImage());

    }

    private void initColors (ObservableList<String> myColorsList) {
        myColorsList.add("white");
        myColorsList.add("blue");
        myColorsList.add("red");
        myColorsList.add("purple");
        myColorsList.add("orange");
        myColorsList.add("green");
    }

    public void setAndShowScene (Stage primaryStage) {
        // WIDTH AND HEIGHT, MORE DETAILS FOR SCENE
        primaryStage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT));
        primaryStage.show();

    }

    public void addToHistory (String stringFromGUI) {

        if (stringFromGUI.trim().length() > 0) {
            // checks that its not just all whitespace
            myHistList.add(stringFromGUI);
        }
        // this will become private when we set up an observer relationship
        // currently controller directly calls this in submit

    }

    public void drawLine () {
        // turtle.setCurrentXPos(turtle.getCurrentXPos() + 20);
        // turtle.setCurrentYPos(turtle.getCurrentYPos() + 20);
        double turtleHeight = turtle.getTurtleImage().getFitHeight();
        // System.out.println(turtleHeight);
        double turtleWidth = turtle.getTurtleImage().getFitWidth();
        Line line = new Line();
        line.setStartX(turtle.getPastXPos() + turtleWidth / 2);
        line.setStartY(turtle.getPastYPos() + turtleHeight / 2);
        line.setEndX(turtle.getCurrentXPos() + turtleWidth / 2);
        line.setEndY(turtle.getCurrentYPos() + turtleHeight / 2);
        line.setStroke(turtle.getPenColor());
        canvasBox.getChildren().add(line);

    }

    public void clearLines () {
        List<Node> newChildren = canvasBox.getChildren().stream()
                .filter(child -> !child.getClass().equals(new Line().getClass()))
                .collect(Collectors.toList());
        canvasBox.getChildren().clear();
        canvasBox.getChildren().addAll(newChildren);

    }

    public void updateTurtle (double[] Pos) {

        turtle.setPastXPos(turtle.getCurrentXPos());
        turtle.setPastYPos(turtle.getCurrentYPos());
        turtle.setCurrentXPos(Pos[0]);
        turtle.setCurrentYPos(Pos[1]);
    }

    public double[] getTurtlePosition () {

        double[] pos = new double[2];
        pos[0] = turtle.getCurrentXPos();
        pos[1] = turtle.getCurrentYPos();

        return pos;
    }

    public double getTurtleDirection () {
        return turtle.getDirection();
    }

    public void setTurtleDirection (double angle) {

        turtle.setDirection(angle);

        System.out.println(activeTurtle.intValue());
    }

    public boolean getPenBool () {

        return turtle.isPenDown();
    }

    public void setTurtlePen (boolean penDown) {

        turtle.setPenDown(penDown);
    }

    public void setTurtleVisible (boolean showing) {

        turtle.setVisible(showing);
    }

    public boolean getTurtleVisible () {
        // TODO Auto-generated method stub
        return turtle.getVisible();
    }

}
