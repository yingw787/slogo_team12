package view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import engine.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
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
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 600;
	private ObservableList<String> myHistList; 
	
	//consider adding a public method called get myHistList, that returns immutable histList
	
	public GUI(Controller controller, String language){
		
		myHistList = FXCollections.observableArrayList();
	  // myHistList.add("History");

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
			
	
			TextArea t = myFactory.makeTextArea();
			commandBox.getChildren().add(t);
			
			
		ListView myHistListView = myFactory.makeClickableList(myHistList);
		historyBox.getChildren().add(myHistListView);
		
		
		//this should be moved to a more approptiate place
		
		
		myHistListView.setOnMouseClicked(e -> {
			//lambda, checks if selected is not null, if its not, populate the command box with the selected history
			if(!(myHistListView.getSelectionModel().getSelectedItem() == null)){
				t.setText((myHistListView.getSelectionModel().getSelectedItem().toString()));
			}
			
		});
		
		
		canvasBox.getChildren().add(myFactory.makeButton("canvas", e -> myController.reset()));
		
		
		
		// makeButton: setOnAction(e-> myController.submit(t.getText()));
		commandBox.getChildren().add(myFactory.makeButton("Go", e -> myController.submit(t.getText())));
		
		historyBox.getChildren().add(myFactory.makeButton("reset", e -> myController.reset()));
		
		
		//make a root, etc, layout everything with the GUIfactory
		
	}
	
	public void setAndShowScene(Stage primaryStage){
		
		//WIDTH AND HEIGHT, MORE DETAILS FOR SCENE
		primaryStage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT));
		primaryStage.show();
		
	}

	public void addToHistory(String stringFromGUI) {
		
		if (stringFromGUI.trim().length() > 0){
			//checks that its not just all whitespace
		myHistList.add(stringFromGUI);
		}
		//this will become private when we set up an observer relationship
		//currently controller directly calls this in submit
		
	}
}
