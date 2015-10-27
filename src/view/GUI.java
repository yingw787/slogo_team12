package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import com.sun.istack.internal.logging.Logger;
import engine.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GUI extends Application {

    private GUIfactory myFactory;

    public static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
    private ResourceBundle myResources;
    private Controller myController;
    private BorderPane root;
    private static final double SCREEN_WIDTH = 800;
    private static final double SCREEN_HEIGHT = 600;
    private static final double CANVAS_RATIO = 0.75;
    private ObservableList<String> myHistList;
    private ObservableList<String> myVariableNames;
    private ObservableList<String> myVariableValues;
    private ObservableList<String> myColorsList;
    private Pane canvasBox;
    private List<Turtle> turtleList;
    private Turtle turtle;
    private Stage myStage;

    private ReadOnlyIntegerProperty activeTurtleNumber;
    // consider adding a public method called get myHistList, that returns
    // immutable histList

    public GUI (Controller controller, String language, ReadOnlyIntegerProperty myActiveTurtleNum) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("turtle.gif"));

        initTurtle(image, 1);
        setUpTurtleList(myActiveTurtleNum, image);

        myHistList = FXCollections.observableArrayList();
        myVariableNames = FXCollections.observableArrayList();
        myVariableValues = FXCollections.observableArrayList();
        myColorsList = FXCollections.observableArrayList();
        initColors(myColorsList);// myHistList.add("History");

        myController = controller;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        myFactory = new GUIfactory(myResources, myController);
        root = myFactory.makeBorderPane();
        VBox historyBox = myFactory.makeVBox();
        HBox optionsBox = myFactory.makeHBox();
        HBox commandAndVarBox = myFactory.makeHBox();
        commandAndVarBox.setMaxHeight(SCREEN_HEIGHT / 5);
        canvasBox = myFactory.makePane();
        canvasBox.setPrefSize(SCREEN_WIDTH * (CANVAS_RATIO), SCREEN_HEIGHT * (CANVAS_RATIO));
        TextArea t = myFactory.makeTextArea();
        root.setTop(optionsBox);
        root.setBottom(commandAndVarBox);
        root.setCenter(canvasBox);
        root.setRight(historyBox);

        ClickableManager myClickableManager =
                new ClickableManager(canvasBox, turtle, myColorsList, myController, t, myHistList,
                                     myVariableNames, myVariableValues, this);

        // Make VARIABLE CHANGEABLE BY BACKEND

        commandAndVarBox.getChildren().add(t);

        List<Clickable> optionsBoxClickables = myClickableManager.getOptionsBoxClickables();
        List<Clickable> commandAndVarBoxClickables =
                myClickableManager.getCommandAndVarBoxClickables();
        List<Clickable> historyBoxClickables = myClickableManager.getHistoryBoxClickables();
        List<Clickable> variableBoxClickables = myClickableManager.getVariableBoxClickables();

        initOptionsBox(optionsBox, optionsBoxClickables);
        initHistoryBox(historyBox, historyBoxClickables);

        initCommandAndVarBox(commandAndVarBox, commandAndVarBoxClickables);

        VBox variablesDisplayContainer = myFactory.makeVBox();
        HBox variablesBox = myFactory.makeHBox();
        variablesDisplayContainer.getChildren().add(new Text("Variables"));

        renderVariablesMap(variablesBox, variableBoxClickables);
        variablesDisplayContainer.getChildren().add(variablesBox);
        commandAndVarBox.getChildren().add(variablesDisplayContainer);

        canvasBox.getChildren().add(turtle.getTurtleImage());

    }

    private void initOptionsBox (HBox optionsBox, List<Clickable> optionsBoxClickables) {
        for (Clickable g : optionsBoxClickables) {
            optionsBox.getChildren().add((Node) g.getClickable());
        }
    }

    private void initHistoryBox (VBox historyBox, List<Clickable> historyBoxClickables) {
        historyBox.setMaxWidth(SCREEN_WIDTH / 4);
        historyBox.getChildren().add(new Text("History"));

        for (Clickable g : historyBoxClickables) {
            historyBox.getChildren().add((Node) g.getClickable());
        }
    }

    private void setUpTurtleList (ReadOnlyIntegerProperty myActiveTurtleNum, Image image) {
        turtleList = new ArrayList<Turtle>();
        turtleList.add(turtle);

        activeTurtleNumber = myActiveTurtleNum;
        activeTurtleNumber.addListener(new ChangeListener<Number>() {

            @Override
            public void changed (ObservableValue<? extends Number> observable,
                                 Number oldValue,
                                 Number newValue) {
                if (turtleList.size() < newValue.intValue()) {
                    turtleList.add(newValue.intValue() - 1,
                                   new Turtle(SCREEN_WIDTH * CANVAS_RATIO,
                                              SCREEN_HEIGHT * CANVAS_RATIO, image,
                                              newValue.intValue()));
                    // turtleList.get(newValue.intValue()-1).setTurtleImage(image);
                    // turtleList.get(newValue.intValue()-1).setTurtleID(newValue.intValue());
                    canvasBox.getChildren()
                            .add(turtleList.get(newValue.intValue() - 1).getTurtleImage());
                }
                // make sure this works, if does rename to active turtle
                turtle = turtleList.get(activeTurtleNumber.get() - 1);
            }
        });
    }

    private void initTurtle (Image image, int ID) {
        turtle = new Turtle(SCREEN_WIDTH * CANVAS_RATIO, SCREEN_HEIGHT * CANVAS_RATIO, image, 1);
        // turtle.setTurtleID(ID);
        // turtle.setTurtleImage(image);
    }

    private void initCommandAndVarBox (HBox commandAndVarBox,
                                       List<Clickable> commandAndVarBoxClickables) {
        VBox commandAndVarBoxButtonsHolder = new VBox();
        for (Clickable g : commandAndVarBoxClickables) {
            commandAndVarBoxButtonsHolder.getChildren().add((Node) g.getClickable());
        }

        commandAndVarBox.getChildren().add(commandAndVarBoxButtonsHolder);
    }

    private void initColors (ObservableList<String> myColorsList) {
        myColorsList.add("black");
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

        try {
            start(primaryStage);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
        // turtle = turtleList.get(activeTurtleNumber.get()-1);
        double turtleHeight = turtle.getTurtleImage().getFitHeight();
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

        // rename to Set Turtle Position

        turtle = turtleList.get(activeTurtleNumber.get() - 1);

        turtle.setPastXPos(turtle.getCurrentXPos());
        turtle.setPastYPos(turtle.getCurrentYPos());
        double slope;
        double deltaX = Pos[0] - turtle.getPastXPos();
        boolean forward = deltaX > 0;
        double deltaY = Pos[1] - turtle.getPastYPos();

        if (deltaX == 0) {
            slope = 1;
        }
        else {
            slope = deltaY / deltaX;
        }

        /*
         * KeyFrame frame = new KeyFrame(Duration.millis(10),
         * e -> {
         * 
         * if((turtle.getCurrentXPos() != Pos[0]) || (turtle.getCurrentYPos() !=Pos[1])){
         * System.out.println("need to move");
         * if(forward){
         * turtle.setCurrentXPos(turtle.getCurrentXPos()+1);
         * turtle.setCurrentYPos(turtle.getCurrentYPos()+slope);
         * }
         * if(!forward && deltaX != 0){
         * turtle.setCurrentXPos(turtle.getCurrentXPos()-1);
         * turtle.setCurrentYPos(turtle.getCurrentYPos()-slope);
         * }
         * if(deltaX == 0){
         * turtle.setCurrentYPos(turtle.getCurrentYPos()+deltaY/Math.abs(deltaY));
         * }
         * 
         * 
         * //This math is all messed up tho
         * 
         * drawLine();
         * }});
         * Timeline animation = new Timeline();
         * animation.setCycleCount(Timeline.INDEFINITE);
         * animation.getKeyFrames().add(frame);
         * animation.play();
         * 
         */

        turtle.setCurrentXPos(Pos[0]);
        turtle.setCurrentYPos(Pos[1]);
        if (turtle.isPenDown())

        {
            drawLine();
        }

    }

    public double[] getTurtlePosition () {
        // turtle = turtleList.get(activeTurtleNumber.get()-1);
        double[] pos = new double[2];
        pos[0] = turtle.getCurrentXPos();
        pos[1] = turtle.getCurrentYPos();

        return pos;
    }

    public double getTurtleDirection () {
        // turtle = turtleList.get(activeTurtleNumber.get()-1);
        return turtle.getDirection();
    }

    public void setTurtleDirection (double angle) {
        // turtle = turtleList.get(activeTurtleNumber.get()-1);
        turtle.setDirection(angle);
    }

    public boolean getPenBool () {
        // turtle = turtleList.get(activeTurtleNumber.get()-1);
        return turtle.isPenDown();
    }

    public void setTurtlePen (boolean penDown) {
        turtle = turtleList.get(activeTurtleNumber.get() - 1);
        turtle.setPenDown(penDown);
    }

    public void setTurtleVisible (boolean showing) {
        // turtle = turtleList.get(activeTurtleNumber.get()-1);
        turtle.setVisible(showing);
    }

    public boolean getTurtleVisible () {
        // turtle = turtleList.get(activeTurtleNumber.get()-1);
        return turtle.getVisible();
    }

    public void updateVariablesMap () {
        myVariableNames.clear();
        myVariableValues.clear();
        Map<String, Double> variablesMap = myController.getUnmodifiableVariablesMap();
        for (String key : variablesMap.keySet()) {
            if (!myVariableNames.contains(key)) {
                myVariableNames.add(key);
                myVariableValues.add(variablesMap.get(key).toString());
            }
        }
    }

    private void renderVariablesMap (HBox variablesBox, List<Clickable> variableBoxClickables) {
        for (Clickable g : variableBoxClickables) {
            variablesBox.getChildren().add((Node) g.getClickable());
        }
        variablesBox.setMaxWidth(SCREEN_WIDTH / 4);
    }

    public int getNumTurtles () {
        return turtleList.size();
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

    }

}
