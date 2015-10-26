package view;

import java.io.File;
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


public class GUI extends Application{

    private GUIfactory myFactory;

    public static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
    private ResourceBundle myResources;
    private Controller myController;
    private BorderPane root;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private ObservableList<String> myHistList;
    private ObservableList<String> myVariableNames;
    private ObservableList<String> myVariableValues;
    private ObservableList<String> myColorsList;
    private Pane canvasBox;
    final FileChooser fileChooser;
    private List<Turtle> turtleList;
    private Turtle turtle;
    
    private ReadOnlyIntegerProperty activeTurtleNumber;
    // consider adding a public method called get myHistList, that returns
    // immutable histList

    public GUI (Controller controller, String language, ReadOnlyIntegerProperty myActiveTurtleNum) {
        turtle = new Turtle(SCREEN_WIDTH, SCREEN_HEIGHT);
        turtle.setTurtleID(1);
        turtleList = new ArrayList<Turtle>();
        turtleList.add(turtle);
      
        

        Image image = new Image(getClass().getClassLoader().getResourceAsStream("turtle.gif"));
        turtle.setTurtleImage(image);
        
        
        activeTurtleNumber = myActiveTurtleNum;
        activeTurtleNumber.addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(turtleList.size()<newValue.intValue()){
					turtleList.add(newValue.intValue()-1, new Turtle(SCREEN_WIDTH, SCREEN_HEIGHT));
					turtleList.get(newValue.intValue()-1).setTurtleImage(image);
					turtleList.get(newValue.intValue()-1).setTurtleID(newValue.intValue());
					canvasBox.getChildren().add(turtleList.get(newValue.intValue()-1).getTurtleImage());
				//System.out.println("got here");
				}
				//make sure this works, if does rename to active turtle
				//turtle = turtleList.get(activeTurtleNumber.get()-1);
				System.out.println("active turtle is " + newValue.intValue()+ " and was "+oldValue.intValue());
			}});

        
        myHistList = FXCollections.observableArrayList();
        myVariableNames = FXCollections.observableArrayList();
        myVariableValues = FXCollections.observableArrayList();
        myColorsList = FXCollections.observableArrayList();
        initColors(myColorsList);// myHistList.add("History");

        fileChooser = initFileChooser();
        myController = controller;

        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        myFactory = new GUIfactory(myResources, myController);

        root = myFactory.makeBorderPane();

        HBox optionsBox = myFactory.makeHBox();
        root.setTop(optionsBox);

        canvasBox = myFactory.makePane();

        /*
         * Background canvasBG = new Background(???);
         * canvasBox.setBackground(canvasBG);
         */

        // Make VARIABLE CHANGEABLE BY BACKEND
        // canvasBox.getChildren().add(new Rectangle(50,50));

        // not sure if this actually works
        canvasBox.setPrefSize(SCREEN_WIDTH * (3 / 4), SCREEN_HEIGHT * (3 / 4));
        root.setCenter(canvasBox);

        ClickableManager myClickableManager = new ClickableManager(canvasBox, turtle, myColorsList);
        List<Clickable> optionsBoxClickables = myClickableManager.getOptionsBoxClickables();
        for (Clickable g : optionsBoxClickables) {
            optionsBox.getChildren().add((Node) g.getClickable());
        }

        HBox commandAndVarBox = myFactory.makeHBox();
        commandAndVarBox.setMaxHeight(SCREEN_HEIGHT / 5);
        root.setBottom(commandAndVarBox);

        TextArea t = myFactory.makeTextArea();
        commandAndVarBox.getChildren().add(t);

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
        commandAndVarBox.getChildren()
                .add(myFactory.makeButton("Go", e -> myController.submit(t.getText(), "English")));

        VBox variablesDisplayContainer = myFactory.makeVBox();
        HBox variablesBox = myFactory.makeHBox();
        variablesDisplayContainer.getChildren().add(new Text("Variables"));

        ///////// The list of variables needs to come from backend. updated by
        ///////// backend.
        /// Rather than be a list of strings, list of some type of object
        ///////// (variables object?) that
        ///////// can
        // be changed on click. To change on click see lambda function for history command box.
        renderVariablesMap(variablesBox);
        variablesDisplayContainer.getChildren().add(variablesBox);
        commandAndVarBox.getChildren().add(variablesDisplayContainer);

        optionsBox.getChildren()
                .add(myFactory.makeButton("pickImageButton", e -> this.pickImage()));
        // make a root, etc, layout everything with the GUIfactory
        
        canvasBox.getChildren().add(turtle.getTurtleImage());

    }

    private FileChooser initFileChooser () {
        FileChooser f = new FileChooser();
        f.setTitle("Open Image File");
        f.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
        return f;
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
       
        try {
			start(primaryStage);
		} catch (Exception e) {
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

    private void pickImage () {
    	turtle = turtleList.get(activeTurtleNumber.get()-1);
    	
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Image image1 = new Image(file.toURI().toString());
            canvasBox.getChildren().remove(turtle.getTurtleImage());
            turtle.setTurtleImage(image1);
            canvasBox.getChildren().add(turtle.getTurtleImage());
        }
    }

    public void drawLine () {
    	turtle = turtleList.get(activeTurtleNumber.get()-1);
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
    	
    	//rename to Set Turtle Position
    	
    	turtle = turtleList.get(activeTurtleNumber.get()-1);
    	System.out.println("turtle to update: "+ activeTurtleNumber.get());
    	
        turtle.setPastXPos(turtle.getCurrentXPos());
        turtle.setPastYPos(turtle.getCurrentYPos());
        double slope;
        double deltaX = Pos[0] - turtle.getPastXPos();
        boolean forward = deltaX >0;
        double deltaY = Pos[1] - turtle.getPastYPos();
       
        if(deltaX==0){
        	slope = 1;
        }else{
        	slope = deltaY/deltaX;
        }
       
       /*
        while((turtle.getCurrentXPos() != Pos[0]) || (turtle.getCurrentYPos() !=Pos[1])){
        	System.out.println("in while loop to animate");
        	if(forward){
        	turtle.setCurrentXPos(turtle.getCurrentXPos()+1);
        	turtle.setCurrentYPos(turtle.getCurrentYPos()+slope);
        	}
        	if(!forward && deltaX != 0){
        		turtle.setCurrentXPos(turtle.getCurrentXPos()-1);
            	turtle.setCurrentYPos(turtle.getCurrentYPos()-slope);
        	}
        	if(deltaX == 0){
        		turtle.setCurrentYPos(turtle.getCurrentYPos()+deltaY/Math.abs(deltaY));
        	}
        	
        	
        }
        */
        
        
        
       /*
        
        
        KeyFrame frame = new KeyFrame(Duration.millis(10),
                e -> { if((turtle.getCurrentXPos() != Pos[0]) || (turtle.getCurrentYPos() !=Pos[1])){
                	System.out.println("need to move");
                	if(forward){
                	turtle.setCurrentXPos(turtle.getCurrentXPos()+1);
                	turtle.setCurrentYPos(turtle.getCurrentYPos()+slope);
                	}
                	if(!forward && deltaX != 0){
                		turtle.setCurrentXPos(turtle.getCurrentXPos()-1);
                    	turtle.setCurrentYPos(turtle.getCurrentYPos()-slope);
                	}
                	if(deltaX == 0){
                		turtle.setCurrentYPos(turtle.getCurrentYPos()+deltaY/Math.abs(deltaY));
                	}
                	
                	
                	//This math is all messed up tho
                	
                	drawLine();
                }});
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
        
        */
        
        turtle.setCurrentXPos(Pos[0]);
        turtle.setCurrentYPos(Pos[1]);
        drawLine();
    }

    public double[] getTurtlePosition () {
    	turtle = turtleList.get(activeTurtleNumber.get()-1);
        double[] pos = new double[2];
        pos[0] = turtle.getCurrentXPos();
        pos[1] = turtle.getCurrentYPos();

        return pos;
    }

    public double getTurtleDirection () {
    	turtle = turtleList.get(activeTurtleNumber.get()-1);
    	return turtle.getDirection();
    }

    public void setTurtleDirection (double angle) {
    	turtle = turtleList.get(activeTurtleNumber.get()-1);
        turtle.setDirection(angle);


        System.out.println(activeTurtleNumber.intValue());

    }

    public boolean getPenBool () {
    	turtle = turtleList.get(activeTurtleNumber.get()-1);
        return turtle.isPenDown();
    }

    public void setTurtlePen (boolean penDown) {
    	//turtle = turtleList.get(activeTurtleNumber.get()-1);
        turtle.setPenDown(penDown);
    }

    public void setTurtleVisible (boolean showing) {
    	turtle = turtleList.get(activeTurtleNumber.get()-1);
        turtle.setVisible(showing);
    }

    public boolean getTurtleVisible () {
    	turtle = turtleList.get(activeTurtleNumber.get()-1);
    	return turtle.getVisible();
    }
    
	public void updateVariablesMap() {
		myVariableNames.clear();
		myVariableValues.clear();
		Map<String,Double> variablesMap = myController.getUnmodifiableVariablesMap();
        for (String key : variablesMap.keySet()) {
        	if (!myVariableNames.contains(key)) {
            	myVariableNames.add(key);
            	myVariableValues.add(variablesMap.get(key).toString());
        	}
        }
	}
	
	private void renderVariablesMap(HBox variablesBox) {
		ListView variableNames = myFactory.makeClickableList(myVariableNames);
		ListView variableValues = myFactory.makeClickableList(myVariableValues);
		variableNames.setOnMouseClicked(e -> launchVariablePopUp("Change variable name", variableNames, 
				(a,b) -> onVariableNameChange(a,b)));
		variableValues.setOnMouseClicked(e -> launchVariablePopUp("Change variable value", variableValues,
				(a,b) -> onVariableValueChange(a, b)));
        variablesBox.getChildren().add(variableNames);
        variablesBox.getChildren().add(variableValues);
        variablesBox.setMaxWidth(SCREEN_WIDTH/4);
	}
	
	private void launchVariablePopUp(String displayMessage, ListView variableBox, BiConsumer<String,String> changeVariableFunc) {
		int selectedIndex = variableBox.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			String originalValue = myVariableNames.get(selectedIndex);
			TextInputDialog variablePopup = new TextInputDialog();
			variablePopup.setTitle("Variable Definition");
			variablePopup.setHeaderText("Modify Variable");
			variablePopup.setContentText(displayMessage);
			Optional<String> input = variablePopup.showAndWait();
			changeVariableFunc.accept(originalValue, input.get() == null ? "" : input.get());
			updateVariablesMap();
		}
	}
	
	private void onVariableNameChange(String oldName, String newName) {
		if (!newName.equals("")) {
			myController.changeVariableName(oldName, newName);
		}
	}
	
	private void onVariableValueChange(String key, String newValue) {
		if (!newValue.equals("")) {
			myController.changeVariableValue(key, newValue);
		}
	}

	public int getNumTurtles() {
		return turtleList.size();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		
	}

}
