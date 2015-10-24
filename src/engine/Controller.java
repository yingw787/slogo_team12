package engine;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableNumberValue;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import model.BackEndController;
import view.GUI;

public class Controller extends Application {

	private GUI myGUI;
	private BackEndController myBackend;
	private Stage myStage;
	private String myLanguage = "English";
	private IntegerProperty myActiveTurtle;
	
	//IntegerProperty active = new SimpleIntegerProperty(1);
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		myStage = primaryStage;
//import model.Backend;
//import view.GUI;


		//discussed in lecture -- Observable!
		//sets up observable relationships
		//for making modular, for instace, history pane, canvas, both 
		//want to know when something has changed (something obervable)
		//then act everytime change

		initializeActiveTurtleProperty();
		myGUI = new GUI(this, myLanguage, myActiveTurtle);
		myBackend = new BackEndController(this);
		
		//init gui to set up everything, call this part of it last
		myGUI.setAndShowScene(myStage);

		
		
		
	}
	
	public void reset(){
		System.out.println("reset");
		initializeActiveTurtleProperty();
		myGUI = new GUI(this, myLanguage, myActiveTurtle);
		myGUI.setAndShowScene(myStage);
		myBackend = new BackEndController(this);
	

		//clear history, reset turtle, clear everythibg.
		//just make new Gui object and set it? decide what to do

		
		
	}
	
	public void submit(String stringFromGUI, String myLanguage){
		
		if (stringFromGUI.trim().length() > 0) {
		myGUI.addToHistory(stringFromGUI);
		myBackend.generateTurtleCommands(stringFromGUI, myLanguage);
		}
	}
	
	public void makeNewWindow(Stage s){
		
		Controller anotherController = new Controller();
		try {
			anotherController.start(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public int getActiveTurtleID() {
		return myActiveTurtle.get();
	}
	
	public void setActiveTurtleID(int newID) {
		myActiveTurtle.set(newID);
	}

	
	//ELIZABETH'S ADDITIONS FOR CONNECTING TURTLE TO COMMANDS
	//refactor these to only get and set values for myActiveTurtle id number
	public void clear() {
		myGUI.clearLines();
	}
	
	public int getNumTurtles() {
		//TODO get the max turtle ID aka number of turtles
		return 1;
	}
	
	public double getTurtleDirection() {
		
	    return myGUI.getTurtleDirection();
	}
	
	public void setTurtleDirection(double angle) {
		
		
	    myGUI.setTurtleDirection(angle);
	    
	    
	}
	
	public double[] getTurtlePosition() {
		return myGUI.getTurtlePosition();
		

	}
	
	public void setTurtlePosition(double[] newPos) {
		///!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		//TRY to make animated, for loop for old position to new position...
		//TODO 
		myGUI.updateTurtle(newPos);
		if(isTurtlePenDown()){
		myGUI.drawLine();
		}
		//sets turtle's x,y position
		
		
		
	}
	
	public boolean isTurtlePenDown() {
		// returns true if pen is down
		return myGUI.getPenBool();
	}
	
	public void setIsTurtlePenDown(boolean penDown) {
		myGUI.setTurtlePen(penDown);
		// sets turtle's "pen down" boolean
	}
	
	public boolean isTurtleShowing() {
		// TODO returns true if turtle is showing
		return myGUI.getTurtleVisible();
	}
	
	public void setIsTurtleShowing(boolean showing) {
		
		myGUI.setTurtleVisible(showing);
		//sets turtle's "hidden" boolean
	}
	
	private void initializeActiveTurtleProperty(){
		myActiveTurtle = new SimpleIntegerProperty(1);
	}
}
