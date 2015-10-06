package view;

import java.util.ResourceBundle;

import engine.Controller;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI {

	private GUIfactory myFactory; 
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	private ResourceBundle myResources;
	private Controller myController;
	private Parent root;
	private static final int SCREEN_WIDTH = 600;
	private static final int SCREEN_HEIGHT = 600;
	
	public GUI(Controller controller, String language){
		myController = controller;
		
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		myFactory = new GUIfactory(myResources);
		
		
		//make a root, etc, layout everything with the GUIfactory
		
	}
	
	public void setAndShowScene(Stage primaryStage){
		root = myFactory.makeBorderPane();
		//WIDTH AND HEIGHT, MORE DETAILS FOR SCENE
		primaryStage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT));
		primaryStage.show();
		
	}
}
