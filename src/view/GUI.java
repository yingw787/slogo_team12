package view;

import java.util.ResourceBundle;
import engine.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
	private String canvasColor;
	//consider adding a public method called get myHistList, that returns immutable histList

	public GUI(Controller controller, String language){

		myHistList = FXCollections.observableArrayList();
		// myHistList.add("History");

		myController = controller;


		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		myFactory = new GUIfactory(myResources, myController);


		root = myFactory.makeBorderPane();

		HBox optionsBox = myFactory.makeHBox();
		root.setTop(optionsBox);
		optionsBox.getChildren().add(new Text("Language option to go here?"));
		
		
		Pane canvasBox = myFactory.makePane();
		
		/*
		Background canvasBG = new Background(???);
		canvasBox.setBackground(canvasBG);
		*/
		
		//Make VARIABLE CHANGEABLE BY BACKEND
		canvasColor = "green";
		try{
		canvasBox.setStyle("-fx-background-color: "+canvasColor+";");
		}catch(Exception e){
			//invalid color 
		}
		//canvasBox.getChildren().add(new Rectangle(50,50));
		
		//not sure if this actually works
		canvasBox.setPrefSize(SCREEN_WIDTH*(3/4), SCREEN_HEIGHT*(3/4));
		root.setCenter(canvasBox);

		
		
		HBox commandAndVarBox = myFactory.makeHBox();
		commandAndVarBox.setMaxHeight(SCREEN_HEIGHT/5);
		root.setBottom(commandAndVarBox);
		
		TextArea t = myFactory.makeTextArea();
		commandAndVarBox.getChildren().add(t);
		
		

		VBox historyBox = myFactory.makeVBox();
		historyBox.setMaxWidth(SCREEN_WIDTH/4);
		historyBox.getChildren().add(new Text("History"));
		root.setRight(historyBox);
		
		ListView myHistListView = myFactory.makeClickableList(myHistList);
		historyBox.getChildren().add(myHistListView);
		historyBox.getChildren().add(myFactory.makeButton("reset", e -> myController.reset()));


		//this should be moved to a more appropriate place
		myHistListView.setOnMouseClicked(e -> {
			//lambda, checks if selected is not null, if its not, populate the command box with the selected history
			if(!(myHistListView.getSelectionModel().getSelectedItem() == null)){
				t.setText((myHistListView.getSelectionModel().getSelectedItem().toString()));
			}

		});


	


		// makeButton: setOnAction(e-> myController.submit(t.getText()));
		commandAndVarBox.getChildren().add(myFactory.makeButton("Go", e -> myController.submit(t.getText())));

		VBox variablesBox = myFactory.makeVBox();
		variablesBox.getChildren().add(new Text("Variables"));


		///////// The list of variables needs to come from backend. updated by backend. 
		/// Rather than be a list of strings, list of some type of object (variables object?) that can 
		//be changed on click. To change on click see lambda function for history command box.
		ObservableList<String> listOfVariables = FXCollections.observableArrayList();
		listOfVariables.add("Variable 1");

		variablesBox.getChildren().add(myFactory.makeClickableList(listOfVariables));
		commandAndVarBox.getChildren().add(variablesBox);


		

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
