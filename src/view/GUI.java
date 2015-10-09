package view;

import java.util.ResourceBundle;

import engine.Controller;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI {

	private GUIfactory myFactory; 
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	private ResourceBundle myResources;
	private Controller myController;
	private BorderPane root;
	private static final int SCREEN_WIDTH = 600;
	private static final int SCREEN_HEIGHT = 600;
	
	public GUI(Controller controller, String language){
		myController = controller;
		
		
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		myFactory = new GUIfactory(myResources, myController);
		
		
		root = myFactory.makeBorderPane();
		
		
		Pane canvasBox = myFactory.makePane();
			root.setLeft(canvasBox);
		
		VBox historyBox = myFactory.makeVBox();
			root.setRight(historyBox);
			
		HBox commandBox = myFactory.makeHBox();
	
			root.setBottom(commandBox);
			
		
		canvasBox.getChildren().add(myFactory.makeButton("canvas", e -> myController.reset()));
		
		historyBox.getChildren().add(myFactory.makeButton("history", e ->myController.reset()));
		TextArea t = myFactory.makeTextArea();
		commandBox.getChildren().add(t);
		
		// makeButton: setOnAction(e-> myController.submit(t.getText()));
		commandBox.getChildren().add(myFactory.makeButton("Go", e -> myController.submit()));
		
		historyBox.getChildren().add(myFactory.makeButton("reset", e -> myController.reset()));
		
		//make a root, etc, layout everything with the GUIfactory
		
	}
	
	public void setAndShowScene(Stage primaryStage){
		
		//WIDTH AND HEIGHT, MORE DETAILS FOR SCENE
		primaryStage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT));
		primaryStage.show();
		
	}
}
