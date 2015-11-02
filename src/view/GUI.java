package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import engine.Controller;
import engine.IController;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GUI extends Application {

    private GUIfactory myFactory;

    public static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
    private ResourceBundle myResources;
    private IController myController;
    private BorderPane root;
    private static final double SCREEN_WIDTH = 800;
    private static final double SCREEN_HEIGHT = 600;
    private static final double CANVAS_RATIO = 0.75;
    private ObservableList<String> myHistList;
    private ObservableList<String> myVariableNames;
    private ObservableList<String> myVariableValues;
    private Map<Integer, Color> myColors;
    private ObservableList<Integer> myColorsIndex;
    private ObservableList<Rectangle> colorView;
    private Pane canvasBox;
    private List<Turtle> turtleList;
    private Turtle turtle;
    private Stage myStage;
    private Image image;
    private KeyFrame frame;
    private Timeline animation;
    private int ANIMATION_DURATION = 10;

    private ReadOnlyIntegerProperty activeTurtleNumber;

    private Slider mySlider;

    public GUI (IController controller, String language, ReadOnlyIntegerProperty myActiveTurtleNum) {

        setUpTurtles(myActiveTurtleNum);
        initializeHistVarAndColorsLists();
        myController = controller;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        myFactory = new GUIfactory(myResources, myController);
        layOutGUI();

    }

    private void layOutGUI () {
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

        ClickableManager myClickableManager =
                new ClickableManager(canvasBox, turtle, myColorsIndex, myColors, colorView,
                                     myController, t,
                                     myHistList,
                                     myVariableNames, myVariableValues, this);

        List<Clickable> optionsBoxClickables = myClickableManager.getOptionsBoxClickables();
        List<Clickable> commandAndVarBoxClickables =
                myClickableManager.getCommandAndVarBoxClickables();
        List<Clickable> variableBoxClickables = myClickableManager.getVariableBoxClickables();
        List<Clickable> historyBoxClickables = myClickableManager.getHistoryBoxClickables();

        initializeSlider();
        // dont know how to add to clickables manager since slide needs control on GUI
        initOptionsBox(optionsBox, optionsBoxClickables);
        // optionsBox.getChildren().add(myFactory.makeButton("Reset Animation", e -> anima))

        initHistory(historyBox, t, historyBoxClickables);

        initCommandAndVarBox(commandAndVarBox, commandAndVarBoxClickables, t,
                             variableBoxClickables);

        canvasBox.getChildren().add(turtle.getTurtleImage());
    }

    private void initOptionsBox (HBox optionsBox, List<Clickable> optionsBoxClickables) {
        for (Clickable g : optionsBoxClickables) {
            optionsBox.getChildren().add((Node) g.getClickable());
        }
        optionsBox.getChildren().add(new Text("animation duration\n (in ms)"));
        optionsBox.getChildren().add(mySlider);
        optionsBox.getChildren().add(myFactory.makeButton("Pause", e -> animation.pause()));
        optionsBox.getChildren().add(myFactory.makeButton("Play", e -> {
            if (animation.getStatus() != Status.STOPPED) {
                animation.play();
            }
        }));
    }

    private void initHistory (VBox historyBox, TextArea t, List<Clickable> historyBoxClickables) {
        root.setRight(historyBox);
        historyBox.setMaxWidth(SCREEN_WIDTH / 4);
        historyBox.getChildren().add(new Text("History"));
        for (Clickable g : historyBoxClickables) {
            historyBox.getChildren().add((Node) g.getClickable());
        }

    }

    private void initializeHistVarAndColorsLists () {
        myHistList = FXCollections.observableArrayList();
        myVariableNames = FXCollections.observableArrayList();
        myVariableValues = FXCollections.observableArrayList();
        myColorsIndex = FXCollections.observableArrayList();
        colorView = FXCollections.observableArrayList();
        myColors = new HashMap<Integer, Color>();
        initColors(myColorsIndex,colorView,myColors);
    }

    private void initColors (ObservableList<Integer> myColorsIndex,
                             ObservableList<Rectangle> colorView,
                             Map<Integer, Color> myColors) {
       myColorsIndex.addAll(1, 2, 3, 4,5);
       myColors.put(1, Color.rgb(255, 0, 0));
       myColors.put(2, Color.rgb(0, 255, 0));
       myColors.put(3, Color.rgb(0, 0, 255));
       myColors.put(4, Color.rgb(0, 0, 0));
       myColors.put(5, Color.rgb(255, 255, 255));
        for (Color g : myColors.values()) {
            colorView.add(new Rectangle(10, 10, g));
        }
        
    }

    private void setUpTurtles (ReadOnlyIntegerProperty myActiveTurtleNum) {
        image = new Image(getClass().getClassLoader().getResourceAsStream("turtle.gif"));
        initTurtle(image, 1);
        setUpTurtleList(myActiveTurtleNum, image);
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
                                       List<Clickable> commandAndVarBoxClickables,
                                       TextArea t,
                                       List<Clickable> variableBoxClickables) {
        VBox commandAndVarBoxButtonsHolder = new VBox();
        for (Clickable g : commandAndVarBoxClickables) {
            commandAndVarBoxButtonsHolder.getChildren().add((Node) g.getClickable());
        }

        commandAndVarBox.getChildren().add(commandAndVarBoxButtonsHolder);
        commandAndVarBox.getChildren().add(t);

        VBox variablesDisplayContainer = myFactory.makeVBox();
        HBox variablesBox = myFactory.makeHBox();
        variablesDisplayContainer.getChildren().add(new Text("Variables"));

        renderVariablesMap(variablesBox, variableBoxClickables);
        variablesDisplayContainer.getChildren().add(variablesBox);
        commandAndVarBox.getChildren().add(variablesDisplayContainer);
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

    public void setPalette (int index, Color color) {
        if (myColorsIndex.contains(index)) {
            myColorsIndex.remove((Integer) index);
            myColors.remove(index);
        }
        myColorsIndex.add(index);
        myColors.put(index, color);
        for (Color g : myColors.values()) {
            colorView.add(new Rectangle(10, 10, g));
        }
    }

    public void drawLine (Turtle turt) {
        // turtle = turtleList.get(activeTurtleNumber.get()-1);
        double turtleHeight = turt.getTurtleImage().getFitHeight();
        double turtleWidth = turt.getTurtleImage().getFitWidth();
        Line line = new Line();
        line.setStartX(turt.getPastXPos() + turtleWidth / 2);
        line.setStartY(turt.getPastYPos() + turtleHeight / 2);
        line.setEndX(turt.getCurrentXPos() + turtleWidth / 2);
        line.setEndY(turt.getCurrentYPos() + turtleHeight / 2);
        line.setStroke(turt.getPenColor());
        canvasBox.getChildren().add(line);

    }

    public void clearLines () {
        List<Node> newChildren = canvasBox.getChildren().stream()
                .filter(child -> !child.getClass().equals(new Line().getClass()))
                .collect(Collectors.toList());
        canvasBox.getChildren().clear();
        canvasBox.getChildren().addAll(newChildren);

    }

    public void updateTurtle (double[] Pos, int ID) {

        Turtle tempTurtle = turtleList.get(ID - 1);
        double deltaX = Pos[0] - tempTurtle.getCurrentXPos();
        double deltaY = Pos[1] - tempTurtle.getCurrentYPos();

        frame = new KeyFrame(Duration.millis(ANIMATION_DURATION),
                             e -> {

                                 tempTurtle.setPastXPos(tempTurtle.getCurrentXPos());
                                 tempTurtle.setPastYPos(tempTurtle.getCurrentYPos());
                                 tempTurtle.setCurrentXPos(tempTurtle.getCurrentXPos() +
                                                           (deltaX) / 100);
                                 tempTurtle.setCurrentYPos(tempTurtle.getCurrentYPos() +
                                                           (deltaY) / 100);

                                 if (tempTurtle.isPenDown()) {
                                     drawLine(tempTurtle);
                                 }

                                 if (Math.abs(tempTurtle.getCurrentXPos() - Pos[0]) < 0.009) {
                                     tempTurtle.setCurrentXPos(Pos[0]);
                                 }
                                 if (Math.abs(tempTurtle.getCurrentYPos() - Pos[1]) < 0.009) {
                                     tempTurtle.setCurrentYPos(Pos[1]);
                                 }

                             });
        //animation = new Timeline();
        animation.setCycleCount(100);
        animation.getKeyFrames().add(frame);
       // animation.setOnFinished(e-> notifyAll());
        animation.play();

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

    public void setTurtlePenColor (int index) {
        turtle.setPenIndex(index);
        turtle.setPenColor(myColors.get(index));
    }

    public int getTurtlePenIndex () {
        return turtle.getPenIndex();
    }

    public void setTurtleDirection (double angle) {
        Turtle  myTurtle = turtleList.get(activeTurtleNumber.get()-1);
        
        /*
        if(animation.getStatus() == Status.RUNNING || animation.getStatus() == Status.PAUSED){
        	try {
        		wait(ANIMATION_DURATION);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        */
        myTurtle.setDirection(angle);
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

    public void setBackgroundColor (int index) {
        canvasBox
                .setStyle("-fx-background-color: " + getCssSpec((Color) myColors.get(index)) + ";");
    }

    // This code from stack overflow
    // http://stackoverflow.com/questions/21321482/easier-way-to-assign-instyle-css
    private String getCssSpec (Color c) {
        int r = (int) (c.getRed() * 256);
        int g = (int) (c.getGreen() * 256);
        int b = (int) (c.getBlue() * 256);
        return String.format("rgb(%d, %d, %d)", r, g, b);
    }

    private void initializeSlider () {
        this.mySlider = new Slider(10, 2000, ANIMATION_DURATION * 10);
        this.mySlider.setOrientation(Orientation.HORIZONTAL);
        this.mySlider.setShowTickMarks(false);
        this.mySlider.setShowTickLabels(true);
        this.mySlider.setMajorTickUnit(1000);
        // this.mySlider.setBlockIncrement(OPTION_SLIDER_BLOCK_INCREMENT);
        this.mySlider.valueProperty()
                .addListener( (observable, oldValue, newValue) -> handleSliderChange(observable,
                                                                                     oldValue,
                                                                                     newValue));
        // this.myOptionBox.getChildren().add(this.mySlider);
    }

    private void handleSliderChange (ObservableValue<? extends Number> observable,
                                     Number oldValue,
                                     Number newValue) {
        ANIMATION_DURATION = newValue.intValue() / 10;
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

    }

}
