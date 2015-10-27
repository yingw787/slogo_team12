package engine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;
import model.BackEndController;
import view.GUI;

public class Controller extends Application {

	private GUI myGUI;
	private BackEndController myBackend;
	private Stage myStage;
	private String myLanguage = "English";
	private IntegerProperty myActiveTurtle;
	private Map<String,Double> myVariablesMap;
	
	public Controller() {
		myVariablesMap = new HashMap<String,Double>();
	}
	
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
			myGUI.updateVariablesMap();
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
	
	public Map<String,Double> getUnmodifiableVariablesMap() {
		return Collections.unmodifiableMap(myVariablesMap);
	}
	
	public Map<String,Double> getVariablesMap() {
		return myVariablesMap;
	}
	
	public void setVariablesMap(Map<String,Double> variablesMap) {
		myVariablesMap = variablesMap;
	}
	
	public void changeVariableName(String oldName, String newName) {
		double value = myVariablesMap.get(oldName);
		myVariablesMap.remove(oldName);
		myVariablesMap.put(newName, value);
	}
	
	public void changeVariableValue(String key, String newValue) {
		myVariablesMap.put(key, Double.parseDouble(newValue));
	}

	
	//ELIZABETH'S ADDITIONS FOR CONNECTING TURTLE TO COMMANDS
	public void setBackgroundColor(int index) {
		//TODO sets background color to specified color (by index)
	}
	
	public void setPenColor(int index) {
		//TODO sets pen color to specified color (by index) (for active turtle!)
	}
	
	public void setPenSize(double pixels) {
		//TODO sets pen stroke size to pixels value
	}
	
	public void setShape(int index) {
		//TODO sets shape of turtle to that represented by index
	}
	
	public void setPalette(int index, String newColor) {
		//TODO sets color in palette at index to newColor
	}
	
	public int getPenColor() {
		//TODO returns the index of the active turtle's pen color
		return 0;
	}
	
	public int getShape() {
		//TODO returns the index of the active turtle's shape
		return 0;
	}
	
	public void makeStamp() {
		//TODO makes a stamp of the active turtle
	}
	
	public int getNumStamps() {
		//TODO returns number of stamps on the canvas
		return 0;
	}
	
	public void clearStamps() {
		//TODO clears all stamps off the canvas (similar to clearLines())
	}
	
	public int getNumTurtles() {
		//TODO get the max turtle ID aka number of turtles
		return myGUI.getNumTurtles();
	}
	
	public void clear() {
		myGUI.clearLines();
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
		myGUI.updateTurtle(newPos);
		if(isTurtlePenDown()){
			myGUI.drawLine();
		}
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
		return myGUI.getTurtleVisible();
	}
	
	public void setIsTurtleShowing(boolean showing) {
		
		myGUI.setTurtleVisible(showing);
		//sets turtle's "hidden" boolean
	}
	
	private void initializeActiveTurtleProperty(){
		myActiveTurtle = new SimpleIntegerProperty(1);
	}

	public void onSave(String input, String programTitle) {
		myBackend.saveProgram(input, programTitle);
	}
}
